import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {
    ArrayList<ArrayList<Integer>> arrayListTwoD = new ArrayList<>();
    int row;
    int column;

    public Matrix(int row, int column, ArrayList<ArrayList<Integer>> arrayListTwoD){
        this.row = row;
        this.column = column;
        this.arrayListTwoD = arrayListTwoD;
    }

    public int sumOfRow(int row, ArrayList<ArrayList<Integer>> arrayListTwoD){
        for(int i = 0; i < row;i++){
            int rowSum = 0;
            for(int val : arrayListTwoD.get(i)){
                rowSum += val;
            }
            System.out.println("Sum of row " + i + ":" + rowSum);
        }
    }

    public int sumOfColumn(int column,int row, ArrayList<ArrayList<Integer>> arrayListTwoD){
        for(int i = 0; i< row;i++){
            int colSum = 0;
            for(int j = 0; j < column; j++){
                colSum += arrayListTwoD.get(j).get(i);
            }
            System.out.println("Sum of column " + i + ":" + colSum);
        }
    }

    public int values(int row, int column, ArrayList<ArrayList<Integer>> arrayListTwoD){
        Scanner info = new Scanner(System.in);
        for (int i = 0; i < row; i++) {
            arrayListTwoD.add(new ArrayList<>()); // Add a new row
            for (int j = 0; j < column; j++) {
                System.out.print("Enter value for position (" + i + ", " + j + "): ");
                int value = info.nextInt();
                arrayListTwoD.get(i).add(value);
            }
        }
        System.out.println("Your 2D ArrayList:");
        for (ArrayList<Integer> line : arrayListTwoD) {
            System.out.println(line);
        }
    }
}
