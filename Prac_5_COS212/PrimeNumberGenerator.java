public class PrimeNumberGenerator {
    PrimeNode head;

    @Override
    public String toString() {
        String res = head.toString();
        PrimeNode ptr = head.next;
        while (ptr != null) {
            res += "->" + ptr.toString();
            ptr = ptr.next;
        }
        return res;
    }

    public PrimeNumberGenerator() {
        head = new PrimeNode(2);
    }

    public int currentPrime() {
        return head.value;
    }

    public int nextPrime() {
        if (head.next == null) {
            sieveOfEratosthenes();
        }
        head = head.next;
        return head.value;
    }

    public void sieveOfEratosthenes() {
        int len = head.value*2+1;
        boolean notPrime[] = new boolean[len];

        int jump = 2;
        while(jump < len){
            int counter = jump+jump;
            while (counter < len) {
                notPrime[counter] = true;
                counter += jump;
            }
            jump ++;
        }

        PrimeNode ptr = head;
        while(ptr.next != null){
            ptr = ptr.next;
        }
        for (int i = ptr.value; i < len; i++) {
            if (notPrime[i] == false) {
                if(!head.contains(head, i)){
                    ptr.next = new PrimeNode(i);
                    ptr = ptr.next;
                }
                    
            }
        }
    }

}
