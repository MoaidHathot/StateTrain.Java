package statetrain.builder;

import statetrain.builder.exceptions.StateMachineBuilderException;
import statetrain.core.State;
import statetrain.core.StateMetadata;
import statetrain.core.behavior.IBehavior;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public interface IStateBuilder<TTrigger, TState> {
    State<TTrigger, TState> build() throws StateMachineBuilderException;

    IStateBuilder<TTrigger, TState> addTransition(TTrigger trigger, TState state);

    IStateBuilder<TTrigger, TState> setName(String name) throws StateMachineBuilderException;

    IStateBuilder<TTrigger, TState> setState(TState state);

    IStateBuilder<TTrigger, TState> setMetadata(StateMetadata metadata);

    IStateBuilder<TTrigger, TState> modifyMetadata(Consumer<StateMetadata> consumer);

    IStateBuilder<TTrigger, TState> setBehaviors(List<Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>>> behaviors);

    IStateBuilder<TTrigger, TState> addBehavior(IBehavior<TTrigger, TState> behavior);

    IStateBuilder<TTrigger, TState> addBehavior(Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>> function);

    IStateBuilder<TTrigger, TState> addBehaviorAsFirst(Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>> function);

    IStateBuilder<TTrigger, TState> ignoreGlobalBehaviors();

    TState getState();

    String getName();

    boolean getIgnoreGlobalBehaviors();

    StateMetadata getMetadata();

    Map<TTrigger, TState> getStateMap();

    List<Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>>> getBehaviors();
}
