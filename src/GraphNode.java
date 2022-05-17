public class GraphNode {
    private final int nodeKey;
    private GraphEdge inEdge;
    private GraphEdge outEdge;
    private GraphNode nextNode;
    private GraphNode previousNode;
    private Boolean bfs_v_visited;
    private GraphNode bfs_v_parent;
    private GraphNode BFSNextNodeInQueue;
    private GraphNode leftChild;
    private GraphNode rightSibling;
    private int depthLayer;
    private GraphNode bfs_layersPrintNextInQ;
    private Boolean dfs_v_visited;
    private GraphNode dfs_v_parent;
    private GraphNode DFSNextNodeInQueue;

    public GraphNode(int key) {
        this.nodeKey = key;
        this.inEdge = null;
        this.outEdge = null;
        this.nextNode = null;
        this.previousNode = null;
    }

    public GraphNode getDFSNextNodeInQueue() {
        return DFSNextNodeInQueue;
    }

    public void setDFSNextNodeInQueue(GraphNode DFSNextNodeInQueue) {
        this.DFSNextNodeInQueue = DFSNextNodeInQueue;
    }

    public GraphNode getDfs_v_parent() {
        return dfs_v_parent;
    }

    public void setDfs_v_parent(GraphNode dfs_v_parent) {
        this.dfs_v_parent = dfs_v_parent;
    }

    public Boolean getDfs_v_visited() {
        return dfs_v_visited;
    }

    public void setDfs_v_visited(Boolean dfs_v_visited) {
        this.dfs_v_visited = dfs_v_visited;
    }



    public GraphNode getBfs_layersPrintNextInQ() {
        return bfs_layersPrintNextInQ;
    }

    public void setBfs_layersPrintNextInQ(GraphNode bfs_layersPrintNextInQ) {
        this.bfs_layersPrintNextInQ = bfs_layersPrintNextInQ;
    }

    public int getDepthLayer() {
        return depthLayer;
    }

    public void setDepthLayer(int depthLayer) {
        this.depthLayer = depthLayer;
    }

    public void initializeForBFS(){
        this.bfs_v_visited = false;
        this.bfs_v_parent = null;
        this.BFSNextNodeInQueue = null;
        this.leftChild = null;
        this.rightSibling = null;
    }

    public GraphNode getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(GraphNode rightSibling) {
        this.rightSibling = rightSibling;
    }

    public GraphNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(GraphNode leftChild) {
        this.leftChild = leftChild;
    }

    public GraphNode getBFSNextNodeInQueue() {
        return BFSNextNodeInQueue;
    }

    public void setBFSNextNodeInQueue(GraphNode BFSNextNodeInQueue) {
        this.BFSNextNodeInQueue = BFSNextNodeInQueue;
    }


    public Boolean getBfs_v_visited() {
        return bfs_v_visited;
    }

    public void setBfs_v_visited(Boolean bfs_v_visited) {
        this.bfs_v_visited = bfs_v_visited;
    }

    public GraphNode getBfs_v_parent() {
        return bfs_v_parent;
    }

    public void setBfs_v_parent(GraphNode bfs_v_parent) {
        this.bfs_v_parent = bfs_v_parent;
    }



    public GraphNode getNextNode(){
        return this.nextNode;
    }

    public GraphNode getPrevNode(){
        return this.previousNode;
    }

    public void setNextNode(GraphNode next){
        this.nextNode = next;
    }

    public void setPrevNode(GraphNode prev){
        this.previousNode = prev;
    }

    public GraphEdge getInEdgeList(){
        return this.inEdge;
    }

    public GraphEdge getOutEdgeList(){
        return this.outEdge;
    }

    public void setInEdge(GraphEdge newInEdge) {
        if (newInEdge != null){
            if (this.inEdge != null){
                newInEdge.setNextToEdge(this.inEdge);
                this.inEdge.setPrevToEdge(newInEdge);
            }

        }
        this.inEdge = newInEdge;
    }

    public void setOutEdge(GraphEdge newOutEdge) {
        if (newOutEdge != null){
            if (this.outEdge != null){
                newOutEdge.setNextFromEdge(this.outEdge);
                this.outEdge.setPrevFromEdge(newOutEdge);
            }
        }
        this.outEdge = newOutEdge;
    }

    public void updateForInDeleteEdge(){
        GraphEdge updatedStartEdge = this.inEdge.getNextToEdge();
        this.inEdge = updatedStartEdge;
    }

    public void updateForOutDeleteEdge(){
        GraphEdge updatedStartEdge = this.outEdge.getNextFromEdge();
        this.outEdge = updatedStartEdge;
    }

    public int getOutDegree(){
        int outDegree = 0;
        if (this.outEdge == null){
            return outDegree;
        }
        outDegree++;
        GraphEdge nextOutEdge = this.outEdge.getNextFromEdge();
        while (nextOutEdge != null){
            outDegree++;
            nextOutEdge = nextOutEdge.getNextFromEdge();
        }
        return outDegree;
    }

    public int getInDegree(){
        int inDegree = 0;
        if (this.inEdge == null){
            return inDegree;
        }
        inDegree++;
        GraphEdge nextInEdge = this.inEdge.getNextToEdge();
        while (nextInEdge != null){
            inDegree++;
            nextInEdge = nextInEdge.getNextToEdge();
        }
        return inDegree;
    }

    public int getKey(){
        return this.nodeKey;
    }


}

