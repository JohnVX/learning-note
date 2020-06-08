import java.util.Arrays;

/**
 * 快速排序方法
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] T = new int[]{23,6,3,8,1,2,9,21};
        sort(T, 0, T.length-1);
        System.out.println(Arrays.toString(T));
    }
    public static void sort(int[] T, int left, int right){
        if(right > left){
            int pivotIndex = (left + right)/2;
            int newPivotIndex = partition(T, left, right, pivotIndex);
            sort(T, left, newPivotIndex-1);
            sort(T, newPivotIndex+1, right);
        }
    }
    private static int partition(int[] T, int left, int right, int pivotIndex){
        int pivotValue = T[pivotIndex];
        swap(T, pivotIndex, right);
        int storeIndex = left;
        for(int i = left; i < right; i++){
            if(T[i] <= pivotValue) {
                swap(T, i, storeIndex);
                storeIndex++;
            }
        }
        swap(T, right, storeIndex);
        return storeIndex;
    }
    private static void swap(int[] T, int i, int j){
        int temp = T[j];
        T[j] = T[i];
        T[i] = temp;
    }
}
