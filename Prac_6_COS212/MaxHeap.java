class MaxHeap<T extends Comparable<T>> extends Heap<T> {

    public MaxHeap() {
        super();
    }

    public MaxHeap(T[] array) {
        super(array);
    }

    @Override
    protected boolean compare(Comparable<T> child, Comparable<T> parent) {
        if(((T)child).compareTo((T)parent) == 0){
            return false;
        }
        return ((T)child).compareTo((T)parent) > 0;
    }
}