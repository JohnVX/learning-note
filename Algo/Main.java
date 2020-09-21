import java.util.*;

import static java.lang.System.in;

public class Main {
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
                            if (pos > 0 && (save[pos - 1].row == row && save[pos - 1].col == col)) {
                                pos--;
                            }
                        } else if (set.size() > 1) {
                            if (pos > 0 && (save[pos - 1].row == row && save[pos - 1].col == col)) {
                                save[pos - 1].setIndex++;
                            } else {
                                save[pos++] = new Range(row, col, 0);
                            }
                            arr[row][col] = set.toArray(new Integer[0])[save[pos - 1].setIndex];
                        } else {
                            //set size == 0
                            row = save[pos - 1].row;
                            col = save[pos - 1].col;
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
                        }
                    }
                }
            }
            if(breakAble){
                break;
            }
        }
    }
}

//        Scanner scanner = new Scanner(in);
//        while (scanner.hasNext()){
//            int num = scanner.nextInt();
//            List<Integer> list  = new ArrayList<>();
//            for(int i = 0; i < num; i++){
//                list.add(i);
//            }
//            int index = 0;
//            while (list.size() > 1){
//                index = (index+2) % list.size();
//                list.remove(index);
//            }
//            out.println(list.get(0));
//        }


//        Scanner scanner = new Scanner(in);
//        while (scanner.hasNext()){
//            Set<Character> set = new LinkedHashSet<>();
//            for(char c : scanner.next().toCharArray()){
//                set.add(c);
//            }
//            for(char c: set){
//                out.print(c);
//            }
//            out.println();
//        }


