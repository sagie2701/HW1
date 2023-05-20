public class Node {
    /**
     * Node class -- a vertex in the game
     * @param prevNode -- the previous Node
     * @param state -- an object with the curren state of the game
     * @param prevAction -- the previous action that committed to arrive to this node
     * @param actions -- an array with all possible actions to commit from the node
     * @param placeInChane -- the place in chane of the current Node
     */
    private Node prevNode;
    private State state;
    private Action prevAction;
    private Action[] actions;
    private int placeInChane;

    /**
     * crate a new node
     * @param prevNode -- previous node in chane
     * @param state -- current state of the new node
     * @param prevAction -- previous state of the new node
     */
    public Node(Node prevNode, State state, Action prevAction, int placeInChane) {
        this.prevNode = prevNode;
        this.state = state;
        this.prevAction = prevAction;
        this.actions = this.state.actions();
        this.placeInChane = placeInChane;
    }

    /**
     * crate all possible next nodes from curren node
     * @return -- an array of nodes with all possible next nodes
     */
    public Node[] expand(){
        Node[] nextNodes = new Node[this.actions.length];
        for (int i = 0 ; i < this.actions.length ; i++){
            nextNodes[i] = new Node(this, this.state.result(this.actions[i]), this.actions[i],
                    this.placeInChane + 1);
        }
        return nextNodes;
    }

    /**
     * calculate the heuristic value of a node by manhattan distance the Tiles and Node's place in chane
     * @return -- heuristic value of a node
     */

    public int heuristicValue(){
        return this.state.getEmptyTilesDistance() + this.placeInChane / 5;
    }


    /**
     * @return -- the node previous Action
     */
    public Action getAction(){
        return this.prevAction;
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
