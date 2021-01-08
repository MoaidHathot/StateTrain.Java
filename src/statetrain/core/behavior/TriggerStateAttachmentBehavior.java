package statetrain.core.behavior;

import statetrain.core.TransitionArgs;
import statetrain.core.behavior.args.*;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TriggerStateAttachmentBehavior<TTrigger, TState> extends BaseBehavior<TTrigger, TState> {

    private final Map<TTrigger, List<Consumer<TransitionArgs<TTrigger, TState>>>> attachmentActions;

    public TriggerStateAttachmentBehavior(TState attachedState, Map<TTrigger, List<Consumer<TransitionArgs<TTrigger, TState>>>> attachmentActions) {
        super(attachedState);
        this.attachmentActions = attachmentActions;
    }

    @Override
    public void activating(BehaviorActivatingArgs<TTrigger, TState> args) {

    }

    @Override
    public void activated(BehaviorActivatedArgs<TTrigger, TState> args) {
    }

    @Override
    public void deactivating(BehaviorDeactivatingArgs<TTrigger, TState> args) {
        final var actions = attachmentActions.getOrDefault(args.getTrigger(), null);

        if(actions != null){
            for(final var action : actions){
                action.accept(args.getTransitionArgs());
            }
        }
    }

    @Override
    public void deactivated(BehaviorDeactivatedArgs<TTrigger, TState> args) {
    }

    @Override
    public TransitionResult<TState> triggerTransition(BehaviorTriggerTransitionArgs<TTrigger, TState> args) {
        return TransitionResult.noActionResult();
    }

    @Override
    public void close() {

    }
}
