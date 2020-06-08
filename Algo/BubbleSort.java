import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] T = new int[]{6,3,8,1,2,9};
        sort(T);
        System.out.println(Arrays.toString(T));
    }
    public static void sort(int[] T){
        for(int i = 0; i < T.length-1; i++)
            for(int j = 0; j < T.length-i-1; j++){
                if(T[j] > T[j+1])
                    swap(T, j, j+1);
            }
    }
    private static void swap(int[] T, int i, int j){
        int temp = T[j];
        T[j] = T[i];
        T[i] = temp;
    }
}
