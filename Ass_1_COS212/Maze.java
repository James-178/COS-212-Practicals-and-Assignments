import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Maze {
    private String[] map;

    public Maze(String filename) {
        try {
            FileReader file = new FileReader(filename);
            Scanner s1 = new Scanner(file);

            int lines = Integer.parseInt(s1.nextLine());
            map = new String[lines];
            populateMap(s1, 0, lines);
            s1.close();
        }
        catch(FileNotFoundException e){
            map = new String[0];
        }
    }

    public Maze(Maze other) {
        if (other.map.length == 0) {
            map = new String[0];
        }
        map =  new String[other.map.length];
        copy(other, 0);
    }

    @Override
    public String toString() {
        if (map.length == 0) {
            return "Empty Map";
        }
        String str = map[0];
        return print(str, 1);
    }

    public boolean validSolution(int startX, int startY, int goalX, int goalY, LinkedList path) {
        if (path.head == null) {
            return false;
        }
        if (startY < 0 || startY >= map.length) {
            return false;
        }
        if (startX < 0 || startX >= map[startY].length()) {
            return false;
        }
        if (goalY < 0 || goalY >= map.length) {
            return false;
        }
        if (goalX < 0 || goalX >= map[goalY].length()) {
            return false;
        }
        if (path.head.x != startX || path.head.y != startY || path.head == null) {
            return false;
        }
        if (map[startY].charAt(startX) == 'X' || map[goalY].charAt(goalX) == 'X') {
            return false;
        }
        
        CoordinateNode ptr = path.head;
        int[] alreadyCoveredX = new int[path.length()];
        int[] alreadyCoveredY = new int[path.length()];
        return valid(ptr, goalX, goalY, alreadyCoveredX, alreadyCoveredY, 0);//might need to change this

    }

    public String solve(int x, int y, int goalX, int goalY) {
        if (y < 0 || y >= map.length) {
            return "No valid solution exists";
        }
        if (x < 0 || x >= map[y].length()) {
            return "No valid solution exists";
        }
        if (goalY < 0 || goalY >= map.length) {
            return "No valid solution exists";
        }
        if (goalX < 0 || goalX >= map[goalY].length()) {
            return "No valid solution exists";
        }
        if (map[y].charAt(x) == 'X' || map[goalY].charAt(goalX) == 'X') {
            return "No valid solution exists";
        }

        String[] newMapOutput = new String[map.length];
        String[] newMap = newMap(newMapOutput, 0, map);

        newMap[y] = newMap[y].substring(0,x) + "S" + newMap[y].substring(x+1);
        newMap[goalY] = newMap[goalY].substring(0,goalX) + "E" + newMap[goalY].substring(goalX+1);
        //check if goal = start
        LinkedList solution = new LinkedList();
        if (x == goalX && y == goalY) {
            solution.append(goalX, goalY);
            return "Solution\n" + printSol(newMap, 0, "")+"\n"+solution;
        }
        
        LinkedList prevSteps = new LinkedList();
        String[] solvedMaze = solveRec(x,y,goalX,goalY,newMap, solution, prevSteps); 
        if (solvedMaze[goalY].charAt(goalX) == 'F') {
            return "No valid solution exists";
        }
        solvedMaze = replace(solvedMaze, 0);
        solution.append(goalX,goalY);
        return "Solution\n" + printSol(solvedMaze, 0, "")+"\n"+solution;
    }

    private String[] replace(String[] arr, int i){
        if (i == arr.length) {
            return arr;
        }
        arr[i] = arr[i].replace("O", "-");
        return replace(arr, i+1);
    }

    private String printSol(String[] solvedMaze, int i, String s) {
        if (i == solvedMaze.length) {
            return s;
        }
        s = (i == solvedMaze.length-1) ?  s+solvedMaze[i] : s+solvedMaze[i] + "\n";
        
        return printSol(solvedMaze, i + 1, s);
    }


    private String[] solveRec(int x, int y, int goalX, int goalY, String[] newMap, LinkedList solution, LinkedList prevSteps) {       
         System.out.println(printSol(newMap, 0, ""));
         System.out.println();
        boolean check = false;

        if (check == false && (x-1) >= 0) { //check left
    
            if (newMap[y].charAt(x-1) == 'E') {
                if (newMap[y].charAt(x) == '-') {
                    newMap[y] = newMap[y].substring(0,x) + "@" + newMap[y].substring(x+1);
                }
                solution.append(x, y);
                return newMap;
            }

            if (newMap[y].charAt(x-1) == '-') {
                check = true;
                if (newMap[y].charAt(x) == '-') {
                    newMap[y] = newMap[y].substring(0,x) + "@" + newMap[y].substring(x+1);
                }
                solution.append(x, y);
                prevSteps.append(x, y);
                x = x-1;
            }
        }
        if (check == false && (y-1)>=0) {//check up

            if ( newMap[y-1].length()-1 >= x) {
                
                if (newMap[y-1].charAt(x) == 'E') {
                    if (newMap[y].charAt(x) == '-') {
                        newMap[y] = newMap[y].substring(0,x) + "@" + newMap[y].substring(x+1);
                    }
                    solution.append(x, y);
                    return newMap;
                }
                if (newMap[y-1].charAt(x) == '-') {
                    check = true;
                    if (newMap[y].charAt(x) == '-') {
                        newMap[y] = newMap[y].substring(0,x) + "@" + newMap[y].substring(x+1);
                    }
                    solution.append(x, y);
                    prevSteps.append(x, y);
                    y = y-1;
                }
                
            }

            
        }

        if (check == false && (y + 1) < newMap.length) {//check below

            if ( newMap[y+1].length()-1 >= x) {

                if (newMap[y+1].charAt(x) == 'E') {
                    if (newMap[y].charAt(x) == '-') {
                        newMap[y] = newMap[y].substring(0,x) + "@" + newMap[y].substring(x+1);
                    }
                    solution.append(x, y);
                    return newMap;
                }
                if (newMap[y+1].charAt(x) == '-') {
                    check = true;
                    if (newMap[y].charAt(x) == '-') {
                        newMap[y] = newMap[y].substring(0,x) + "@" + newMap[y].substring(x+1);
                    }
                    solution.append(x, y);
                    prevSteps.append(x, y);
                    y = y+1;
                }
            }
        }

        if (check == false && (x+1) < newMap[y].length()) {//check right
            if (newMap[y].charAt(x+1) == 'E') {
                if (newMap[y].charAt(x) == '-') {
                    newMap[y] = newMap[y].substring(0,x) + "@" + newMap[y].substring(x+1);
                }
                solution.append(x, y);
                return newMap;
            }

            if (newMap[y].charAt(x+1) == '-') {
                check = true;
                if (newMap[y].charAt(x) == '-') {
                    newMap[y] = newMap[y].substring(0,x) + "@" + newMap[y].substring(x+1);
                }
                solution.append(x, y);
                prevSteps.append(x, y);
                x = x+1;
            }
        }
        if (check == false) {

            if(prevSteps.head != null){
                newMap[y] = newMap[y].substring(0,x) + "O" + newMap[y].substring(x+1);
                x = prevSteps.reversed().head.x;
                y = prevSteps.reversed().head.y;
                prevSteps.removeLast();
                solution.removeLast();
            }else{
                newMap[goalY] = newMap[goalY].substring(0,goalX) + "F" + newMap[goalY].substring(goalX+1);
                return newMap;
            }
        }
        return solveRec(x, y, goalX, goalY, newMap, solution, prevSteps); 
    }
    
    private String[] newMap(String[] newMap,  int i, String[] oldMap) {
        if (i == oldMap.length) {
            return newMap;
        }
        newMap[i] = oldMap[i];
        return newMap(newMap, i+1, oldMap);
    }

    public LinkedList validStarts(int x, int y) {
        LinkedList validValues = new LinkedList();
        addValues(validValues, 0, x, y);
        return validValues;
    }

    private void addValues(LinkedList validValues, int i, int x, int y) {
        if (map.length == i) {
            return;
        }
        addValuesX(validValues, 0, i, map[i].length(), x, y);
        addValues(validValues, i+1, x, y);
    }

    private void addValuesX(LinkedList validValues, int j, int i, int length, int x, int y) {
        if (j == length) {
            return;
        }
        String check = solve(j, i, x, y);
        if (check.equals("No valid solution exists") == false ) {
            validValues.append(j, i);
        }
        addValuesX(validValues, j+1, i, length, x, y);
    }

    private void populateMap(Scanner s, int i, int length){
        if (i == length) {
            return;
        }
        map[i] = s.nextLine();
        populateMap(s, i+1, length);
    }

    private void copy(Maze other, int i){
        if (other.map.length == i) {
            return;
        }
        map[i] = other.map[i];
        copy(other, i+1);
    }

    private String print(String str, int i){
        if (i == map.length) {
            return str;
        }
        str += "\n" + map[i];
        return print(str, i+1);
    }

    private boolean valid(CoordinateNode ptr, int goalX, int goalY, int[] covX, int[] covY, int i){
        if (ptr.y >= map.length || ptr.x >= map[ptr.y].length()) {
            return false;
        }

        if (ptr.y < 0 || ptr.x < 0) {
            return false;
        }

        if (map[ptr.y].charAt(ptr.x) == 'X') {
            return false;
        }
        covX[i] = ptr.x;
        covY[i] = ptr.y;
        boolean db = loubleBack(ptr, covX, covY, 0, i);
        
        if (db) {
            return false;
        }
        

        if (ptr.next != null) {
            if (ptr.x != ptr.next.x && ptr.y != ptr.next.y) {
                return false;
            }

            if (ptr.x == ptr.next.x && Math.abs(ptr.y - ptr.next.y) != 1) {
                return false;
            }
            if (ptr.y == ptr.next.y && Math.abs(ptr.x - ptr.next.x) != 1) {
                return false;
            }
        }

        if (ptr.x == goalX && ptr.y == goalY && ptr.next == null) {
            return true;
        }
    
        if (ptr.next == null) {
            return false;
        }
        
        return valid(ptr.next, goalX, goalY, covX, covY, i + 1);
    }

    private boolean loubleBack(CoordinateNode ptr, int[] covX, int[] covY, int i, int currLength){
        if (i == currLength) {
            return false;
        }
        //System.out.println("made it" + i +" "+ covX[i]+" "+covY[i]);
        if (ptr.x == covX[i] && ptr.y == covY[i]) {
            return true;
        }
        
        return loubleBack(ptr, covX, covY, i+1, currLength);
    }
}