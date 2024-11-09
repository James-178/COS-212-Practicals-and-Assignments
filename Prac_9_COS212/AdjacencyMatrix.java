public class AdjacencyMatrix<T> implements GraphImplementation<T> {
    public LinkedList<T> nodes; // Stores the nodes to maintain index mapping
    public boolean[][] matrix; // Adjacency matrix to store edge information

    /*
     * Constructor
     */
    public AdjacencyMatrix() {
        nodes = new LinkedList<>();
        matrix = new boolean[0][0];
    }

    /*
     * Public methods to adhere to the interface
     */
    @Override
    public void addNode(T data) {
        nodes.add(data);
        
        boolean[][] temp = new boolean[matrix.length][matrix.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }

        matrix = new boolean[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }

    @Override
    public void addEdge(T dataFrom, T dataTo) {
        if(!containsNode(dataTo) || !containsNode(dataFrom)){
            return;
        }

        int from = nodes.indexOf(dataFrom);
        int to = nodes.indexOf(dataTo);
        matrix[from][to] = true;
    }

    public void removeNode(T data) {
        if(containsNode(data)){
            int k = indexOfNode(data);
            boolean[][] temp = new boolean[matrix.length][matrix.length];
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp.length; j++) {
                    temp[i][j] = matrix[i][j];
                }
            }
            int i1 = 0;
            matrix = new boolean[matrix.length - 1][matrix.length - 1];
            for (int i = 0; i < temp.length; i++) {
                int i2 = 0;
                if(i != k){
                    for (int j = 0; j < temp.length; j++) {
                        if(j != k){
                            matrix[i1][i2] = temp[i][j];
                            i2++;
                        }
                    }
                    i1++;
                }
                
            }

            nodes.remove(data);
        }

        
    }

    public void removeEdge(T dataFrom, T dataTo) {
        if(!containsNode(dataTo) || !containsNode(dataFrom)){
            return;
        }

        int from = nodes.indexOf(dataFrom);
        int to = nodes.indexOf(dataTo);
        matrix[from][to] = false;
    }

    @Override
    public int numberOfNodes() {
        return nodes.size();
    }

    @Override
    public int numberOfEdges() {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j]){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public boolean containsNode(T data) {
        if(nodes.indexOf(data) != -1){
            return true;
        } 
        return false;
    }

    @Override
    public int indexOfNode(T data) {
        if(containsNode(data)){
            return nodes.indexOf(data);
        }
        return -1;
    }

    @Override
    public T nodeAtIndex(int index) {
        if(index < nodes.size()){
            return nodes.get(index);
        }
        return null;
        
    }

    @Override
    public boolean isConnected(T dataFrom, T dataTo) {
        if(!containsNode(dataTo) || !containsNode(dataFrom)){
            return false;
        }

        int from = nodes.indexOf(dataFrom);
        int to = nodes.indexOf(dataTo);

        if(matrix[from][to]){
            return true;
        }
        return false;
    }

    @Override
    public LinkedList<T> getNeighbors(T data) {
        LinkedList<T> newLL = new LinkedList<T>();
        if(containsNode(data)){
            int j = indexOfNode(data);
            for (int i = 0; i < matrix.length; i++) {
                if(matrix[j][i]){
                    newLL.add(nodeAtIndex(i));
                }
            }
        }
        return newLL;
    }

    @Override
    public String display() {
        String out = ("Adjacency Matrix:\n");
        for (int i = 0; i < matrix.length; i++) {
            out += "\t" + nodes.get(i) + ": ";
            for (int j = 0; j < matrix[i].length; j++) {
                out += (matrix[i][j] ? "1 " : "0 ");
            }
            out += "\n";
        }
        return out;
    }

    /*
     * Private methods used for this implementation
     */

}
