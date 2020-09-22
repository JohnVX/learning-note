/*
 * 7 0 0 0 0 0 6 3 0
 * 0 0 2 0 3 0 0 7 4
 * 0 6 8 1 0 4 0 0 0
 * 4 0 0 0 0 1 3 9 0
 * 9 5 0 3 0 0 4 0 0
 * 2 0 7 5 0 0 0 6 0
 * 0 4 3 0 9 0 0 0 6
 * 0 0 0 0 1 7 0 0 0
 * 0 0 0 8 0 0 2 0 9
 *
 *
 * 7 1 4 9 5 2 6 3 8
 * 5 9 2 6 3 8 1 7 4
 * 3 6 8 1 7 4 9 5 2
 * 4 8 6 7 2 1 3 9 5
 * 9 5 1 3 8 6 4 2 7
 * 2 3 7 5 4 9 8 6 1
 * 8 4 3 2 9 5 7 1 6
 * 6 2 9 4 1 7 5 8 3
 * 1 7 5 8 6 3 2 4 9
 *
 *
 * 4 0 0 0 5 0 6 0 0
 * 0 0 8 7 0 0 0 9 4
 * 9 0 6 0 8 0 0 0 0
 * 0 8 0 0 4 1 0 7 0
 * 0 1 0 0 0 0 5 4 0
 * 7 0 0 9 3 5 0 0 0
 * 8 3 0 0 0 2 0 0 7
 * 0 9 1 0 0 7 0 0 2
 * 0 0 0 8 0 0 9 3 0
 *
 * 4 7 3 1 5 9 6 2 8
 * 1 5 8 7 2 6 3 9 4
 * 9 2 6 4 8 3 7 5 1
 * 3 8 5 6 4 1 2 7 9
 * 6 1 9 2 7 8 5 4 3
 * 7 4 2 9 3 5 8 1 6
 * 8 3 4 5 9 2 1 6 7
 * 5 9 1 3 6 7 4 8 2
 * 2 6 7 8 1 4 9 3 5
 *
 *
 * 0 0 9 0 0 0 0 3 6
 * 3 0 0 0 7 2 0 0 0
 * 0 7 0 0 0 5 0 0 8
 * 0 0 0 8 0 0 4 0 0
 * 0 0 3 4 1 0 0 6 0
 * 0 1 0 0 0 0 0 0 5
 * 0 0 4 0 0 0 6 7 0
 * 0 0 0 0 0 4 1 5 0
 * 9 0 0 0 0 0 0 0 0
 *
 * 5 2 9 1 4 8 7 3 6
 * 3 4 8 6 7 2 5 9 1
 * 6 7 1 9 3 5 2 4 8
 * 2 9 6 8 5 3 4 1 7
 * 8 5 3 4 1 7 9 6 2
 * 4 1 7 2 9 6 3 8 5
 * 1 8 4 5 2 9 6 7 3
 * 7 6 2 3 8 4 1 5 9
 * 9 3 5 7 6 1 8 2 4
 */

import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class Sudoku {
    static class Range{
        int row, col, setIndex;
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return row == range.row &&
                    col == range.col &&
                    setIndex == range.setIndex;
        }
        @Override
        public int hashCode() {
            return Objects.hash(row, col, setIndex);
        }
        Range(int row, int col, int setIndex){
            this.row = row;
            this.col = col;
            this.setIndex = setIndex;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        int[][] arr = new int[9][9];
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                arr[row][col] = scanner.nextInt();
            }
        }
        Range[] save = new Range[81];
        List<Range> record = new ArrayList<>();
        int pos = 0;
        boolean breakAble;
        while (true){
            breakAble = true;
            for(int row = 0; row < 9; row++){
                for(int col = 0; col < 9; col++){
                    if (arr[row][col] == 0) {
                        breakAble = false;
                        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                        for (int i = 0; i < 9; i++) {
                            set.remove(arr[row][i]);
                            set.remove(arr[i][col]);
                        }
                        for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
                            for (int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
                                set.remove(arr[i][j]);
                            }
                        }
                        if (set.size() == 1) {
                            arr[row][col] = set.toArray(new Integer[0])[0];
                            record.add(new Range(row, col, 0));
                        } else if (set.size() > 1) {
                            if (pos > 0 && (save[pos - 1].row == row && save[pos - 1].col == col)) {
                                save[pos - 1].setIndex++;
                                if(save[pos - 1].setIndex >= set.size()){
                                    pos--;
                                    int[] r = backtrace(pos, arr, save, record);
                                    row = r[0];
                                    col = r[1];
                                }else {
                                    arr[row][col] = set.toArray(new Integer[0])[save[pos - 1].setIndex];
                                }
                            } else {
                                save[pos++] = new Range(row, col, 0);
                                arr[row][col] = set.toArray(new Integer[0])[save[pos - 1].setIndex];
                            }
                        } else {
                            //set size == 0
                            int[] r = backtrace(pos, arr, save, record);
                            row = r[0];
                            col = r[1];
                        }
                    }
                }
            }
            if(breakAble){
                break;
            }
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                out.print(arr[i][j] + " ");
            }
            out.println();
        }
    }
    private static int[] backtrace(int pos, int[][] arr, Range[] save, List<Range> record){
        int row = save[pos - 1].row;
        int col = save[pos - 1].col;
        arr[row][col] = 0;
        for(int i = row; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(i*9+j > (row*9+col) && record.contains(new Range(i, j, 0))){
                    arr[i][j] = 0;
                    record.remove(new Range(i, j, 0));
                }
            }
        }
        if(col == 0){
            row--;
            col = 8;
        }else{
            col--;
        }
        return new int[]{row, col};
    }
}


