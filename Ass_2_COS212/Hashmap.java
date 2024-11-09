
public class Hashmap<K, V> {
    public HashNode<K, V>[] data;
    public int numValues;
    public int capacity;

    public Hashmap() {
        data = new HashNode[2];
        numValues = 0;
        capacity =2;
    }

    public boolean insert(K key, V value) {
        if(get(key) != null){
            return false;
        }

        int h1 = hornerHash(key);
        int h2 = secondaryHash(key);
        boolean inserted = false;
        for (int i = 0; i < capacity; i++) {
            int hash = (h1 + h2 * i) % capacity;
            if (data[hash] == null){
                data[hash] = new HashNode<>(key, value);
                numValues++;
                inserted = true;
                break;
            } 
        }
        
        if(numValues == capacity){
            HashNode<K, V>[] temp = new HashNode[capacity];
            for (int i = 0; i < capacity; i++) {
                temp[i] = data[i];
            }
            data = new HashNode[capacity * 2];
            capacity *= 2;
            numValues = 0;
            for (int i = 0; i < temp.length; i++) {
                if(temp[i] != null){
                    insert(temp[i].key, temp[i].value);
                }
            }
        }

        return inserted;

    }

    public void delete(K key) {
        if(get(key) == null){
            return;
        }

        int h1 = hornerHash(key);
        int h2 = secondaryHash(key);
        for (int i = 0; i < capacity; i++) {
            int hash = (h1 + h2 * i) % capacity;
            if (data[hash] != null){
                if(data[hash].key.equals(key)){
                    data[hash] = null;
                    numValues--;
                    return;
                }
            } 
        }

    }

    public V get(K key) {
        int h1 = hornerHash(key);
        int h2 = secondaryHash(key);
        for (int i = 0; i < capacity; i++) {
            int hash = (h1 + h2 * i) % capacity;
            if(data[hash] != null){
                if (data[hash].key.equals(key)){
                    return data[hash].value;
                } 
            }
        }
        return null;
    }


    public Object[] getKeys() {
        Object[] arr = new Object[numValues];
        int count = 0;
        for (int i = 0; i < capacity; i++) {
            if(data[i] != null){
                arr[count] = data[i].key;
                count++;
            }
        }
        return arr;
    }

    public void clear() {
        data = new HashNode[2];
        numValues = 0;
        capacity =2;
    }

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I tried to make it pretty. */
    /* -------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------- */
    /* Also we may test against it */
    /* -------------------------------------------------------------------------- */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s | %-20s | %-20s%n", "Index", "Key", "Value");
        sb.append(header);
        for (int i = 0; i < header.length() - 1; i++) {
            sb.append("-");
        }
        sb.append("\n");
        for (int i = 0; i < capacity; i++) {
            if (data[i] != null) {
                String row = String.format("%-10d | %-20s | %-20s%n", i, data[i].key.toString(),
                        data[i].value.toString());
                sb.append(row);
            } else {
                String row = String.format("%-10d | %-20s | %-20s%n", i, "null", "null");
                sb.append(row);
            }
        }

        return sb.toString();
    }

    public int hornerHash(K key) {
        String keyStr = key.toString();
        int hashVal = 0;
        for (int i = 0; i < keyStr.length(); i++)
            hashVal = 37 * hashVal + keyStr.charAt(i);
        hashVal %= capacity;
        if (hashVal < 0)
            hashVal += capacity;
        return hashVal;
    }

    public int secondaryHash(K key) {
        int hash = key.hashCode();
        // Ensure the step size is odd to ensure it's coprime with capacity, since
        // capacity is a power of 2.
        int step = (hash & (capacity - 1)) | 1; // This ensures the step size is always odd.
        return step;
    }

}
