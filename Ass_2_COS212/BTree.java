public class BTree<T extends Comparable<T>> {
    public BTreeNode<T> root;
    public int m;

    public BTree(int m) {
        this.m = m;
        this.root = null;
    }

    public void insert(T data) {
        if(root == null){
            root = new BTreeNode<>(m);
            root.parent = null;
            root.nodeData[0] = data;
            //root.nodeChildren = null;
            return;
        }
        insertRec(root, root.parent, data, 0);
    }

    private void insertRec(BTreeNode<T> ptr, BTreeNode<T> parent, T data, int i) {
        if(ptr.nodeChildren[0] == null){
            if(ptr.isFull() == false){
                ptr.simpleInsert(data);
            }else{
                BTreeNode<T> newNode = new BTreeNode<>(m);
                ptr.simpleInsert(data);
                
                T temp = (T) ptr.nodeData[ptr.size/2];
                
                int k = 0;
                for (int j = ptr.size/2 + 1; j < ptr.size; j++) {
                    newNode.nodeData[k] = ptr.nodeData[j];
                    ptr.nodeData[j] = null; 
                    k++;
                }
                

                ptr.nodeData[ptr.size/2] = null;
                if(parent == null){
                    BTreeNode<T> tempPtr = ptr;
                    ptr = new BTreeNode<>(ptr.size);
                    ptr.nodeData[0] = temp;
                    ptr.nodeChildren[0] = tempPtr;
                    ptr.nodeChildren[0].parent = ptr;
                    
                    ptr.nodeChildren[1] = newNode;
                    ptr.nodeChildren[1].parent = ptr;

                    root = ptr;
                }else{
                    if(parent.isFull() == false){
                        parent.simpleInsert(temp);
                        parent.simpleNodeInsert(newNode);
                    }else{
                        parent.simpleNodeInsert(newNode);
                        insertUp(newNode, ptr, parent, temp);
                    }   
                }
            }
        }else{
            if(data.compareTo((T)ptr.nodeData[i]) <= 0 && i == 0){
                insertRec(ptr.nodeChildren[i], ptr, data, i);
            }else if(data.compareTo((T)ptr.nodeData[i]) >= 0 && i == ptr.size-1){
                insertRec(ptr.nodeChildren[i + 1], ptr, data, 0);
            }else if(data.compareTo((T)ptr.nodeData[i]) >= 0 && ptr.nodeData[i+1] == null){
                insertRec(ptr.nodeChildren[i + 1], ptr, data, 0);
            }else if(data.compareTo((T)ptr.nodeData[i]) >= 0 && data.compareTo((T)ptr.nodeData[i+1]) < 0){
                insertRec(ptr.nodeChildren[i + 1], ptr, data, 0);
            }else{
                insertRec(ptr, parent, data, i+1);
            }
        }
    }

    private void insertUp(BTreeNode<T> newNode, BTreeNode<T> ptr, BTreeNode<T> parent, T data) {
        BTreeNode<T> newParentNode = new BTreeNode<>(m);
        parent.simpleInsert(data);
        T temp = (T) parent.nodeData[ptr.size/2];
        int k = 0;
        for (int j = parent.size/2 + 1; j < parent.size; j++) {
            newParentNode.nodeData[k] = parent.nodeData[j];
            newParentNode.simpleNodeInsert(parent.nodeChildren[j]);
            parent.nodeChildren[j] = null;
            parent.nodeData[j] = null; 
            k++;
        }
    
        newParentNode.simpleNodeInsert(parent.nodeChildren[parent.size]);
        parent.nodeChildren[parent.size] = null;        
        
        parent.nodeData[parent.size/2] = null;

        if(parent.parent == null){
            BTreeNode<T> tempPtr = parent;
            parent = new BTreeNode<>(parent.size);
            
            parent.nodeData[0] = temp;

            parent.nodeChildren[0] = tempPtr;
            parent.nodeChildren[0].parent = parent;

            parent.nodeChildren[1] = newParentNode;
            parent.nodeChildren[1].parent = parent;
            root = parent;
        }else if(parent.parent.isFull() == false){
            parent.parent.simpleInsert(temp);
            parent.parent.simpleNodeInsert(newParentNode);

        }else{
            parent.parent.simpleNodeInsert(newParentNode);
            insertUp(newParentNode, parent, parent.parent, temp);
        }
       
        
    }

    public String printPath(T key) {
        if(root == null){
            return "Null";
        }

        String s = "";

        return printRec(root, s, key,0); 
    }

    private String printRec(BTreeNode<T> ptr, String s, T key, int i) {
        String arrow = (s.equals("")) ? "":" -> ";

        if(ptr == null){
            return (s + arrow + "NULL");
        }

        if(key.compareTo((T)ptr.nodeData[i]) == 0){
            return (s + arrow + ptr.nodeData[i].toString());
        }
        
        if(key.compareTo((T)ptr.nodeData[i]) <= 0 && i == 0){
            return printRec(ptr.nodeChildren[i], s + arrow + ptr.nodeData[i].toString(), key, 0);
        }else if(key.compareTo((T)ptr.nodeData[i]) >= 0 && i == ptr.size-1){
             return printRec(ptr.nodeChildren[i+1], s + arrow + ptr.nodeData[i].toString(), key, 0);
        }else if(key.compareTo((T)ptr.nodeData[i]) >= 0 && ptr.nodeData[i+1] == null){
             return printRec(ptr.nodeChildren[i+1], s + arrow + ptr.nodeData[i].toString(), key, 0);
        }else if(key.compareTo((T)ptr.nodeData[i]) >= 0 && key.compareTo((T)ptr.nodeData[i+1]) < 0){
             return printRec(ptr.nodeChildren[i+1], s + arrow + ptr.nodeData[i].toString(), key, 0);
        }else{
            return printRec(ptr, s + arrow + ptr.nodeData[i].toString(), key, i + 1);
        }
    }

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I tried to make it pretty. */
    /* -------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------- */
    /* Also we may test against it */
    /* -------------------------------------------------------------------------- */

    

    @Override
    public String toString() {
        if (root == null) {
            return "The B-Tree is empty";
        }
        StringBuilder builder = new StringBuilder();
        buildString(root, builder, "", true);
        return builder.toString();
    }

    private void buildString(BTreeNode<T> node, StringBuilder builder, String prefix, boolean isTail) {
        if (node == null)
            return;

        builder.append(prefix).append(isTail ? "└── " : "├── ");
        for (int i = 0; i < node.nodeData.length; i++) {
            if (node.nodeData[i] != null) {
                builder.append(node.nodeData[i]);
                if (i < node.nodeData.length - 1 && node.nodeData[i + 1] != null) {
                    builder.append(", ");
                }
            }

        }
        if (node.parent != null)
            builder.append("\t(p:" + node.parent.nodeData[0].toString() + ")");
        builder.append("\n");

        int numberOfChildren = m;
        for (int i = 0; i < numberOfChildren; i++) {

            BTreeNode<T> child = node.descend(i);
            buildString(child, builder, prefix + (isTail ? "    " : "│   "), i == numberOfChildren - 1);
        }
    }
}
