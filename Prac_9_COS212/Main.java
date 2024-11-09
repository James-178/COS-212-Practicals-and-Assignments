public class Main {
    public static void main(String[] args) throws Exception {
        AdjacencyMatrix<Integer> am1 = new AdjacencyMatrix<Integer>();
        System.out.println(am1.display()); 
        am1.addNode(1);
        am1.addNode(2);
        am1.addNode(3);
        am1.addNode(4);
        System.out.println(am1.display());
        am1.addEdge(1,2);
        am1.addEdge(3,2);
        am1.addEdge(4,3);
        am1.addEdge(4,2);
        am1.addEdge(4,1);
        System.out.println(am1.display());

        am1.removeNode(3);
        System.out.println(am1.display());

        am1.removeEdge(4,1);
        System.out.println(am1.display());
        System.out.println(am1.numberOfEdges());
        System.out.println(am1.numberOfNodes());
        System.out.println(am1.containsNode(4));
        System.out.println(am1.containsNode(5));
        System.out.println(am1.indexOfNode(2));
        System.out.println(am1.nodeAtIndex(0));
        System.out.println(am1.isConnected(4,2));
        System.out.println(am1.isConnected(1,4));
        am1.addEdge(4, 1);
        am1.getNeighbors(4).printList();

        AdjacencyList<Integer> al1 = new AdjacencyList<Integer>();
        System.out.println(al1.display()); 
        al1.addNode(1);
        al1.addNode(2);
        al1.addNode(3);
        al1.addNode(4);
        System.out.println(al1.display());
        al1.addEdge(1,2);
        al1.addEdge(3,2);
        al1.addEdge(4,3);
        al1.addEdge(4,2);
        al1.addEdge(4,1);
        System.out.println(al1.display());

        al1.removeNode(3);
        System.out.println(al1.display());

        al1.removeEdge(4,1);
        System.out.println(al1.display());
        System.out.println(al1.numberOfEdges());
        System.out.println(al1.numberOfNodes());
        System.out.println(al1.containsNode(4));
        System.out.println(al1.containsNode(5));
        System.out.println(al1.indexOfNode(2));
        System.out.println(al1.nodeAtIndex(0));
        System.out.println(al1.isConnected(4,2));
        System.out.println(al1.isConnected(1,4)); 
        al1.addEdge(4, 1);
        al1.getNeighbors(4).printList();


    }
}
