public class Hashmap {
    public KeyValuePair[] array;
    public PrimeNumberGenerator prime = new PrimeNumberGenerator();

    @Override
    public String toString() {
        String res = String.valueOf(prime.currentPrime()) + "\n";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += "\n";
            }
            res += i + "\t";
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res;
    }

    public String toStringOneLine() {
        String res = String.valueOf(prime.currentPrime()) + "[";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += ",";
            }
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res + "]";
    }

    public Hashmap() {
        array = new KeyValuePair[1];
    }
    //2[-,u5:50%]
    public Hashmap(String inp) {
        String inpArr[] = inp.split("\\[");
        inpArr[1] = inpArr[1].substring(0,inpArr[1].length()-1);
        int p = Integer.parseInt(inpArr[0]);

        while(prime.currentPrime() < p){
            prime.nextPrime();
        }

        String arr[] = inpArr[1].split(",");
        array = new KeyValuePair[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals("-") ){
                array[i] = null;
                
            }else{
                int sNum = Integer.parseInt(arr[i].substring(1, arr[i].indexOf(":")));
                int m = Integer.parseInt(arr[i].substring(arr[i].indexOf(":")+1 , arr[i].length()-1));
                array[i] = new KeyValuePair(sNum, m);
            }
            
        }

    }

    public int hash(int studentNumber) {
        int hashVal = 0;
        String sNum = String.valueOf(studentNumber);
        String nums[] = sNum.split("");
        for (int i = 0; i < nums.length; i++) {
            hashVal = prime.currentPrime() * hashVal + Integer.parseInt(nums[i]);
        }

        if (hashVal < 0) {
            hashVal = Math.abs(hashVal);
        }

        return hashVal %= array.length;
    }

    public KeyValuePair search(int studentNumber) {
        int ind = hash(studentNumber);
        if (array[ind] == null || array[ind].studentNumber != studentNumber) {
            
            for (int i = 1; i < 4; i++) {
                int newHash = Math.abs(ind + (i*i) * prime.currentPrime()) % array.length;
                if (array[newHash] == null || array[newHash].studentNumber != studentNumber) {
                    newHash = Math.abs(ind - (i*i) * prime.currentPrime()) % array.length;
                    if (array[newHash] != null && array[newHash].studentNumber == studentNumber) {
                        return array[newHash];
                    }
                }else{
                    return array[newHash];
                }
            }
        }else{
            return array[ind];
        }
        return null;
    }

    public void insert(int studentNumber, int mark) {
        KeyValuePair s = search(studentNumber);
        int ind = hash(studentNumber);
        if (s == null) {
            if (array[ind] == null) {
                array[ind] = new KeyValuePair(studentNumber, mark);
                return;
            }else{
                for (int i = 1; i < 4; i++) {
                    int newHash = Math.abs(ind + (i*i) * prime.currentPrime()) % array.length;
                    if (array[newHash] == null) {
                        array[newHash] = new KeyValuePair(studentNumber, mark);
                        return;
                    }else{
                        newHash = Math.abs(ind - (i*i) * prime.currentPrime()) % array.length;
                        if (array[newHash] == null) {
                            array[newHash] = new KeyValuePair(studentNumber, mark);
                            return;
                        }
                    }
                        
                }
            }
        }else if(s != null){
            if (array[ind] != null && array[ind].studentNumber == studentNumber) {
                array[ind].mark = mark;
                return;
            }else{
                for (int i = 1; i < 4; i++) {
                    int newHash = Math.abs(ind + (i*i) * prime.currentPrime()) % array.length;
                    if (array[newHash] != null && array[newHash].studentNumber == studentNumber) {
                        array[newHash].mark = mark;
                        return; 
                    }else{
                        newHash = Math.abs(ind - (i*i) * prime.currentPrime()) % array.length;
                        if (array[newHash] != null && array[newHash].studentNumber == studentNumber) {
                            array[newHash].mark = mark;
                            return;
                        }
                    }
                }
            }
        }

        KeyValuePair temp[] = new KeyValuePair[array.length];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = new KeyValuePair[array.length*2];
        prime.nextPrime();
        for (int i = 0; i < temp.length; i++) {
            if(temp[i] != null){
                insert(temp[i].studentNumber, temp[i].mark);
            }
        }

        insert(studentNumber, mark);
    }

    public void remove(int studentNumber) {
        KeyValuePair s = search(studentNumber);
        int ind = hash(studentNumber);
        if (s == null) {
            return;
        }
        if (array[ind] == null || array[ind].studentNumber != studentNumber) {
            
            for (int i = 1; i < 4; i++) {
                int newHash = Math.abs(ind + (i*i) * prime.currentPrime()) % array.length;
                if (array[newHash] == null || array[newHash].studentNumber != studentNumber) {
                    newHash = Math.abs(ind - (i*i) * prime.currentPrime()) % array.length;
                    if (array[newHash] != null && array[newHash].studentNumber == studentNumber) {
                        array[newHash] = null;
                        return;
                    }
                }else{
                    array[newHash] = null;
                    return;
                }
            }
        }else{
            array[ind] = null;
            return;
        }
    }
}
