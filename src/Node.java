public class Node {

    private Node prevNode;
    private State state;
    private Action prevAction;
    private Action[] actions;

    private int actionCommitted;

    /**
     * crate a new node
     * @param prevNode -- previous node in chane
     * @param state -- current state of the new node
     * @param prevAction -- previous state of the new node
     */
    public Node(Node prevNode, State state, Action prevAction, int actionCommitted) {
        this.prevNode = prevNode;
        this.state = state;
        this.prevAction = prevAction;
        this.actions = this.state.actions();
        this.actionCommitted = actionCommitted;
    }

    public Node[] expand(){
        Node[] nextNodes = new Node[this.actions.length];
        for (int i = 0 ; i < this.actions.length ; i++){
            nextNodes[i] = new Node(this, this.state.result(this.actions[i]), this.actions[i], i);
        }
        return nextNodes;
    }

    /**
     * calculate the heuristic value of a node by manhetten distance of his possible actions
     * @return -- heuristic value of a node
     */
    public int heuristicValue(){
        int counterManhettenDistance = 0;
        for (Action action: this.actions){
            if (this.prevNode != null && !(this.prevNode.getActions()[this.actionCommitted].getTileToMove().getValue()
                    == action.getTileToMove().getValue()))
                counterManhettenDistance += action.isGoodMove();
        }
        return counterManhettenDistance;
    }

    /**
     * @return -- the node previous Action
     */
    public Action getAction(){
        return this.prevAction;
    }

    /**
     * @return -- the node possible actions
     */
    public Action[] getActions(){
        return this.actions;
    }

    /**
     * @return -- the node previous Node (parent)
     */
    public Node getParent(){
        return this.prevNode;
    }

    /**
     * @return -- the node State (object with game board object)
     */
    public State getState(){
        return this.state;
    }

}
