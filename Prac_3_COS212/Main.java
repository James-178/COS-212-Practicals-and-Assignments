public class Main {
    public static void main(String[] args) {
        Node n1 = new Node(1, 50);
        System.out.println(n1);

        SplayTree s1 = new SplayTree();
        System.out.println(s1);

        // SplayTree s2 = new SplayTree("{[u10:null%]{[u5:40%]{[u3:30%]{}{}}{}}{[u12:40%]{[u11:80%]{}{}}{}}}");
        // SplayTree s3 = new SplayTree("{[u15:52%]{[u14:80%]{[u13:10%]{[u5:60%]{}{[u7:30%]{[u6:20%]{}{}}{}}}{}}{}}{}}");
        SplayTree s3 = new SplayTree("{[u18:null%]{[u10:null%]{[u9:null%]{[u6:null%]{[u4:null%]{}{}}{}}{}}{[u11:null%]{}{}}}{[u55:null%]{[u20:null%]{}{[u40:null%]{[u32:null%]{[u30:null%]{}{}}{[u34:50%]{[u33:50%]{}{}}{}}}{}}}{}}}");
        System.out.println(s3);
        System.out.println("hereeeeeeee");
        s3.access(18);
        System.out.println(s3);
        s3.access(10);
        System.out.println(s3);
        s3.access(32);
        System.out.println(s3);
        s3.access(55);
        System.out.println(s3);
        s3.access(4);
        System.out.println(s3);
        s3.access(18);
        System.out.println(s3);
        s3.access(12);
        System.out.println(s3);
        s3.access(80);
        System.out.println(s3);
        s3.access(34,50);
        System.out.println(s3);
        System.out.println(s3.remove(34));
        System.out.println(s3);
        System.out.println(s3.remove(29));
        System.out.println(s3);
        System.out.println(s3.sortByStudentNumber());
        System.out.println(s3.sortByMark());

        
    }
}

