public class Node {
    /**
     * Node class -- a vertex in the game
     * @param prevNode -- the previous Node
     * @param state -- an object with the curren state of the game
     * @param prevAction -- the previous action that committed to arrive to this node
     * @param actions -- an array with all possible actions to commit from the node
     * @param actionCommitted -- the previous action number (in actions array) that committed to arrive to this node
     * @param placeInChane -- place in the solution of the node
     */
    private Node prevNode;
    private State state;
    private Action prevAction;
    private Action[] actions;
    private int actionCommitted;
    private int placeInChane;

    /**
     * crate a new node
     * @param prevNode -- previous node in chane
     * @param state -- current state of the new node
     * @param prevAction -- previous state of the new node
     */
    public Node(Node prevNode, State state, Action prevAction, int actionCommitted, int placeInChane) {
        this.prevNode = prevNode;
        this.state = state;
        this.prevAction = prevAction;
        this.actions = this.state.actions();
        this.actionCommitted = actionCommitted;
        this.placeInChane = placeInChane;
    }

    /**
     * crate all possible next nodes from curren node
     * @return -- an array of nodes with all possible next nodes
     */
    public Node[] expand(){
        Node[] nextNodes = new Node[this.actions.length];
        for (int i = 0 ; i < this.actions.length ; i++){
            nextNodes[i] = new Node(this, this.state.result(this.actions[i]), this.actions[i], i ,
                    this.placeInChane++);
        }
        return nextNodes;
    }

    /**
     * calculate the heuristic value of a node by manhetten distance of his possible actions
     * @return -- heuristic value of a node
     */
    public int heuristicValue(){
        if (this.state.isGoal())
            return 0;
        int counterManhettenDistance = 0;
        for (Action action: this.actions){
//            if (this.prevNode != null && !(this.prevNode.getActions()[this.actionCommitted].getTileToMove().getValue()
//                    == action.getTileToMove().getValue()))
                counterManhettenDistance += action.isGoodMove();
        }
        return counterManhettenDistance * this.placeInChane + this.state.getEmptyTileDistance();
    }

//    public int heuristicValue(){
//        int counterManhettenDistance = this.heuristicValueNode();
//        for (Node node: this.expand()){
//            if (node.heuristicValueNode() > counterManhettenDistance)
//                counterManhettenDistance = node.heuristicValueNode();
//        }
//        return counterManhettenDistance;
//    }

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
