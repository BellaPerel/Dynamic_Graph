import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {

    GraphNode root;

    public RootedTree(){
        this.root = null;
    }

    public GraphNode getRoot() {
        return root;
    }

    public void setRoot(GraphNode root) {
        this.root = root;
    }


    public void printByLayer(DataOutputStream out) throws IOException {
        int height=0;
        this.root.setDepthLayer(height);
        GraphNode firstNodeInQPrint = this.root;
        GraphNode lastNodeInQPrint = this.root;
        this.root.setBfs_layersPrintNextInQ(null);
        int currentKey = firstNodeInQPrint.getKey();
        String preorderString;
        while (firstNodeInQPrint != null){
            currentKey = firstNodeInQPrint.getKey();
            if(firstNodeInQPrint.getDepthLayer()> height){
                height++;
                out.writeBytes("\n");
                preorderString = "" + currentKey;
                out.writeBytes(preorderString);
            }
            else {
                if (firstNodeInQPrint == this.root){
                    preorderString = "" + currentKey;
                    out.writeBytes(preorderString);
                }
                else{
                    preorderString = "," + currentKey;
                    out.writeBytes(preorderString);
                }
            }
            GraphNode newChild = firstNodeInQPrint.getLeftChild();
            if (newChild != null){
                lastNodeInQPrint.setBfs_layersPrintNextInQ(newChild);
                newChild.setBfs_layersPrintNextInQ(null);
                lastNodeInQPrint = newChild;
                newChild.setDepthLayer(height+1);
                newChild = newChild.getRightSibling();
            }
            while(newChild != null){
                lastNodeInQPrint.setBfs_layersPrintNextInQ(newChild);
                newChild.setBfs_layersPrintNextInQ(null);
                lastNodeInQPrint = newChild;
                newChild.setDepthLayer(height+1);
                newChild = newChild.getRightSibling();
            }
            firstNodeInQPrint = firstNodeInQPrint.getBfs_layersPrintNextInQ();

        }
    }

    public void preorderPrint(DataOutputStream out) throws IOException {
        GraphNode currentNode = this.root;
        if (currentNode == null)
            return;
        int currentKey = currentNode.getKey();
        String preorderString = "" + currentKey;
        out.writeBytes(preorderString);
        if (currentNode.getLeftChild() == null)
            return;
        preorderPrint_aux(out, currentNode.getLeftChild());
    }

    public void preorderPrint_aux(DataOutputStream out, GraphNode currentNode) throws IOException {
        if (currentNode == null)
            return;
        else{
            int currentKey = currentNode.getKey();
            String toAddString = "," + currentKey;
            out.writeBytes(toAddString);
            GraphNode currentChild = currentNode.getLeftChild();
            GraphNode currntSibling = currentNode.getRightSibling();
            preorderPrint_aux(out, currentChild);
            preorderPrint_aux(out, currntSibling);
        }

    }
}
