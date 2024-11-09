public class Main {
    public static void main(String[] args) {
        
        PrimeNumberGenerator p1 = new PrimeNumberGenerator();
        System.out.println(p1.toString());
        for (int i = 0; i < 15; i++) {
            
            p1.nextPrime();
            System.out.println(p1.toString());
        }
        
        Hashmap h1 = new Hashmap("7[u23:45%,u16:60%,-,u2222:60%,u33380:60%]");
        System.out.println(h1.toStringOneLine());

        Hashmap h2 = new Hashmap();
        System.out.println(h2.toStringOneLine());

        int i1 = h1.hash(33380);
        System.out.println(i1);

        System.out.println();
        Hashmap h3 = new Hashmap("37[u222222:10%,u10:10%,u55:10%,u658:10%]");
        KeyValuePair k1 = h3.search(55);
        System.out.println(k1.toString());
        KeyValuePair k2 = h3.search(555);
        if (k2 == null) {
            System.out.println("null");
        }
        h3.insert(1000, 30);
        System.out.println(h3.toStringOneLine());
        System.out.println(h3.hash(1000));
        h3.remove(55);
        System.out.println(h3.toStringOneLine());
        System.out.println(h3);
        h3.insert(55, 10);
        System.out.println(h3.toStringOneLine());
        h3.insert(55, 20);
        System.out.println(h3.toStringOneLine());
    }
}
