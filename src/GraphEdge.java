public class GraphEdge {
    private GraphNode toNode;
    private GraphNode fromNode;
    private GraphEdge nextToEdge;
    private GraphEdge prevToEdge;
    private GraphEdge nextFromEdge;
    private GraphEdge prevFromEdge;

    public GraphEdge(GraphNode from, GraphNode to) {
        this.toNode = to;
        this.fromNode = from;
        this.nextToEdge = null;
        this.nextFromEdge = null;
        this.prevToEdge = null;
        this.prevFromEdge = null;
    }

    public GraphNode getToNode() {
        return toNode;
    }

    public GraphNode getFromNode() {
        return fromNode;
    }

    public GraphEdge getNextToEdge() {
        return nextToEdge;
    }

    public void setNextToEdge(GraphEdge nextToEdge) {
        this.nextToEdge = nextToEdge;
    }

    public GraphEdge getNextFromEdge() {
        return nextFromEdge;
    }

    public void setNextFromEdge(GraphEdge nextFromEdge) {
        this.nextFromEdge = nextFromEdge;
    }

    public GraphEdge getPrevToEdge() {
        return prevToEdge;
    }

    public void setPrevToEdge(GraphEdge prevToEdge) {
        this.prevToEdge = prevToEdge;
    }

    public GraphEdge getPrevFromEdge() {
        return prevFromEdge;
    }

    public void setPrevFromEdge(GraphEdge prevFromEdge) {
        this.prevFromEdge = prevFromEdge;
    }
}
