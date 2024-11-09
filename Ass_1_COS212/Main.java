public class Main {
    // Output is in output.txt
    public static void main(String[] args) {
        // Maze m = new Maze("input.txt");
        // System.out.println(m.solve(3, 3, 1, 0));
        // System.out.println(m.validStarts(0, 0));

        CoordinateNode cn1 = new CoordinateNode(0, 0);
        System.out.println(cn1);
        
        System.out.println("\nList 1");
        LinkedList l1 = new LinkedList();
        System.out.println(l1);
        l1.append(1, 1);
        l1.append(1, 2);
        l1.append(1, 3);
        System.out.println(l1);
        
        System.out.println("\nList 2");
        LinkedList l2 = new LinkedList(2,2);
        l2.append(2, 3);
        System.out.println(l2);

        l2.appendList(l1);
        System.out.println(l1);
        System.out.println(l2);

        System.out.println("\nList 3");
        LinkedList l3 = new LinkedList();
        System.out.println(l3.contains(0, 0));
        l3.appendList(l2);
        System.out.println(l2);
        l3.appendList(new LinkedList());
        System.out.println(l2);
        System.out.println(l3.contains(0, 0));
        System.out.println(l3.contains(1, 1));
        System.out.println(l3.length());
        //l3.appendList(l3.reversed());
        LinkedList l4 = l3.reversed();
        System.out.println(l4);
        System.out.println(l3);
        l4.appendList(l3);
        System.out.println(l4);

        System.out.println();
        System.out.println();
        System.out.println();   
        
        Maze m1 = new Maze("input.txt");
        System.out.println(m1);
        Maze m2 = new Maze(m1);
        System.out.println();
        System.out.println(m2);

        Maze m3 = new Maze("random.txt");
        System.out.println(m3);

        LinkedList validSol = new LinkedList(3, 3);
        validSol.append(2, 3);
        validSol.append(1, 3);
        validSol.append(0, 3);
        validSol.append(0, 2);
        validSol.append(0, 1);
        validSol.append(0, 0);
        validSol.append(1, 0);
        System.out.println(validSol.length());
        System.out.println(validSol);
        System.out.println(m1.validSolution(3, 3, 1, 0, validSol));
        System.out.println(m1.validSolution(3, 3, 1, 0, new LinkedList()));
        //invalid start/end
        System.out.println(m1.solve(4, 3, 6, 0));
        System.out.println(m1.solve(4, 3, 1, 6));
        System.out.println(m1.solve(6, 3, 1, 0));
        System.out.println(m1.solve(4, -1, 1, 0));

        //test forward and backwards
        System.out.println(m1.solve(4, 3, 1, 0));
        System.out.println(m1.solve(1, 0, 4, 3));
        
        System.out.println(m1.solve(0, 4, 1, 0));
        System.out.println(m1.solve(0, 0, 0, 0));
        System.out.println(m1.solve(1, 0, 0, 4));
        System.out.println(m1.solve(4, 2, 0, 4));
        LinkedList validStarts = m1.validStarts(0, 0);
        System.out.println(validStarts);

        LinkedList linkedList1 = new LinkedList();
        linkedList1.append(1, 0);
        linkedList1.append(0, 0);
        linkedList1.append(0, 1);
        linkedList1.append(0, 2);
        linkedList1.append(1, 2);
        linkedList1.append(1, 1);
        linkedList1.append(2, 1);
        linkedList1.append(2, 0);
        linkedList1.append(3, 0);
        linkedList1.append(3, 1);
        linkedList1.append(3, 2);
        linkedList1.append(2, 2);
        linkedList1.append(2, 3);
        linkedList1.append(2, 4);
        linkedList1.append(3, 4);
        linkedList1.append(4, 4);
        System.out.println(m1.validSolution(1,0,4,3,linkedList1));
        
        linkedList1.append(4, 3);

        System.out.println(m1.validSolution(1,0,4,3,linkedList1));
        System.out.println(m1.validSolution(4,3,1,0,linkedList1));
        System.out.println(m1.validSolution(4,3,1,0,linkedList1.reversed()));
        System.out.println(m1.validSolution(8,0,4,3,linkedList1));
        System.out.println(m1.validSolution(1,8,4,3,linkedList1));
        System.out.println(m1.validSolution(2,0,4,3,linkedList1));
        System.out.println(m1.validSolution(2,0,4,3,new LinkedList()));
        System.out.println(m1.validSolution(0,0,0,0,new LinkedList(1,0)));
        System.out.println(m1.validSolution(0,0,0,0,new LinkedList(0,0)));
    }
}