public abstract class Heap<T extends Comparable<T>> {

    public Comparable<T>[] data;
    public int size;

    public Heap() {
        this.data = (Comparable<T>[]) new Comparable[2];
        size = 0;
    }

    public Heap(T[] array) {
        data = array;
        int i = ((array.length - 1)-1)/2;
        size = array.length;
        while(i>=0){
            T p = array[i];  
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            while((2*i+1) < size){
                int swapIndex = l;
                if((r) < size && compare( array[r], array[l])){
                    swapIndex = r;
                }
                if (compare(array[swapIndex], p)){
                    T temp = array[swapIndex];
                    array[swapIndex] = p;
                    array[i] = temp;


                    i = swapIndex;
                    p = array[i];
                    l = 2 * i + 1;
                    r = 2 * i + 2;
                } else {
                    break;
                }
            }
            i--;
        } 
        
        this.data = (Comparable<T>[]) new Comparable[array.length];
        for (int j = 0; j < array.length; j++) {
            this.data[j] = array[j];
        }
    }

    protected abstract boolean compare(Comparable<T> child, Comparable<T> parent);

    public void push(T item) {
        if (size == data.length) {
            Comparable<T>[] newData = (Comparable<T>[]) new Comparable[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
        
        data[size] = item;
        size++;
        int i = size - 1;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (compare(data[i], data[parent])) {
                T temp = (T)this.data[i];
                data[i] = data[parent];
                data[parent] = temp;
                i = parent;
            } else {
                break;
            }
        }

    }

    public Comparable<T> pop() {
        T root = (T)this.data[0];
        this.data[0] = this.data[size-1];
        size--;
        int i = 0;
        while ((2 * i + 1) < size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;
            if (compare(data[left], data[largest])) {
                largest = left;
            }
            if ((right < size) && compare(data[right], data[largest])) {
                largest = right;
            }
            if (largest != i) {
                T temp = (T)data[i];
                data[i] = data[largest];
                data[largest] = temp;
                i = largest;
            } else {
                break;
            }
        }

        return root;
    }

    public Comparable<T> peek() {
        return this.data[0];
    }

    /*
     * 
     * Functions used for the toString
     * Do not change them but feel free to use them
     * 
     */

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(0, "", true, sb); // Start from the root
        return sb.toString();
    }

    private void buildString(int index, String prefix, boolean isTail, StringBuilder sb) {
        if (index < size) {
            String linePrefix = isTail ? "└── " : "┌── ";
            if (getRightChildIndex(index) < size) { // Check if there is a right child
                buildString(getRightChildIndex(index), prefix + (isTail ? "|   " : "    "), false, sb);
            }
            sb.append(prefix).append(linePrefix).append(data[index]).append("\n");
            if (getLeftChildIndex(index) < size) { // Check if there is a left child
                buildString(getLeftChildIndex(index), prefix + (isTail ? "    " : "│   "), true, sb);
            }
        }
    }

}
