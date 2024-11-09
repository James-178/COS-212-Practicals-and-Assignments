public class BTreeNode<T extends Comparable<T>> {
    public Comparable<T>[] nodeData;
    public BTreeNode<T>[] nodeChildren;
    public BTreeNode<T> parent;
    public int size;

   // @SuppressWarnings("unchecked")
    public BTreeNode(int size) {
        this.size = size;
        nodeData = (Comparable<T>[]) new Comparable[size];
        nodeChildren = new BTreeNode[size + 1];
        // parent = null;
    }

    public Comparable<T> getIndex(int i) {
        return nodeData[i];
    }

    public BTreeNode<T> ascend() {
        return parent;
    }

    public BTreeNode<T> descend(int i) {
        return nodeChildren[i];
    }

    public boolean isFull(){
        for (int i = 0; i < size - 1; i++) {
            if(nodeData[i] == null){
                return false;
            }
        }
        
        return true;
    }

    public void simpleInsert(T data){
        Comparable<T>[] temp = (Comparable<T>[]) new Comparable[size];
        System.arraycopy(nodeData, 0, temp, 0, size);
        nodeData = (Comparable<T>[]) new Comparable[size];
        int tempInd = 0;
        boolean ins = false;
        for (int i = 0; i < nodeData.length; i++) {
            if(temp[tempInd] != null){
                if(data.compareTo((T)temp[tempInd]) < 0 && ins == false){
                    nodeData[i] = data;
                    ins = true;
                }else{
                    nodeData[i] = temp[tempInd];
                    tempInd++;
                }
            }else{
                if(ins){
                    nodeData[i] = temp[tempInd];
                    tempInd++;
                }else{
                    nodeData[i] = data;
                    ins = true;
                }
            }
            
        }
    }

    public void simpleNodeInsert(BTreeNode<T> newNode){
        // System.out.println("new node: " + newNode.nodeData[0]);
        // System.out.println(this);
        // System.out.println(nodeChildren[0]);
        BTreeNode<T>[] temp = new BTreeNode[size+1];
        System.arraycopy(nodeChildren, 0, temp, 0, size + 1);
        nodeChildren = new BTreeNode[size+1];
        int tempInd = 0;
        boolean ins = false;
        for (int i = 0; i < nodeChildren.length; i++) {
            if(temp[i] != null){
                if(newNode != null)
                    if((newNode.nodeData[0]).compareTo((T)temp[tempInd].nodeData[0]) < 0 && ins == false){
                        nodeChildren[i] = newNode;
                        nodeChildren[i].parent = this;
                        ins = true;
                    }else{
                        nodeChildren[i] = temp[tempInd];
                        tempInd++;
                    }
                else{
                    nodeChildren[i] = temp[tempInd];
                    tempInd++;
                }
                        
            }else{
                if(ins){
                    nodeChildren[i] = temp[tempInd];
                    if(nodeChildren[i] != null){
                        nodeChildren[i].parent = this;
                    }
                    tempInd++;
                }else{
                    nodeChildren[i] = newNode;
                    if(nodeChildren[i] != null){
                        nodeChildren[i].parent = this;
                    }
                    
                    ins = true;
                }
            }
        }
    }

    /* -------------------------------------------------------------------------- */
    /* Helpers */
    /* -------------------------------------------------------------------------- */

    public String toString() {
        String out = "|";
        for (int i = 0; i < nodeData.length; i++) {
            if (nodeData[i] == null) {
                out += "null|";
            } else {
                out += nodeData[i].toString() + "|";
            }

        }
        return out;
    }

}
