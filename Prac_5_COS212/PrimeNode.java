public class PrimeNode {
    public PrimeNode next;
    public int value;

    public PrimeNode(int v) {
        value = v;
        next = null;
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }

    public boolean contains(PrimeNode node, int val){
        if(node == null) return false;
        if(node.value == val){
            return true;
        }
        return contains(node.next, val);
    }
}
