public class BST<T extends Comparable<T>> {

    public BinaryNode<T> root;

    public BST() {
        root = null;
    }

    public void delete(T data) {

        root = deleteRec(root, data);
        
    }

    public boolean contains(T data) {
        BinaryNode<T> ptr = root;
        return search(data, ptr);
    }

    public void insert(T data) {
        if (contains(data)) {
            return;
        }
        if (root == null) {
            root = new BinaryNode<T>(data);
            return;
        }
        BinaryNode<T> ptr = root;
        insertRec(ptr, data, null);
    }

    

    public int getHeight() {
        if (root == null) {
            return 0;
        }

        BinaryNode<T> ptr = root;

        return getHeightRec(ptr);
    }

    public String printSearchPath(T data) {
        String str = "";
        if (root == null) {
            return "Null";
        }
        str += root.data;
        BinaryNode<T> ptr;
        if (data.compareTo(root.data) < 0) {
            ptr = root.left;
        }else{
            ptr = root.right;
        }
        return print(ptr, data, str);
    }

    public int getNumLeaves() {
        BinaryNode<T> ptr = root;
        return getNumLeavesRec(ptr);
    }

    

    public BST<T> extractBiggestSuperficiallyBalancedSubTree(){
        BinaryNode<T> ptr =root;
        return getLargestBalanced(ptr);
    }    

    public BinaryNode<T> getNode(T data) {
        if (!contains(data)) {
            return null;
        }
        BinaryNode<T> ptr = root;
        return getNodeRec(ptr, data);
    }


    public boolean isSuperficiallyBalanced() {
        if (root == null) {
            return true;
        }
        BinaryNode<T> ptr = root;
        int left = countNodes(ptr.left);
        int right = countNodes(ptr.right);
        return (left == right);
    }

    public BinaryNode<T> findMax() {
        if (root == null) {
            return null;
        }
        BinaryNode<T> ptr = root;
        return max(ptr);
    }

    public BinaryNode<T> findMin() {
        if (root == null) {
            return null;
        }
        BinaryNode<T> ptr = root;
        return min(ptr);
    }

    ///////////////

    private StringBuilder toString(BinaryNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != null) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.data.toString()).append("\n");
        if (node.left != null) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return root == null ? "Empty tree" : toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }


    
    private boolean search(T element, BinaryNode<T> ptr){
        if (ptr == null) {
            return false;
        }else if(element.compareTo(ptr.data) == 0){
            return true;
        }else if (element.compareTo(ptr.data) < 0) {
            return search(element, ptr.left);
        }else{
            return search(element, ptr.right);
        }
    }

    private String print(BinaryNode<T> ptr, T data, String str){
        if (ptr == null) {
            return (str + " -> Null"); 
        }

        int compare = data.compareTo(ptr.data);

        if (compare == 0) {
            return (str + " -> " + ptr.data);
        }
        else if (compare < 0) {
            str += " -> " + ptr.data;
            return print(ptr.left, data, str);
        }else{
            str += " -> " + ptr.data;
            return print(ptr.right, data, str);
        }
    }

    private BinaryNode<T> getNodeRec(BinaryNode<T> ptr, T data) {
        if (ptr.data.compareTo(data) == 1) {
            return ptr;
        }

        if (ptr.data.compareTo(data) > 0) {
            return getNodeRec(ptr.left, data);
        }else{
            return getNodeRec(ptr.right, data);
        }
    }

    private BinaryNode<T> max(BinaryNode<T> ptr){
        if (ptr.right == null) {
            return ptr;
        }
        return max(ptr.right);
    }

    private BinaryNode<T> min(BinaryNode<T> ptr){
        if (ptr == null) {
            return null;
        }
        if (ptr.left == null) {
            return ptr;
        }
        return min(ptr.left);
    }

    private void insertRec(BinaryNode<T> ptr, T data, BinaryNode<T> pre) {
        if (ptr == null) {
            if (pre.data.compareTo(data) > 0) {
                pre.left = new BinaryNode<T>(data);
            }
            else{
                pre.right = new BinaryNode<T>(data);
            }
            return;
        }
        pre = ptr;
        if (ptr.data.compareTo(data) > 0) {
            ptr = ptr.left;
        }else{
            ptr = ptr.right;
        }
        insertRec(ptr, data, pre);
    }

    private int getNumLeavesRec(BinaryNode<T> ptr) {
        if (ptr == null) {
            return 0;
        }
        if (ptr.left == null && ptr.right == null) {
            return 1;
        }
        else{
            return getNumLeavesRec(ptr.left) + getNumLeavesRec(ptr.right);
        }
    }

    private int getHeightRec(BinaryNode<T> ptr) {
        if (ptr == null) {
            return 0;
        }
        else{
            int left = getHeightRec(ptr.left);
            int right = getHeightRec(ptr.right);

            if (left > right) {
                return (left +1);
            }else{
                return (right + 1);
            }
        }
    }

    private int countNodes(BinaryNode<T> ptr) {
        if (ptr == null) {
            return 0;
        }else{
            int left = countNodes(ptr.left);
            int right = countNodes(ptr.right);

            return 1 + left + right;
        }
    }

    private BST<T> getLargestBalanced(BinaryNode<T> ptr) {
        if (ptr == null) {
            return new BST<>();
        }

        

        int left = countNodes(ptr.left);
        int right = countNodes(ptr.right);

        if (left == right) {
            BST<T> newTree = new BST<>();
            newTree.root = ptr;
            ptr.left = null;
            ptr.right = null;
            return newTree;
        }

        BST<T> leftTree = getLargestBalanced(ptr.left);
        BST<T> rightTree = getLargestBalanced(ptr.right);

        if (countNodes(leftTree.root) >= countNodes(rightTree.root)) {
            return leftTree;
        }else{
            return rightTree;
        }
    }

    private BinaryNode<T> deleteRec(BinaryNode<T> ptr, T data) {
        if (ptr == null) {
            return null;
        }

        if (data.compareTo(ptr.data) < 0) {
            ptr.left = deleteRec(ptr.left, data);
        }else if(data.compareTo(ptr.data) > 0){
            ptr.right = deleteRec(ptr.right, data);
        }else{
            if (ptr.left == null) {
                return ptr.right;
            }else if (ptr.right == null) {
                return ptr.left;
            }else{
                BinaryNode<T> temp = min(ptr.right);
                ptr.data = temp.data;
                ptr.right = deleteRec(ptr.right, temp.data);
            }
        }
        return ptr;
    }
}
