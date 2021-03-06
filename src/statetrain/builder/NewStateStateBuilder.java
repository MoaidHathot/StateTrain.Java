package statetrain.builder;

import statetrain.core.TransitionArgs;
import statetrain.core.behavior.IBehavior;
import statetrain.builder.exceptions.StateMachineBuilderException;
import statetrain.core.State;
import statetrain.core.StateMetadata;
import statetrain.core.behavior.TriggerStateAttachmentBehavior;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NewStateStateBuilder<TTrigger, TState> implements IStateBuilder<TTrigger, TState> {
    private TState state;

    private String name;
    private StateMetadata metadata = new StateMetadata();
    private HashMap<TTrigger, TState> stateMap = new HashMap<>();
    private LinkedList<Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>>> behaviors = new LinkedList<>();
    private Map<TTrigger, List<Consumer<TransitionArgs<TTrigger, TState>>>> attachmentActions = new HashMap<>();
    private boolean ignoreGlobalBehaviors;

    public NewStateStateBuilder(TState state){
        this(state, null);
    }

    public NewStateStateBuilder(TState state, String name){
        this.state = state;
    }

    @Override
    public State<TTrigger, TState> build() throws StateMachineBuilderException {

        if(!attachmentActions.isEmpty()){
            final Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>> attachmentBehavior = s -> new TriggerStateAttachmentBehavior<>(s.getState(), attachmentActions);
            behaviors.addFirst(attachmentBehavior);
        }

        final var optimizedBehaviors = behaviors.stream().map(func -> func.apply(this)).collect(Collectors.toList());;
        return null == name ? new State<>(state, metadata, stateMap, optimizedBehaviors) : new State<>(state, name, metadata, stateMap, optimizedBehaviors);
    }

    @Override
    public IStateBuilder<TTrigger, TState> addTransition(TTrigger trigger, TState state){
        stateMap.put(trigger, state);
        return this;
    }

    @Override
    public IStateBuilder<TTrigger, TState> addTransition(TTrigger trigger, TState state, TransitionTag... tags) {

        for(final var tag : tags){
            if(!attachmentActions.containsKey(trigger)){
                attachmentActions.put(trigger, new ArrayList<>());
            }

            attachmentActions.get(trigger).add(a -> a.getProperties().put(tag.getKey(), tag.getValue()));
        }

        stateMap.put(trigger, state);
        return this;
    }

    @Override
    public IStateBuilder<TTrigger, TState> setName(String name){
        this.name = name;
        return this;
    }

    @Override
    public IStateBuilder<TTrigger, TState> setState(TState state){
        this.state = state;
        return this;
    }

    @Override
    public IStateBuilder<TTrigger, TState> setMetadata(StateMetadata metadata){
        this.metadata = metadata;
        return this;
    }

    @Override
    public IStateBuilder<TTrigger, TState> modifyMetadata(Consumer<StateMetadata> consumer){
        consumer.accept(this.metadata);
        return this;
    }

    @Override
    public IStateBuilder<TTrigger, TState> setBehaviors(List<Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>>> behaviors){
        this.behaviors = new LinkedList<>(behaviors);
        return this;
    }

    @Override
    public IStateBuilder<TTrigger, TState> addBehavior(IBehavior<TTrigger, TState> behavior){
        return addBehavior(state -> behavior);
    }

    @Override
    public IStateBuilder<TTrigger, TState> addBehavior(Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>> function){
        this.behaviors.addLast(function);
        return this;
    }


    @Override
    public IStateBuilder<TTrigger, TState> addBehaviorAsFirst(Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>> function){
        this.behaviors.addFirst(function);
        return this;
    }

    @Override
    public TState getState() {
        return state;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getIgnoreGlobalBehaviors() {
        return ignoreGlobalBehaviors;
    }

    @Override
    public IStateBuilder<TTrigger, TState> ignoreGlobalBehaviors() {
        ignoreGlobalBehaviors = true;
        return this;
    }

    @Override
    public StateMetadata getMetadata() {
        return metadata;
    }

    @Override
    public Map<TTrigger, TState> getStateMap() {
        return stateMap;
    }

    @Override
    public List<Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>>> getBehaviors() {
        return behaviors;
    }
}
