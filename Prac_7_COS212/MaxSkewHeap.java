public class MaxSkewHeap {
    Node root;

    @Override
    public String toString() {
        return (root == null ? "Empty Tree" : toString(root, "", true));
    }

    public String toString(Node node, String prefix, boolean end) {
        String res = "";
        if (node.right != null) {
            res += toString(node.right, prefix + (end ? "│   " : "    "), false);
        }
        res += prefix + (end ? "└── " : "┌── ") + node.toString() + "\n";
        if (node.left != null) {
            res += toString(node.left, prefix + (end ? "    " : "│   "), true);
        }
        return res;
    }

    public String toStringOneLine() {
        return (root == null ? "{}" : toStringOneLine(root));
    }

    public String toStringOneLine(Node ptr) {
        if (ptr == null) {
            return "{}";
        }
        return "{" + ptr.toString() + toStringOneLine(ptr.left) + toStringOneLine(ptr.right) + "}";
    }

    public MaxSkewHeap() {
        root = null;
    }

    public MaxSkewHeap(String input) {
        if(input.equals("{}")){
            root = null;
            return;
        }

        root = null;
        addNodes(root, input, null, 'n');


    }

    private void addNodes(Node ptr, String input, Node pre, char check) {
        if (input.equals("{}") ) {
            return;
        }
        String temp = input.substring(1);
        int d = Integer.parseInt(temp.substring(0, temp.indexOf("{")));

        if (check == 'n') {
            root = new Node(d);
            ptr = root;
        }else if (check == 'l') {
            pre.left = new Node(d);
            ptr = pre.left;
        }else if (check == 'r'){
            pre.right = new Node(d);
            ptr = pre.right;
        }
        
        String lr = temp.substring(temp.indexOf("{"), temp.length()-1);
        int ind = 1;//0, -1, 1
        int i = 1;//count how many until end of child
        while (ind != 0) {
            if (lr.charAt(i) == '{') {
                ind++;
            }else if (lr.charAt(i) == '}') {
                ind--;
            }
            i++;
        }

        String left = lr.substring(0, i);
        String right = lr.substring(i);

        addNodes(ptr.left, left, ptr, 'l');
        addNodes(ptr.right, right, ptr, 'r');

    }

    public void insert(int data) {
        if(root == null){
            root = new Node(data);
            return;
        }

        if(search(data) != null){
            return;
        }

        Node newNode = new Node(data);

        if(data > root.data){
            Node temp = root;
            root = newNode;
            root.left = temp;
            return;
        }

        root = merge(root, newNode);

    }

    private Node merge(Node ptr, Node newNode) {
        if(newNode == null){
            return ptr;
        }
        if(ptr == null){
            return newNode;
        }

        if(ptr.data > newNode.data){
            Node temp = ptr.left;
            ptr.left = merge(ptr.right, newNode);
            ptr.right = temp;
            return ptr;
        }else{
            Node temp = newNode.left;
            newNode.left = merge(newNode.right, ptr);
            newNode.right = temp;
            return newNode;
        }

    }

    public void remove(int data) {//still do
        if(root == null){
            return;
        }
        
        if(search(data) == null){
            return;
        }

        root = removeRec(root, data);


    }

    private Node removeRec(Node ptr, int val) {
        if(ptr == null){
            return null;
        }

        if(ptr.data == val){
            if(ptr.left == null && ptr.right == null){
                return null;
            }

            if(ptr.left == null){
                return ptr.right;
            }

            if(ptr.right == null){
                return ptr.left;
            }

            return merge(ptr.left, ptr.right);
        }

        ptr.right = removeRec(ptr.right, val);
        ptr.left = removeRec(ptr.left, val);

        return ptr;
    }

    public Node search(int value) {
        return searchRec(root, value);
    }

    private Node searchRec(Node ptr, int val){
        if(ptr == null || ptr.data == val){
            return ptr;
        }

        Node r = searchRec(ptr.right, val);
        if(r != null){
            return r;
        }

        if(ptr.data < val){
            return null;
        }

        return searchRec(ptr.left, val);
    }

    public String searchPath(int value) {
        if(root == null){
            return "";
        }

        String s = searchPathRec(root, value, "");
        if(s.substring(s.length()-2,s.length()).equals("->")){
            s = s.substring(0, s.length()-2);
        }
        return s;

    }

    private String searchPathRec(Node ptr, int val, String returnStr){//might be null ptr
        if(ptr == null){
            return returnStr;
        }

        if(ptr.data == val){
            return returnStr + "[" + String.valueOf(val) + "]";
        }

        returnStr += ptr.toString();

        returnStr += "->";

        

        if(ptr.data < val){//null ptr here
            return returnStr;
        }

        returnStr = searchPathRec(ptr.right, val, returnStr);

        return searchPathRec(ptr.left, val, returnStr);

    }

    public boolean isLeftist() {
        return false;
    }

    public boolean isRightist() {
        return true;
    }
}
