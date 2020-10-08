package sort;

import java.util.Arrays;
import static java.lang.System.out;

/**
 * 堆排序是指利用堆这种数据结构所设计的一种排序算法。
 * 堆是一个完全二叉树的结构，并同时满足堆的性质：即子节点的键值总是小于（或者大于）它的父节点。
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3,5,1,4,2};
        heapSort(arr);
        out.println(Arrays.toString(arr));
    }
    private static void heapSort(int[] arr){
        for(int i = arr.length/2 -1; i >= 0; i--){
            maxHeapify(arr, i, arr.length-1);
        }
        for(int i = arr.length-1; i > 0; i--){
            swap(arr, 0, i);
            maxHeapify(arr, 0, i-1);
        }
    }
    private static void maxHeapify(int[] arr, int start, int end){
        int parent = start;
        int child = 2*start+1;
        while (child <= end){
            if(child+1 <= end && arr[child] < arr[child+1]){
                child++;
            }
            if(arr[parent] > arr[child]){
                return;
            }else {
                swap(arr, parent, child);
                parent = child;
                child = 2*parent+1;
            }
        }
    }
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
