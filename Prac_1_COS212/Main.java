public class Main {
    public static void main(String[] args) {
        RecursiveArray arr1 = new RecursiveArray();
        System.out.println(arr1);

        RecursiveArray arr2 = new RecursiveArray("1,2,3,4");
        System.out.println(arr2.array[0]);
        System.out.println(arr2.array[1]);
        System.out.println(arr2.array[2]);
        System.out.println(arr2.array[3]);
        System.out.println(arr2);
        System.out.println(arr2.isAscending());
        System.out.println(arr2.isDescending());
        System.out.println(arr2.contains(10));
        System.out.println(arr2.contains(1));
        arr2.sortDescending();
        System.out.println(arr2);
        arr2.sortAscending();
        System.out.println(arr2);
        arr2.append(5);
        System.out.println(arr2);
        arr2.prepend(6);
        System.out.println(arr2);
    }
}