package string;

import java.util.Scanner;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * 最长公共子序列（LCS）是一个在一个序列集合中（通常为两个序列）用来查找所有序列中最长子序列的问题。
 * 这与查找最长公共子串的问题不同的地方是：子序列不需要在原序列中占用连续的位置 。
 * 最长公共子序列问题是一个经典的计算机科学问题，也是数据比较程序，比如Diff工具，和生物信息学应用的基础。
 * 它也被广泛地应用在版本控制，比如 Git用来调和文件之间的改变。
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()){
            String lineX = scanner.nextLine();
            String lineY = scanner.nextLine();
            int[][] lengthArr = LCS(lineX.toCharArray(), lineY.toCharArray());
            for(int[] arr : lengthArr) {
                for (int element : arr) {
                    out.print(element);
                }
                out.println();
            }
            int i = lineX.length(), j = lineY.length();
            int prev = -1;
            while (i != 0 && j != 0){
                if(lengthArr[i-1][j-1] == lengthArr[i][j]){
                    i--;j--;
                }else {
                    //lengthArr[i][j] == lengthArr[i-1][j-1] + 1
                    if(lineX.charAt(i-1) == lineY.charAt(j-1)){
                        if(lengthArr[i][j] != prev) {
                            out.print(lineX.charAt(i - 1));
                            prev = lengthArr[i][j];
                        }
                        i--;j--;
                    }else if(lengthArr[i][j-1] == lengthArr[i][j]){
                        if (lengthArr[i][j] != prev)  {
                            out.print(lineX.charAt(i - 1));
                            prev = lengthArr[i][j];
                        }
                        i--;
                    }else{
                        //lengthArr[i][j] == lengthArr[i-1][j]
                        if(lengthArr[i][j] != prev) {
                            out.print(lineY.charAt(j - 1));
                            prev = lengthArr[i][j];
                        }
                        j--;
                    }
                }
            }
        }
    }
    private static int[][] LCS(char[] X, char[] Y){
        int[][] lengthArr = new int[X.length+1][Y.length+1];
        for(int i = 0; i < X.length+1; i++){
            lengthArr[i][0] = 0;
        }
        for(int i = 0; i < Y.length+1; i++){
            lengthArr[0][i] = 0;
        }
        for(int i = 1; i < X.length+1; i++){
            for(int j = 1; j < Y.length+1; j++){
                if(X[i-1] == Y[j-1]){
                    lengthArr[i][j] = lengthArr[i-1][j-1]+1;
                }else {
                    lengthArr[i][j] = Math.max(lengthArr[i-1][j], lengthArr[i][j-1]);
                }
            }
        }
        return lengthArr;
    }
}
