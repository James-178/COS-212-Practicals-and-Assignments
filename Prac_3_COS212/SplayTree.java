public class SplayTree {
    public Node root;
    /*
     * The functions below this line was given
     */

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
        return (root == null ? "Empty Tree" : "{" + toStringOneLine(root) + "}");
    }

    public String toStringOneLine(Node node) {
        return node.toString()
                + (node.left == null ? "{}" : "{" + toStringOneLine(node.left) + "}")
                + (node.right == null ? "{}" : "{" + toStringOneLine(node.right) + "}");
    }

    public SplayTree() {
        root = null;
    }

    /*
     * The functions above this line was given
     */

    public SplayTree(String input) {
        if (input.equals("Empty Tree")  || input.equals("{}")) {
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

        int studentNum = Integer.parseInt(input.substring(3, input.indexOf(":")));
        String sm = input.substring(input.indexOf(":")+1, input.indexOf("%"));
        Integer studentMark;
        if (sm.equals("null")) {
            studentMark = null;
        }else{
            studentMark = Integer.parseInt(input.substring(input.indexOf(":")+1, input.indexOf("%")));
        }
        

        if (check == 'n') {
            root = new Node(studentNum, studentMark);
            ptr = root;
        }else if (check == 'l') {
            pre.left = new Node(studentNum,studentMark);
            ptr = pre.left;
        }else if (check == 'r'){
            pre.right = new Node(studentNum, studentMark);
            ptr = pre.right;
        }
        

        String lr = input.substring(input.indexOf("]")+1, input.length()-1);
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

    public Node access(int studentNumber) {
        return access(studentNumber, null);
    }

    public Node access(int studentNumber, Integer mark) {
        if (root == null) {
            root = new Node(studentNumber, mark);
            return root;
        }
        if (root.studentNumber == studentNumber) {
            if (mark != null) {
                root.mark = mark;
            }
            return root;
        }
        
        Node newNode = new Node(studentNumber, mark);

        if (contains(root, studentNumber) == false) {
            
            insert(root,newNode, null);
            
        }
        root = splay(root, newNode); 
        if (root.studentNumber != newNode.studentNumber) {
            if (root.studentNumber < newNode.studentNumber) {
                root = lRotate(root);
            }else{
                root = rRotate(root);
            }
        }
        
        if (mark != null) {
            root.mark = mark;
        }
        return root;
    }

    private Node rRotate(Node ptr){
        Node ptrLeft = ptr.left;
        ptr.left = ptrLeft.right;
        ptrLeft.right = ptr;
        return ptrLeft;
    }

    private Node lRotate(Node ptr){
        Node ptrRight = ptr.right;
        ptr.right = ptrRight.left;
        ptrRight.left = ptr;
        return ptrRight;
    }

    
    private Node splay(Node ptr, Node newNode) {

        if (ptr == null || ptr.studentNumber == newNode.studentNumber) {
            return ptr;
        }

        if (ptr.studentNumber > newNode.studentNumber) {
            if (ptr.left == null) {
                return ptr;
            }
            if (ptr.left.studentNumber > newNode.studentNumber) {
                if (ptr.left.left.studentNumber == newNode.studentNumber) {
                    ptr.left.left = splay(ptr.left.left, newNode);                
                    ptr = rRotate(ptr);
                }else{
                    ptr.left = splay(ptr.left, newNode);
                    if (ptr.left.studentNumber == newNode.studentNumber) {
                        return ptr;
                    }else{
                        ptr = splay(ptr, newNode);
                        return ptr;
                    }
                }
                
            }else if(ptr.left.studentNumber < newNode.studentNumber){
                if (ptr.left.right.studentNumber == newNode.studentNumber) {
                    ptr.left.right = splay(ptr.left.right, newNode);
                    ptr.left = lRotate(ptr.left);
                }
                else{
                    ptr.left = splay(ptr.left, newNode);
                    if (ptr.left.studentNumber == newNode.studentNumber) {
                        return ptr;
                    }else{
                        ptr = splay(ptr, newNode);
                        return ptr;
                    }
                }
                
            }
            return (ptr.left == null) ? ptr : rRotate(ptr);
        }else{
            if (ptr.right == null){
                return ptr;
            } 
            if (ptr.right.studentNumber > newNode.studentNumber) {
                if (ptr.right.left.studentNumber == newNode.studentNumber) {
                    ptr.right.left = splay(ptr.right.left, newNode);
                    ptr.right = rRotate(ptr.right);
                }else{
                    ptr.right = splay(ptr.right, newNode);
                    if (ptr.right.studentNumber == newNode.studentNumber) {
                        return ptr;
                    }else{
                        ptr = splay(ptr, newNode);
                        return ptr;
                    }
                }
                
            }else if (ptr.right.studentNumber < newNode.studentNumber) {
                if (ptr.right.right.studentNumber == newNode.studentNumber) {
                    ptr.right.right = splay(ptr.right.right, newNode);
                    ptr = lRotate(ptr);
                }else{
                    ptr.right = splay(ptr.right, newNode);
                    if (ptr.right.studentNumber == newNode.studentNumber) {
                        return ptr;
                    }else{
                        ptr = splay(ptr, newNode);
                        return ptr;
                    }
                }
            }
            return (ptr.right == null)? ptr: lRotate(ptr);
        }
    }

    private void insert(Node ptr, Node newNode, Node pre) {
        if (newNode == null) {
            return;
        }
        if (ptr == null && pre == null) {
            ptr = newNode;
            return;
        }
        
        if (ptr == null) {
            if (pre.studentNumber > newNode.studentNumber) {
                pre.left = newNode;
            }
            else{
                pre.right = newNode;
            }
            return;
        }

        pre = ptr;
        if (ptr.studentNumber > newNode.studentNumber) {
            ptr = ptr.left;
        }else{
            ptr = ptr.right;
        }
        insert(ptr, newNode, pre);
    }

    public Node remove(int studentNumber) {
        if (root == null) {
            return null;
        }

        root = access(studentNumber);
        Node removedNode = new Node(root.studentNumber, root.mark);
        Node leftTree = copy(root.left);
        Node rightTree = copy(root.right);
        if (leftTree == null && rightTree == null) {
            root = null;
            return removedNode;
        }
        if (leftTree == null) {
            root = rightTree;
            return removedNode;
        }

        root = leftTree;
        root = access(max(leftTree).studentNumber);
        root.right = rightTree;
        return removedNode;
    }

    private Node copy(Node ptr){
        if (ptr == null) {
            return null;
        }

        Node newNode = new Node(ptr.studentNumber,ptr.mark);
        newNode.left = copy(ptr.left);
        newNode.right = copy(ptr.right);
        return newNode;
    }

    private Node max(Node ptr){
        if (ptr.right == null) {
            return ptr;
        }
        return max(ptr.right);
    }

    public String sortByStudentNumber() {
        if (root == null) {
            return "Empty Tree";
        }
        String s = "";
        return inOrder(root, s);
    }

    private String inOrder(Node ptr, String s) {
        if (ptr != null) {
            
            s = inOrder(ptr.left, s);
            s += ptr.toString();
            s = inOrder(ptr.right, s);
            
        }
        return s;
    }

    public String sortByMark() {
        return "Empty Tree";
    }

    private boolean contains(Node ptr, int studentNumber){
        if (ptr == null) {
            return false;
        }else if(studentNumber == ptr.studentNumber){
            return true;
        }else if(ptr.studentNumber < studentNumber){
            return contains(ptr.right, studentNumber);

        }else{
            return contains(ptr.left, studentNumber);
        }
    }


}
