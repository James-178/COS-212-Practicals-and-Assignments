public class RedBlackTree<T extends Comparable<T>> {

    /*
     * Sentinel is not the root. Go check the text book if this doesn't make sense
     */
    public RedBlackNode<T> SENTINEL;
    public RedBlackNode<T> NULL_NODE;

    public static final int RED = 1;
    public static final int BLACK = 0;

    public RedBlackTree() {
        SENTINEL = new RedBlackNode<T>(null);
        SENTINEL.colour = BLACK;
        NULL_NODE = new RedBlackNode<T>(null);
        NULL_NODE.colour = BLACK;
        SENTINEL.left = SENTINEL.right = NULL_NODE;
        NULL_NODE.left = NULL_NODE.right = NULL_NODE;
    }

    public RedBlackNode<T> getRoot() {
        return SENTINEL.right;
    }

    public void bottomUpInsert(T data) {
        if (SENTINEL.right == NULL_NODE) {
            SENTINEL.right = new RedBlackNode<T>(data);
            SENTINEL.right.colour = BLACK;
            SENTINEL.right.right = NULL_NODE;
            SENTINEL.right.left = NULL_NODE;
            return;
        }
 
        if (contains(data, SENTINEL.right)) {
            return;
        }

        insert(SENTINEL.right, data, null);
        // System.out.println(toString());

        SENTINEL.right = fixTree(SENTINEL.right, data);
        SENTINEL.right.colour = BLACK;

    }

    private RedBlackNode<T> rRotate(RedBlackNode<T> ptr){
        RedBlackNode<T> ptrLeft = ptr.left;
        ptr.left = ptrLeft.right;
        ptrLeft.right = ptr;
        return ptrLeft;
    }

    private RedBlackNode<T> lRotate(RedBlackNode<T> ptr){
        RedBlackNode<T> ptrRight = ptr.right;
        ptr.right = ptrRight.left;
        ptrRight.left = ptr;
        return ptrRight;
    }

    private RedBlackNode<T> fixTree(RedBlackNode<T> ptr, T data) {
        if (ptr == NULL_NODE || data.compareTo(ptr.data) == 0) {
            return ptr;
        }

        if (ptr.data.compareTo(data) > 0) {
            if (ptr.left == NULL_NODE) {
                return ptr;
            }

            if (ptr.left.data.compareTo(data) > 0) {
                if (ptr.left.left.data.compareTo(data) == 0) { //zig-zig
                    if (ptr.left.colour == RED && ptr.right.colour == RED) {
                        ptr.colour = RED;
                        ptr.left.colour = ptr.right.colour = BLACK;
                        if (SENTINEL.right.data.compareTo(ptr.data) == 0) {
                            ptr.colour = BLACK;
                        }
                    }else if(ptr.left.colour == RED && ptr.right.colour == BLACK){
                        ptr.colour = RED;
                        ptr.left.colour = BLACK;
                        ptr = rRotate(ptr);
                        if (SENTINEL.right.data.compareTo(ptr.data) == 0) {
                            ptr.colour = BLACK;
                        }
                    }
                }else{
                    ptr.left = fixTree(ptr.left, data);
                    if (ptr.left.colour == RED && ptr.left.left.colour == RED) {
                        if (ptr.left.colour == RED && ptr.right.colour == RED) {
                            ptr.colour = RED;
                            ptr.left.colour = ptr.right.colour = BLACK;
                        }else if(ptr.left.colour == RED && ptr.right.colour == BLACK){
                            ptr.colour = RED;
                            ptr.left.colour = BLACK;
                            ptr = rRotate(ptr);
                        }
                    }
                }
            }else if (ptr.left.data.compareTo(data) < 0) {
                if (ptr.left.right.data.compareTo(data) == 0) {
                    if (ptr.left.colour == RED && ptr.right.colour == RED) {
                        ptr.colour = RED;
                        ptr.left.colour = ptr.right.colour = BLACK;
                        if (SENTINEL.right.data.compareTo(ptr.data) == 0) {
                            ptr.colour = BLACK;
                        }
                    }else if (ptr.left.colour == RED && ptr.right.colour == BLACK) {
                        ptr.colour = RED;
                        ptr.left.right.colour = BLACK;
                        ptr.left = lRotate(ptr.left);
                        ptr = rRotate(ptr);
                        if (SENTINEL.right.data.compareTo(ptr.data) == 0) {
                            ptr.colour = BLACK;
                        }
                    }
                }else{
                    ptr.left = fixTree(ptr.left, data);
                    if (ptr.left.colour == RED && ptr.left.right.colour == RED) {
                        ptr.colour = RED;
                        ptr.left.right.colour = BLACK;
                        ptr.left = lRotate(ptr.left);
                        ptr = rRotate(ptr);
                    }
                }
            }
            return ptr;
        }else{
            if (ptr.right == NULL_NODE) {
                return ptr;
            }

            if (ptr.right.data.compareTo(data) > 0) {
                if (ptr.right.left.data.compareTo(data) == 0) {
                    if (ptr.left.colour == RED && ptr.right.colour == RED) {
                        ptr.colour = RED;
                        ptr.left.colour = ptr.right.colour = BLACK;
                        if (SENTINEL.right.data.compareTo(ptr.data) == 0) {
                            ptr.colour = BLACK;
                        }
                    }else if(ptr.right.colour == RED && ptr.left.colour == BLACK){
                        ptr.colour = RED;
                        ptr.right.left.colour = BLACK;
                        ptr.right = rRotate(ptr.right);
                        ptr = lRotate(ptr);
                        if (SENTINEL.right.data.compareTo(ptr.data) == 0) {
                            ptr.colour = BLACK;
                        }
                    }
                }else{
                    ptr.right = fixTree(ptr.right, data);
                    if (ptr.right.colour == RED && ptr.right.left.colour == RED) {
                        ptr.colour = RED;
                        ptr.right.left.colour = BLACK;
                        ptr.right = rRotate(ptr.right);
                        ptr = lRotate(ptr);
                    }
                }
            }else if (ptr.right.data.compareTo(data) < 0) {
                if (ptr.right.right.data.compareTo(data) == 0) {
                    if (ptr.left.colour == RED && ptr.right.colour == RED) {
                        ptr.colour = RED;
                        ptr.left.colour = ptr.right.colour = BLACK;
                        if (SENTINEL.right.data.compareTo(ptr.data) == 0) {
                            ptr.colour = BLACK;
                        }
                    }else if (ptr.right.colour == RED && ptr.left.colour == BLACK) {
                        ptr.colour = RED;
                        ptr.right.colour = BLACK;
                        ptr = lRotate(ptr);
                        if (SENTINEL.right.data.compareTo(ptr.data) == 0) {
                            ptr.colour = BLACK;
                        }
                    }
                }else{
                    ptr.right = fixTree(ptr.right, data);
                    if (ptr.right.colour == RED && ptr.right.right.colour == RED) {
                        if (ptr.left.colour == RED && ptr.right.colour == RED) {
                            ptr.colour = RED;
                            ptr.left.colour = ptr.right.colour = BLACK;
                        }else if (ptr.right.colour == RED && ptr.left.colour == BLACK) {
                            ptr.colour = RED;
                            ptr.right.colour = BLACK;
                            ptr = lRotate(ptr);
                        }
                    }
                }
            }
            return ptr;

        }
    }

    private void insert(RedBlackNode<T> ptr, T data, RedBlackNode<T> pre) {
        if (ptr == NULL_NODE) {
            if (pre.data.compareTo(data) > 0) {
                pre.left = new RedBlackNode<T>(data);
                pre.left.colour = RED;
                pre.left.left = NULL_NODE;
                pre.left.right = NULL_NODE;
            }
            else{
                pre.right = new RedBlackNode<T>(data);
                pre.right.colour = RED;
                pre.right.left = NULL_NODE;
                pre.right.right = NULL_NODE;
            }
            return;
        }
        pre = ptr;
        if (ptr.data.compareTo(data) > 0) {
            ptr = ptr.left;
        }else{
            ptr = ptr.right;
        }
        insert(ptr, data, pre);
    }


    private boolean contains(T data, RedBlackNode<T> root) {
        if (root == NULL_NODE) {
            return false;
        }

        if (data.compareTo(root.data) == 0) {
            return true;
        }else if(data.compareTo(root.data) > 0){
            return contains(data, root.right);
        }else{
            return contains(data, root.left);
        }
    }

    public boolean isValidRedBlackTree() {
        if (SENTINEL.right == NULL_NODE) {
            return true;
        } 

        if (SENTINEL.right.colour != BLACK) {
            return false;
        }

        boolean equal =  equalNum();
        boolean reds = checkReds(SENTINEL.right);

        return (equal && reds);
    }

    private boolean checkReds(RedBlackNode<T> root) {
        if (root.left != NULL_NODE ) {
            if (root.colour == RED && root.left.colour == RED) {
                return false;
            } else{
                return checkReds(root.left);
            }
            
        }
        if (root.right != NULL_NODE) {
            if (root.colour == RED && root.right.colour == RED) {
                return false;
            } else{
                return checkReds(root.right);
            }
         
        }
        return true;
    }

    private boolean equalNum() {
        int blackLeft = numBlack(SENTINEL.right.left);
        int blackRight = numBlack(SENTINEL.right.right);

        if (blackLeft != blackRight) {
            return false;
        }
        return true;
    }

    private int numBlack(RedBlackNode<T> root) {
        if (root == NULL_NODE) {
            return 0;
        }
        
        int blackLeft = numBlack(root.left);
        int blackRight = numBlack(root.right);

        if (blackLeft != blackRight || blackLeft == -1) {
            return -1;
        }
        if (root.colour == BLACK) {
            return 1 + blackLeft;
        }else{
            return 0 + blackLeft;
        }
    }

    public void topDownDelete(T data) {
        if (!contains(data, SENTINEL.right)) {
            return;
        }
        boolean check = false;
        RedBlackNode<T> root = SENTINEL.right;
        while (check == false) {
            if(root.data.compareTo(data) == 0){
                if (root.left != NULL_NODE) {
                    findMaxLeft(root.left);
                }
            }

            if (root.left.colour == BLACK && root.right.colour == BLACK) {//step 1.1
                root.colour = RED;
                if (root.data.compareTo(data) > 0) {
                    if (root.left.left.colour == BLACK && root.left.right.colour == BLACK) {//step 2A
                    // step 2A1
                        root.colour = BLACK;
                        root.right.colour = RED;
                        root.left.colour = RED;
                        root = root.left;
                    }else if(root.right.right.colour == RED || (root.right.left.colour == RED && root.right.right.colour == RED)){//step 2A3
                        root.colour = BLACK;
                        root.right.colour = RED;
                        root.left.colour = RED;
                        root.right.right.colour = BLACK;
                        root = lRotate(root);
                        root = root.left;
                    }else{//step 2A2
                        root.colour = BLACK;
                        root.left.colour = RED;
                        root.right = rRotate(root.right);
                        root = lRotate(root);
                        root = root.left;
                    }
                }else if(root.data.compareTo(data) < 0){
                    if (root.right.left.colour == BLACK && root.right.right.colour == BLACK) {//step 2A
                        // step 2A1
                            root.colour = BLACK;
                            root.right.colour = RED;
                            root.left.colour = RED;
                            root = root.right;
                        }else if(root.left.left.colour == RED || (root.left.left.colour == RED && root.left.right.colour == RED)){//step 2A3
                            root.colour = BLACK;
                            root.right.colour = RED;
                            root.left.colour = RED;
                            root.left.left.colour = BLACK;
                            root = rRotate(root);
                            root = root.right;
                        }else{//step 2A2
                            root.colour = BLACK;
                            root.right.colour = RED;
                            root.left = lRotate(root.left);
                            root = rRotate(root);
                            root = root.right;
                        }
                }
            }else{//step 2B
                if (root.data.compareTo(data) > 0) {
                    root = root.left;//might need to say if not the desired value 
                    if (root.data.compareTo(data) > 0) {
                        if (root.left.colour == RED) {
                            root = root.left;
                        }else{
                            root.colour = RED;
                            root.right.colour = BLACK;
                            root = lRotate(root);
                        }
                    }else if(root.data.compareTo(data) < 0){
                        if (root.right.colour == RED) {
                            root = root.right;
                        }else{
                            root.colour = RED;
                            root.left.colour = BLACK;
                            root = rRotate(root);
                        }
                    }
                }else if (root.data.compareTo(data) < 0) {
                    root = root.right;//might need to say if not the desired value 
                    if (root.data.compareTo(data) > 0) {
                        if (root.left.colour == RED) {
                            root = root.left;
                        }else{
                            root.colour = RED;
                            root.right.colour = BLACK;
                            root = lRotate(root);
                        }
                    }else if(root.data.compareTo(data) < 0){
                        if (root.right.colour == RED) {
                            root = root.right;
                        }else{
                            root.colour = RED;
                            root.left.colour = BLACK;
                            root = rRotate(root);
                        }
                    }
                }
            }
            
        }
    }

    /* -------------------------------------------------------------------------- */
    /* Private methods, which shouldn't be called from outside the class */
    /* -------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I worked really hard to make it pretty. */
    /* Also, it matches the website. -------------------------------------------- */
    /* Also, also, we might test against it ;) ---------------------------------- */
    /* -------------------------------------------------------------------------- */
    private StringBuilder toString(RedBlackNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != NULL_NODE) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.toString()).append("\n");
        if (node.left != NULL_NODE) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return SENTINEL.right == NULL_NODE ? "Empty tree"
                : toString(SENTINEL.right, new StringBuilder(), true, new StringBuilder()).toString();
    }

    public String toVis() {
        return toVisHelper(getRoot());
    }

    private String toVisHelper(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return "{}";
        }
        String leftStr = toVisHelper(node.left);
        String rightStr = toVisHelper(node.right);
        return "{" + node.toString() + leftStr + rightStr + "}";
    }

}
