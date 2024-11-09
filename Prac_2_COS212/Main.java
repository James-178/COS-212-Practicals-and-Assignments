
public class Main {
    public static void main(String[] args) {
        BST<Integer> b1 = new BST<>();
        System.out.println(b1);
        b1.insert(3);
        b1.insert(4);
        b1.insert(2);
        
        System.out.println(b1);
        System.out.println(b1.isSuperficiallyBalanced());

        System.out.println(b1.contains(1));
        System.out.println(b1.contains(8));
        b1.delete(2);
        System.out.println(b1);

        b1.insert(2);
        b1.insert(6);
        b1.insert(0);
        b1.insert(1);
        b1.insert(8);
        b1.insert(9);
        System.out.println(b1);
        System.out.println(b1.getHeight());
        System.out.println(b1.getNumLeaves());
        System.out.println(b1.getNode(5));
        System.out.println(b1.getNode(3));
        System.out.println(b1.findMax());
        System.out.println(b1.findMin());
        b1.delete(1);
        System.out.println(b1);
        System.out.println(b1.isSuperficiallyBalanced());
        System.out.println(b1.printSearchPath(6));


        b1.insert(0);
        b1.insert(-1);
        System.out.println(b1);
        System.out.println(b1.isSuperficiallyBalanced());
        System.out.println(b1);
        System.out.println(b1.extractBiggestSuperficiallyBalancedSubTree());
        System.out.println(b1.printSearchPath(8));
    }
}
