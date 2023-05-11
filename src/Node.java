public class Node {
    private Node prevNode;
    private State state;
    private Action prevAction;
    private Action[] actions;

    public Node(Node prevNode, State state, Action prevAction) {
        this.prevNode = prevNode;
        this.state = state;
        this.prevAction = prevAction;
        this.actions = this.state.actions();
    }

    public Node[] expand(){
        Node[] nextNodes = new Node[this.actions.length];
        for (int i = 0 ; i < this.actions.length ; i++){
            nextNodes[i] = new Node(this, this.state.result(this.actions[i]), this.actions[i]);
        }
        return nextNodes;
    }

    public int heuristicValue(){
        int counterManhettenDistance = 0, actionManhettenDistance, i = this.state.getTilesNotInPlace();
        for (Action action: this.actions){
            actionManhettenDistance = action.isGoodMove();
            if (actionManhettenDistance == 0)
                continue;
            else
                counterManhettenDistance += actionManhettenDistance;
        }
        return counterManhettenDistance;
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
