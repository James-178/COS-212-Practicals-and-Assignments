class AdjacencyList<T> implements GraphImplementation<T> {

    public LinkedList<Node<T>> nodes;

    /*
     * Constructor
     */
    public AdjacencyList() {
        this.nodes = new LinkedList<>();
    }

    /*
     * Public methods to adhere to the interface
     */
    @Override
    public void addNode(T data) {
        Node<T> newNode = new Node<T>(data);
        
        if(!containsNode(data)){
            this.nodes.add(newNode);
        }
    }

    @Override
    public void addEdge(T dataFrom, T dataTo) {
        if(!containsNode(dataTo) || !containsNode(dataFrom)){
            return;
        }

        if(isConnected(dataFrom, dataTo)){
            return;
        }

        int i = indexOfNode(dataFrom);
        int j = indexOfNode(dataTo);
        Node<T> fromNode = (AdjacencyList<T>.Node<T>)nodeAtIndex(i);
        Node<T> toNode = (AdjacencyList<T>.Node<T>)nodeAtIndex(j);
    

        fromNode.edges.add(toNode);

    }

    public void removeNode(T data) {
        if(!containsNode(data)){
            return;
        }

        int i = indexOfNode(data);
        Node<T> fromNode = (AdjacencyList<T>.Node<T>)nodeAtIndex(i);

        nodes.remove(fromNode);
        for (int j = 0; j < nodes.size(); j++) {
            Node<T> n = nodes.get(i);
            n.edges.remove(fromNode);
        }
    }

    public void removeEdge(T dataFrom, T dataTo) {
        if(!containsNode(dataTo) || !containsNode(dataFrom)){
            return;
        }

        if(!isConnected(dataFrom, dataTo)){
            return;
        }

        int i = indexOfNode(dataFrom);
        int j = indexOfNode(dataTo);
        Node<T> fromNode = (AdjacencyList<T>.Node<T>)nodeAtIndex(i);
        Node<T> toNode = (AdjacencyList<T>.Node<T>)nodeAtIndex(j);

        fromNode.edges.remove(toNode);

    }

    @Override
    public int numberOfNodes() {
        return this.nodes.size();
    }

    @Override
    public int numberOfEdges() {
        int s = nodes.size();
        int num = 0;
        for (int i = 0; i < s; i++) {
            Node<T> newNode = nodes.get(i);
            num += newNode.getEdges().size();
        }
        return num;
    }

    @Override
    public boolean containsNode(T data) {
        int i = indexOfNode(data);
        return (i != -1) ? true: false;
    }

    @Override
    public int indexOfNode(T data) {
        int ind = 0;
        for (int i = 0; i < nodes.size(); i++) 
        {
            if (nodes.get(i).data.equals(data)) 
            {
                return ind;
            }
            ind++;
        }
        return -1; 
    }

    @Override
    public T nodeAtIndex(int index) {
        return (T)nodes.get(index);
    }

    @Override
    public boolean isConnected(T dataFrom, T dataTo) {
        if(!containsNode(dataTo) || !containsNode(dataFrom)){
            return false;
        }

        int i = indexOfNode(dataFrom);
        int j = indexOfNode(dataTo);
        Node<T> fromNode = (AdjacencyList<T>.Node<T>)nodeAtIndex(i);
        Node<T> toNode = (AdjacencyList<T>.Node<T>)nodeAtIndex(j);

        return fromNode.getEdges().indexOf(toNode) != -1;
    }

    @Override
    public LinkedList<T> getNeighbors(T data) {
        int i = indexOfNode(data);
        LinkedList<T> newLL = new LinkedList<T>();

        if(i != -1){
            Node<T> fromNode = (AdjacencyList<T>.Node<T>)nodeAtIndex(i);
            LinkedList<Node<T>> e = fromNode.getEdges();
            for (int j = 0; j < e.size(); j++) {
                newLL.add(e.get(j).data);
            }
        }

        return newLL;
    }

    @Override
    public String display() {
        String out = ("Graph structure:\n");
        for (int i = 0; i < nodes.size(); i++) {
            Node<T> currentNode = nodes.get(i);

            StringBuilder displayString = new StringBuilder("\t" + currentNode.data.toString() + ": ");
            LinkedList<Node<T>> edges = currentNode.getEdges();

            for (int j = 0; j < edges.size(); j++) {
                displayString.append(edges.get(j).data.toString());
                if (j < edges.size() - 1) {
                    displayString.append(", ");
                }
            }

            out += (displayString + "\n");
        }
        return out;
    }

    /*
     * Private methods used for this implementation
     */

    /*
     * Inner nodes used for this implementation
     */
    private class Node<U> {
        public U data;
        public LinkedList<Node<U>> edges;

        public Node(U data) {
            this.data = data;
            this.edges = new LinkedList<>();
        }

        public LinkedList<Node<U>> getEdges() {
            return this.edges;
        }

        public void addEdge(Node<U> newConnection) {
            this.edges.add(newConnection);
        }

        public void removeEdge(Node<U> oldConnection) {
            this.edges.remove(oldConnection);
        }

    }
}
