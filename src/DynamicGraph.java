public class DynamicGraph {

    private GraphNode nodes;
    private static int n; // num of nodes in the graph
    private static int m; // num of edges in the graph
    private GraphNode DFSFirstNodeInQ;

    public DynamicGraph(){
        this.nodes = null;
        n = 0;
        m = 0;
    }

    public GraphNode insertNode(int nodeKey){
        GraphNode newNode = new GraphNode(nodeKey);
        if (this.nodes != null){
            newNode.setNextNode(this.nodes);
            this.nodes.setPrevNode(newNode);
        }
        this.nodes = newNode;
        this.n = this.n + 1;
        return newNode;
    }

    public GraphNode getDFSFirstNodeInQ() {
        return DFSFirstNodeInQ;
    }

    public void setDFSFirstNodeInQ(GraphNode DFSFirstNodeInQ) {
        this.DFSFirstNodeInQ = DFSFirstNodeInQ;
    }

    public void deleteNode(GraphNode node){
        if (node == null)
            return;
        if ((node.getInEdgeList() != null) || (node.getOutEdgeList() != null)){
            return;
        }
        GraphNode prevNode = node.getPrevNode();
        GraphNode nextNode = node.getNextNode();
        if (nextNode == null){
            if (prevNode == null){
                if (node == this.nodes)
                    this.nodes = null;
            }
            else{
                prevNode.setNextNode(null);
            }
        }
        else{
            if (prevNode == null){
                nextNode.setPrevNode(null);
                this.nodes = nextNode;
            }
            else{
                nextNode.setPrevNode(prevNode);
                prevNode.setNextNode(nextNode);
            }
        }
        this.n = this.n - 1;
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        GraphEdge newEdge = new GraphEdge(from,to);
        from.setOutEdge(newEdge);
        to.setInEdge(newEdge);
        this.m = this.m + 1;
        return newEdge;
    }

    public void deleteEdge(GraphEdge edge){
        if (edge == null){
            return;
        }
        GraphEdge theNextInEdge = edge.getNextToEdge();
        GraphEdge thePrevInEdge = edge.getPrevToEdge();
        GraphNode nodeToUpdate = edge.getToNode();
        if (thePrevInEdge == null){
            if (theNextInEdge == null){
                nodeToUpdate.setInEdge(null);
            }
            else{
                nodeToUpdate.updateForInDeleteEdge();
                theNextInEdge.setPrevToEdge(null);
            }
        }
        else{
            if (theNextInEdge == null){
                thePrevInEdge.setNextToEdge(null);
            }
            else{
                theNextInEdge.setPrevToEdge(thePrevInEdge);
                thePrevInEdge.setNextToEdge(theNextInEdge);
            }
        }
        GraphEdge theNextOutEdge = edge.getNextFromEdge();
        GraphEdge thePrevOutEdge = edge.getPrevFromEdge();
        GraphNode nodeFromUpdate = edge.getFromNode();
        if (thePrevOutEdge == null){
            if (theNextOutEdge == null){
                nodeFromUpdate.setOutEdge(null);
            }
            else{
                nodeFromUpdate.updateForOutDeleteEdge();
                theNextOutEdge.setPrevFromEdge(null);
            }
        }
        else{
            if (theNextOutEdge == null){
                thePrevOutEdge.setNextFromEdge(null);
            }
            else{
                theNextOutEdge.setPrevFromEdge(thePrevOutEdge);
                thePrevOutEdge.setNextFromEdge(theNextOutEdge);
            }
        }
        this.m = this.m - 1;
    }

    public RootedTree bfs(GraphNode source){
        RootedTree tree = new RootedTree();
        tree.setRoot(source);
        if (source == null){
            return tree;
        }
        bfs_initialization(source);

        GraphNode workingNode = source;
        GraphNode firstNodeInQueue = workingNode;
        GraphNode lastNodeInQueue = workingNode;
        workingNode.setBfs_v_visited(true);
        while (firstNodeInQueue != null){
            GraphEdge currentEdge = workingNode.getOutEdgeList();
            if (currentEdge == null){
                workingNode.setBfs_v_visited(true);
                firstNodeInQueue = firstNodeInQueue.getBFSNextNodeInQueue();
                workingNode = firstNodeInQueue;
                continue;
            }
            GraphNode currentToNode = currentEdge.getToNode();
            while (currentToNode.getBfs_v_visited()){
                currentEdge = currentEdge.getNextFromEdge();
                if (currentEdge == null){
                    workingNode.setBfs_v_visited(true);
                    firstNodeInQueue = firstNodeInQueue.getBFSNextNodeInQueue();
                    workingNode = firstNodeInQueue;
                    break;
                }
                currentToNode = currentEdge.getToNode();
            }
            if (firstNodeInQueue == null || currentEdge == null){
                continue;
            }
            workingNode.setLeftChild(currentToNode);
            currentToNode.setBfs_v_parent(workingNode);
            lastNodeInQueue.setBFSNextNodeInQueue(currentToNode);
            currentToNode.setBfs_v_visited(true);
            lastNodeInQueue = currentToNode;
            currentEdge = currentEdge.getNextFromEdge();
            while (currentEdge != null){
                GraphNode currentToNodeNew = currentEdge.getToNode();
                if (currentToNodeNew.getBfs_v_visited()){
                    currentEdge = currentEdge.getNextFromEdge();
                    continue;
                }
                currentToNode.setRightSibling(currentToNodeNew);
                currentToNodeNew.setBfs_v_parent(workingNode);
                lastNodeInQueue.setBFSNextNodeInQueue(currentToNodeNew);
                currentToNodeNew.setBfs_v_visited(true);
                lastNodeInQueue = currentToNodeNew;
                currentEdge = currentEdge.getNextFromEdge();
                currentToNode = currentToNodeNew;
            }
            workingNode.setBfs_v_visited(true);

            firstNodeInQueue = firstNodeInQueue.getBFSNextNodeInQueue();
            workingNode = firstNodeInQueue;
        }
        return tree;
    }

    public void bfs_initialization(GraphNode source){
        GraphNode currentNode = this.nodes;
        while(currentNode != null){
            if (currentNode != source){
                currentNode.initializeForBFS();
                currentNode = currentNode.getNextNode();
            }
            else{
                currentNode = currentNode.getNextNode();
            }
        }
        source.setBfs_v_visited(false);
        source.setBfs_v_parent(null);
        source.setRightSibling(null);
        source.setLeftChild(null);
        source.setBFSNextNodeInQueue(null);
    }

    public void first_dfs(GraphNode source){
        while (source != null){
            if (source.getDfs_v_visited() == false){
                first_dfs_visit(source);
            }
            source = source.getNextNode();

        }

    }

    public void first_dfs_visit(GraphNode currentNode) {
        if (currentNode == null){
            return;
        }
        GraphEdge currentEdge = currentNode.getOutEdgeList();
        currentNode.setDfs_v_visited(true);
        while (currentEdge != null){
            if (currentEdge.getToNode().getDfs_v_visited() == false){
                GraphNode newCurrentToNode = currentEdge.getToNode();
                first_dfs_visit(newCurrentToNode);

            }
            currentEdge = currentEdge.getNextFromEdge();
        }
        currentNode.setDFSNextNodeInQueue(this.DFSFirstNodeInQ);
        this.setDFSFirstNodeInQ(currentNode);
    }

    public void second_dfs(GraphNode currentNode){
        if (currentNode == null){
            return;
        }
        GraphEdge currentEdge = currentNode.getInEdgeList();
        currentNode.setDfs_v_visited(true);
        GraphNode lastChild = null;
        while (currentEdge != null){
            if (currentEdge.getFromNode().getDfs_v_visited() == false){
                GraphNode newCurrentFromNode = currentEdge.getFromNode();
                if (currentNode.getLeftChild() == null){
                    newCurrentFromNode.setDfs_v_parent(currentNode);
                    currentNode.setLeftChild(newCurrentFromNode);
                    lastChild = newCurrentFromNode;
                }
                else{
                    newCurrentFromNode.setDfs_v_parent(currentNode);
                    if (lastChild != null){
                        lastChild.setRightSibling(newCurrentFromNode);
                    }
                    lastChild = newCurrentFromNode;
                }
                second_dfs(newCurrentFromNode);
            }
            currentEdge = currentEdge.getNextToEdge();
        }
    }




    public void initializeNodesForDfs(GraphNode currentNodeinitialize){
        this.setDFSFirstNodeInQ(null);
        while (currentNodeinitialize != null){
            currentNodeinitialize.setDfs_v_parent(null);
            currentNodeinitialize.setDfs_v_visited(false);
            currentNodeinitialize.setLeftChild(null);
            currentNodeinitialize.setRightSibling(null);
            currentNodeinitialize.setDFSNextNodeInQueue(null);
            currentNodeinitialize = currentNodeinitialize.getNextNode();
        }
    }

    public void initializeNodesForDfs2(GraphNode currentNodeinitialize){
        while (currentNodeinitialize != null){
            currentNodeinitialize.setDfs_v_visited(false);
            currentNodeinitialize = currentNodeinitialize.getNextNode();
        }
    }

    public RootedTree scc(){
        GraphNode currentNode = this.nodes;
        initializeNodesForDfs(currentNode);
        RootedTree tree = new RootedTree();
        GraphNode source = new GraphNode(0);
        tree.setRoot(source);
        first_dfs(currentNode);
        GraphNode currentNode2 = this.DFSFirstNodeInQ;
        initializeNodesForDfs2(currentNode);
        GraphNode lastSibling = null;
        while (currentNode2 != null){
            if (currentNode2.getDfs_v_visited() == false){
                second_dfs(currentNode2);
                if (lastSibling == null){
                    lastSibling = currentNode2;
                    source.setLeftChild(currentNode2);
                    currentNode2.setDfs_v_parent(source);
                }
                else{
                    lastSibling.setRightSibling(currentNode2);
                    currentNode2.setDfs_v_parent(source);
                    lastSibling = currentNode2;
                }
            }
            currentNode2 = currentNode2.getDFSNextNodeInQueue();
        }
        return tree;
    }



}
