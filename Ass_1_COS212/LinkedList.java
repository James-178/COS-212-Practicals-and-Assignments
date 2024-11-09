public class LinkedList {
    public CoordinateNode head;

    public LinkedList() {
        head = null;
    }

    public LinkedList(int x, int y) {
        head = new CoordinateNode(x, y);
    }

    public void append(int x, int y) {
        if (head == null) {
            head = new CoordinateNode(x, y);
            return;
        }
        CoordinateNode ptr = head;
        appendNode(ptr, x, y);
    }

    public void appendList(LinkedList other) {// need to make deep copys
        if (other.head == null) {
            return;
        }
        
        if (head == null) {
            CoordinateNode ptrOther = other.head;
            copyOther(ptrOther);
            return;
        }
        
        CoordinateNode ptr = head;
        appendListRec(ptr, other);
    }

    public boolean contains(int x, int y) {
        if (head == null) {
            return false;
        }
        CoordinateNode ptr = head;
        return containsRec(ptr,x,y);
    }

    @Override
    public String toString() {
        if (head == null) {
            return "Empty List";
        }

        String str = "[" + head.x + "," + head.y + "]";
        CoordinateNode ptr = head;
        return print(ptr.next, str);
    }

    public int length() {
        if (head == null) {
            return 0;
        }
        CoordinateNode ptr = head;
        return lengthRec(ptr, 1);
    }

    public LinkedList reversed() {
        LinkedList rev = new LinkedList();
        LinkedList temp = new LinkedList();
        temp.appendList(this);
        CoordinateNode ptr = temp.head;
        CoordinateNode revHead = reversedRec(ptr);
        rev.copyOther(revHead);
        return rev;
    }

    private void appendNode(CoordinateNode ptr, int x, int y){
        if (ptr.next == null) {
            CoordinateNode newNode = new CoordinateNode(x, y);
            ptr.next = newNode;
            return;
        }

        appendNode(ptr.next,x, y);
    }

    private void copyOther(CoordinateNode ptrOther){
        if (ptrOther == null) {
            return;
        }
        append(ptrOther.x, ptrOther.y);
        copyOther(ptrOther.next);
    }

    private void appendListRec(CoordinateNode ptr, LinkedList other){
        if (ptr.next == null) {
            copyOther(other.head);
            return;
        }

        appendListRec(ptr.next, other);
    }

    private boolean containsRec(CoordinateNode ptr, int x, int y){
        if (ptr.next == null) {
            if (ptr.x == x && ptr.y == y) {
                return true;
            }
            return false;
        }
        if (ptr.x == x && ptr.y == y) {
            return true;
        }
        return containsRec(ptr.next, x, y);
    }

    private int lengthRec(CoordinateNode ptr, int i){
        if (ptr.next == null) {
            return i;
        }

        return lengthRec(ptr.next, i + 1);
    }

    private String print(CoordinateNode ptr, String str){
        if (ptr == null) {
            return str;
        }
        str += " -> " + "[" + ptr.x + "," + ptr.y + "]";
        return print(ptr.next, str);
    }

    private CoordinateNode reversedRec(CoordinateNode node){
        if (node == null || node.next == null) {
            return node;
        }
        CoordinateNode nextNode = reversedRec(node.next);
        node.next.next = node;
        node.next = null;
        return nextNode;
    }

    public void removeLast(){
        if(head == null){
            return;
        }

        if (head.next == null) {
            head = null;
            return;
        }

        CoordinateNode ptr = head;
        removeRec(ptr);
    }

    private void removeRec(CoordinateNode ptr) {
        if (ptr.next.next == null) {
            ptr.next = null;
            return;
        }
        removeRec(ptr.next);
    }
}
