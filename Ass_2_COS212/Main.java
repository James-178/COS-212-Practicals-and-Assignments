public class Main {
    public static void main(String[] args) throws Exception {
        

        Hashmap<Integer, Integer> h1 = new Hashmap<>();
        System.out.println(h1);
        for (int i = 0; i < 20; i++) {
            h1.insert(i, i);
        }
        System.out.println(h1);
        System.out.println(h1.get(9));
        System.out.println((h1.get(30) == null) ? "its null":h1.get(30));
        h1.delete(0);
        Object[] o1 = h1.getKeys();
        System.out.println(o1[0]);
        h1.clear();
        System.out.println(h1);

        BTree<Integer> b1 = new BTree<>(5);
        for (int i = 0; i < 300; i++) {
            b1.insert(i);
        }
        System.out.println(b1);

        System.out.println(b1.printPath(23));
        // Hashmap<Integer, Integer> h2 = new Hashmap<>();
        // for (int i = 0; i < 500; i++) {
        //     h2.insert(i, i);
        // }
        // System.out.println(h2);
        // System.out.println(h2.get(175));

        // for (int i = 40; i <100; i++) {
        //     h2.delete(i);
        // }
        // System.out.println(h2);
        // Object[] keys = h2.getKeys();
        // for (int i = 0; i < keys.length; i++) {
        //     System.out.println(keys[i]);
        // }
        // System.out.println(keys.length);
    }
}
