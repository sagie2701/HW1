public class Node {
    private Node prevNode;
    private State state;
    private Action prevAction;

    public Node(Node prevNode, State state, Action prevAction) {
        this.prevNode = prevNode;
        this.state = state;
        this.prevAction = prevAction;
    }

    public Node[] expand(){
        Action[] actions = this.state.actions();
        Node[] nextNodes = new Node[actions.length];
        for (int i = 0 ; i < actions.length ; i++){
            nextNodes[i] = new Node(this, this.state.result(actions[i]), actions[i]);
        }
        return nextNodes;
    }

    public int heuristicValue(){
        return 0;
    }

    public Action getAction(){
        return this.prevAction;
    }

    public Node getParent(){
        return this.prevNode;
    }

    public State getState(){
        return this.state;
    }
}
