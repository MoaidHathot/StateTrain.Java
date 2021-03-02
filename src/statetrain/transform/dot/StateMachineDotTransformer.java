package statetrain.transform.dot;

import statetrain.core.StateMachine;

public class StateMachineDotTransformer<TTrigger, TState> {

    private final StateMachine<TTrigger, TState> stateMachine;
    private GraphType graphType;

    public StateMachineDotTransformer(StateMachine<TTrigger, TState> stateMachine)
    {
        this.stateMachine = stateMachine;
    }

    public void transform(){
        final var machine = stateMachine.getStates();

        var builder = new StringBuilder();

        builder.append("digraph aic {\r\n");
//        builder.append("node [shape=plaintext];");

        for(final var state : machine.values()){
            for(final var trigger : state.getStateMap().entrySet()){
                builder.append(String.format("\t\"%s\" -> \"%s\" [label=\"%s\"];\r\n", state.getState(), trigger.getValue(), trigger.getKey()));
            }
        }

        builder.append(String.format("\t%s [style=filled, color=blue, fontcolor=white]\r\n", stateMachine.getInitialState()));
        builder.append(String.format("\t{ rank=min; %s; }\r\n", stateMachine.getInitialState()));

        builder.append("}");

        System.out.println(builder.toString());
    }
}
