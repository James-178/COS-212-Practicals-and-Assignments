public class RecursiveArray {
    public Integer[] array;

    public RecursiveArray() {
        array = new Integer[0];
    }

    public RecursiveArray(String input) {
        if (input == "") {
            array = new Integer[0];
        }
        else{
            array = new Integer[0];
            constructArray(input, 0);
        }
    }

    @Override
    public String toString() {
        if (array.length == 0) {
            return "Empty Array";
        }
        String str = "[" + array[0];
        return  print(1, str)+"]";
    }

    public void append(Integer value) {
        Integer[] newArray = new Integer[array.length + 1];
        newArray = createArray(newArray, 0);
        newArray[array.length] = value;
        array = newArray;
    }

    public void prepend(Integer value) {
        Integer[] newArray = new Integer[array.length + 1];
        newArray = createArrayPre(value, newArray, 1);
        array = newArray;
    }

    public boolean contains(Integer value) {
        boolean check = findInt(value, 0);
        return check;
    }

    public boolean isAscending() {
        if (array.length == 0 || array.length == 1) {
            return true;
        }
        return checkAscending(array[0], array[1], 0, true);
    }

    public boolean isDescending() {
        if (array.length == 0 || array.length == 1) {
            return true;
        }
        return checkDescending(array[0], array[1], 0, true);
    }

    public void sortAscending() {
        if (isAscending()) {
            return;
        }
        sortAsc(0);
    }

    public void sortDescending() {
        if (isDescending()) {
            return;
        }
        sortDes(0);
    }

    private void constructArray(String input, int i){
            int ind = findComma(input, i);
            if (ind == -1) {
                append(Integer.parseInt(input.substring(i)));
                return;
            }else{
                append(Integer.parseInt(input.substring(i, ind)));
            }
            constructArray(input, ind + 1);
        
    }

    private int findComma(String input, int index){
        if(input.charAt(index) == ','){
            return index;
        }else if(index >= input.length() - 1){
            return -1;
        }
        return findComma(input, index + 1);
    }

    private Integer[] createArray(Integer[] newArray,int i){
        if (i == array.length) {
            return newArray;
        }
        newArray[i] = array[i];
        return createArray(newArray, i+1);
        
    }

    private Integer[] createArrayPre(Integer value, Integer[] newArray,int i){
        if (i == 1) {
            newArray[0] = value;
        }
        
        if (i == array.length + 1) {
            return newArray;
        }

        newArray[i] = array[i - 1];
        return createArrayPre(value, newArray, i+1);
        
    }
    
    private boolean findInt(Integer val,int i){
        if (i >= array.length) {
            return false;
        }
        else if (array[i] == val) {
            return true;
        }
        return findInt(val, i + 1);
    }

    private boolean checkAscending(Integer first, Integer second, int i, boolean check){
        if (check == false) {
            return check;
        }else if(i == array.length - 2){
            if (first <= second) {
                return check;
            }
            return false;
        }

        if (first <= second) {
            first = second;
            second = array[i + 2];
            return checkAscending(first, second, i + 1, check);
        }
        return false;

        
    }

    private boolean checkDescending(Integer first, Integer second, int i, boolean check){
        if (check == false) {
            return check;
        }else if(i == array.length - 2){
            if (first >= second) {
                return check;
            }
            return false;
        }

        if (first >= second) {
            first = second;
            second = array[i + 2];
            return checkDescending(first, second, i + 1, check);
        }
        return false;
    }

    private void sortAsc(int i){
        int swops = oneAscend(i, array.length);
        
        if (swops >= 1) {
            sortAsc(0);
        }
    }

    private int oneAscend(int i, int length){
        int num = 0;
        if (length < 2) {
            return num;
        }
        
        if (array[i] > array[i+1]) {
            Integer temp = array[i];
            array[i] = array[i + 1];
            array[i+1] = temp;
            num += 1;
        }
            
        num += oneAscend(i+1 , length -1);
        return num;
        
    }

    private void sortDes(int i){
        
        int swops = oneDescend(i, array.length);
        
        if (swops >= 1) {
            sortDes(0);
        }
        
        
    }

    private int oneDescend(int i, int length){
        int num = 0;
        if (length < 2) {
            return num;
        }
        
        if (array[i] < array[i+1]) {
            Integer temp = array[i];
            array[i] = array[i + 1];
            array[i+1] = temp;
            num += 1;
        }
            
        num += oneDescend(i+1 , length -1);
        return num;
        
        
    }

    private String print(int i, String str){
        if (i == array.length) {
            return str;
        }

        return print(i + 1, str + "," + array[i]);
    }
}
