import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class SudokuRecursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        int[][] arr = new int[9][9];
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++){
                arr[row][col] = scanner.nextInt();
            }
        }
        if(process(arr, 0 , 0)){
            for (int[] anArr : arr) {
                for (int anAnArr : anArr) {
                    out.print(anAnArr + " ");
                }
                out.println();
            }
        }
    }
    private static boolean process(int[][] arr, int i, int j){
        if(j > 8){
            return process(arr, i+1, 0);
        }
        if(i == 9){
            return true;
        }
        if(arr[i][j] != 0){
            return process(arr, i, j+1);
        }
        for(int k = 1; k <=9; k++){
            if(!check(arr, i, j, k)) continue;
            arr[i][j] = k;
            if(process(arr, i, j+1)) return true;
            arr[i][j] = 0;
        }
        return false;
    }
    private static boolean check(int[][] arr, int row, int col, int num){
        for(int i = 0; i < 9; i++){
            if(arr[row][i] == num) return false;
            if(arr[i][col] == num) return false;
            if(arr[(row/3)*3 + (i/3)][(col/3)*3 + (i%3)] == num) return false;
        }
        return true;
    }
}
