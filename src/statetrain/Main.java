package statetrain;

import statetrain.core.State;
import statetrain.core.TransitionArgs;
import statetrain.core.event.IStateEventNotifier;
import statetrain.core.event.args.*;
import statetrain.exceptions.StateMachineException;
import statetrain.builder.StateMachineBuilder;
import statetrain.core.StateMachine;
import statetrain.utils.timing.schedule.TimerScheduler;
import statetrain.core.behavior.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException, StateMachineException {

        final var machine = buildStateMachineFluentAPIs();

        machine.triggerTransition("Call-incoming");
        machine.triggerTransition("Call-accepted");
        machine.triggerTransition("blabla");
        machine.triggerTransition("Call-incoming");
//        machine.triggerTransition("Call-accepted");
//        machine.triggerTransition("Call-ended");

        System.in.read();

        System.out.println("Finished with: " + machine.getCurrentState());
    }

    static StateMachine<String, String> buildStateMachineFluentAPIs() throws StateMachineException {
        var scheduler = new TimerScheduler("State Timeout Scheduler");

        var builder = new StateMachineBuilder<String, String>("Idle", "InError");

        builder.configureState("Idle")
                .addTransition("Call-incoming", "InAnswerDecision")
                .addBehavior(state -> new RegisteredTriggerTransitionBehavior<>(state.getState()))
                .addBehavior(state -> new UnregisteredTriggerTransitionBehavior<>(state.getState(), "InUnfamiliarState"));;

        builder.configureState("InAnswerDecision")
                .addTransition("Call-accepted", "InCall")
//              .addTransition("Call-declined", "Idle")
                .addTransition("Call-declined", "InCallEnded")
                .addTransition("Call-declined-timeout", "InCallEnded")
                .addTransition("Call-ended", "Idle")
                .addBehavior(state -> new RegisteredTriggerTransitionBehavior<>(state.getState()))
                .addBehavior(state -> new TimeoutTriggerTransitionBehavior<>(state.getState(), Duration.ofSeconds(3), "Call-declined-timeout", scheduler))
                .addBehavior(state -> new UnregisteredTriggerTransitionBehavior<>(state.getState(), "InUnfamiliarState"));

        builder.configureState("InCall")
                .addTransition("Call-ended", "InCallEnded")
                .addBehavior(state -> new RegisteredTriggerTransitionBehavior<>(state.getState()))
                .addBehavior(state -> new UnregisteredTriggerTransitionBehavior<>(state.getState(), "InUnfamiliarState"));

        builder.configureState("InCallEnded")
                .addTransition("Call-summary-ended", "Idle")
                .addBehavior(state -> new RegisteredTriggerTransitionBehavior<>(state.getState()))
                .addBehavior(state -> new ImmediateTriggerTransitionStateBehavior<>(state.getState(), "Call-summary-ended"));

        builder.configureState("InUnfamiliarState")
                .addTransition("ContinueToIdle", "Idle")
//                .addBehavior(state -> new ImmediateStateTransitionBehavior<>(state.getState(), "TriggerRandom", "Idle"));
                .addBehavior(state -> new RegisteredTriggerTransitionBehavior<>(state.getState()))
                .addBehavior(state -> new ImmediateTriggerTransitionStateBehavior<>(state.getState(), "ContinueToIdle"));

        return builder
                .setDefaultStateBehavior(state -> new RegisteredTriggerTransitionBehavior<>(state.getState()))
                .addNotifier(new ConsolePrinterStateNotifier<>())
                .build();
    }

    private static class ConsolePrinterStateNotifier<TTrigger, TState> implements IStateEventNotifier<TTrigger, TState>{

        @Override
        public void onInitialTransition(InitialTransitionArgs<TTrigger, TState> args) {
            System.out.printf("Initial transition to '%s'\r\n", args.getInitialState().getState());
//            System.out.println(args);
        }

        @Override
        public void onErrorTransition(ErrorTransitionArgs<TTrigger, TState> args) {
            System.out.println("!!!! " + args);
        }

        @Override
        public void onTriggerReceived(TriggerReceivedArgs<TTrigger, TState> args) {
            System.out.printf("\t\t\tTriggered '%s' on state '%s'\r\n", args.getTrigger(), args.getCurrentState().getState());
//            System.out.println("\t\t" + args);
        }

        @Override
        public void onStateTransitioned(StateTransitionedArgs<TTrigger, TState> args) {
            System.out.printf("Transitioned to '%s' from '%s' due to '%s' and '%s'\r\n", args.getNewState().getState(), Optional.ofNullable(args.getOldState()).map(State::getState).orElse(null), args.getTrigger(), args.getTransitionCauser().getName());
//            System.out.println(args);
        }

        @Override
        public void onActivatingBehavior(ActivatingBehaviorArgs<TTrigger, TState> args) {
//            System.out.println("\t\t\t" + args);
        }

        @Override
        public void onActivatedBehavior(ActivatedBehaviorArgs<TTrigger, TState> args) {
//            System.out.println("\t\t\t" + args);
        }

        @Override
        public void onDeactivatedBehavior(DeactivatedBehaviorArgs<TTrigger, TState> args) {
//            System.out.println("\t\t\t" + args);
        }

        @Override
        public void onException(ExceptionArgs<TTrigger, TState> args) {
            System.out.println("!!**!! " + args);
        }

        @Override
        public void onStoppedTransition(StoppedTransitionArgs<TTrigger, TState> args) {
//            System.out.println("\t\t" + args);
        }
    }
}