import java.util.ArrayList;
import java.util.Scanner;

public class Activity {
    public static void main(String[] args) {
        Scanner info = new Scanner(System.in);

        // Prompt user for dimensions
        System.out.print("Enter number of rows for your 2D ArrayList: ");
        int numRows = info.nextInt();
        System.out.print("Enter number of columns for your 2D ArrayList: ");
        int numCols = info.nextInt();

        // Create 2D ArrayList
        ArrayList<ArrayList<Integer>> arrayList2D = new ArrayList<>();

        // Populate the 2D ArrayList
        for (int i = 0; i < numRows; i++) {
            arrayList2D.add(new ArrayList<>()); // Add a new row
            for (int j = 0; j < numCols; j++) {
                System.out.print("Enter value for position (" + i + ", " + j + "): ");
                int value = info.nextInt();
                arrayList2D.get(i).add(value);
            }
        }

        // Print the 2D ArrayList
        System.out.println("Your 2D ArrayList:");
        for (ArrayList<Integer> row : arrayList2D) {
            System.out.println(row);
        }


        for(int i = 0; i < numRows;i++){
            int rowSum = 0;
            for(int val : arrayList2D.get(i)){
                rowSum += val;
            }
            System.out.println("Sum of row " + i + ":" + rowSum);
        }


        for(int i = 0; i< numRows;i++){
            int colSum = 0;
            for(int j = 0; j < numCols; j++){
                colSum += arrayList2D.get(j).get(i);
            }
            System.out.println("Sum of column " + i + ":" + colSum);
        }

    }
}