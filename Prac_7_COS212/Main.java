public class Main {
    public static void main(String[] args) {
        MaxSkewHeap h1 = new MaxSkewHeap();
        System.out.println(h1);
        MaxSkewHeap h2 = new MaxSkewHeap("{20{15{4{3{}{}}{1{}{}}}{}}{12{}{}}}");
        System.out.println(h2);
        h2.insert(14);
        System.out.println(h2);
        h2.insert(11);
        System.out.println(h2);
        h2.insert(30);
        System.out.println(h2);
        h2.insert(25);
        System.out.println(h2);
        h2.insert(24);
        System.out.println(h2);

        System.out.println(h2.searchPath(18));
        System.out.println(h2.searchPath(15));
        System.out.println(h2.searchPath(30));
        System.out.println(h2.searchPath(11));
        System.out.println(h2.search(20).toString());
        System.out.println(h2.search(14).toString());
        System.out.println(h2.search(3).toString());
        System.out.println(h2.search(25).toString());
        

        h2.remove(0);
        System.out.println(h2);
        h2.remove(14);
        System.out.println(h2);
        h2.remove(20);
        System.out.println(h2);


        
    }
}