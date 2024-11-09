public abstract class Graph<T> {
    public GraphImplementation<T> impl;

    Graph(GraphImplementation<T> impl) {
        this.impl = impl;
    }

    public void addNode(T node) {
        impl.addNode(node);
    }

    abstract void addEdge(T from, T to);

    public void removeNode(T node) {
        impl.removeNode(node);
    }

    abstract void removeEdge(T from, T to);

    public void display() {
        impl.display();
    }

    public LinkedList<T> bfs(T startNode) {
        LinkedList<T> edges = new LinkedList<T>();
        return edges;
    }

    public LinkedList<T> dfs(T startNode) {
        int numNodes = impl.numberOfNodes();
        int[] numArr = new int[numNodes];

        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = 0;
        }

        LinkedList<T> edges = new LinkedList<T>();
        return edges;
    }

    // private void dfsRec(T currData, int i, int[] numArr, LinkedList<T> edges){
    //     int numv = impl.indexOfNode(currData);
    //     T currNode = impl.nodeAtIndex(numv);
        
    //     numArr[numv]++;
    //     edges.add(currNode);

    //     LinkedList<T> nodesEdges = impl.getNeighbors(currData);
    //     int s = nodesEdges.size();
    //     for (int j = 0; j < s; j++) {
            
    //         if(){

    //         }
    //     }


    // }

    public LinkedList<T> unweightedShortestPath(T startNode, T endNode) {
        LinkedList<T> ll = new LinkedList<T>();
        return ll;
    }
}
