package statetrain.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StateMachineContext<TTrigger, TState> implements AutoCloseable {

    private final StateMachine<TTrigger, TState> stateMachine;
    private List<Consumer<StateMachineContext<TTrigger, TState>>> resolveActions = new ArrayList<>();

    public StateMachineContext(StateMachine<TTrigger, TState> stateMachine) {
        this.stateMachine = stateMachine;
    }

    public State<TTrigger, TState> getCurrentState(){
        return stateMachine.getCurrentState();
    }

    public void registerResolveAction(Consumer<StateMachineContext<TTrigger, TState>> action){
        resolveActions.add(action);
    }

    public void triggerTransition(TTrigger trigger){
        stateMachine.triggerTransition(trigger);
    }

    public void commit(){
        resolveActions.forEach(a -> a.accept(this));
    }

    @Override
    public void close() {
        commit();
    }
}
