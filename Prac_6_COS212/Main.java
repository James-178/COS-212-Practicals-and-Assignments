
public class Main {
    public static void main(String[] args) throws Exception {
        Integer[] a1 = new Integer[5];
        a1[0] = 5;
        a1[1] = 1;
        a1[2] = 2;
        a1[3] = 3;
        a1[4] = 1;
        MaxHeap<Integer> h1 = new MaxHeap<>();
        System.out.println(h1);


        MaxHeap<Integer> h2 = new MaxHeap<>(a1);
        System.out.println(h2.toString());
        h2.push(4);
        h2.push(12);
        h2.push(6);
        System.out.println(h2.toString());
        
        h2.pop();
        h2.pop();
        h2.pop();
        System.out.println(h2.toString());
        
        MinHeap<Integer> h3 = new MinHeap<>(a1);
        System.out.println(h3.toString());
    }
}
