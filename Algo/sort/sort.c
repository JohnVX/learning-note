/**
 * 几种排序方法的实现:
 * 冒泡排序、选择排序、插入排序、希尔排序、归并排序、快速排序
 *
 * Author: 王书伟
 * 2020/9/11
 */

#include <malloc.h>
#include "sort.h"

void bubble_sort(int arr[], int len){
    for(int i = 0; i < len-1; i++){
        for(int j = 0; j < len-1-i; j++){
            if(arr[j] > arr[j+1]){
                int tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;
            }
        }
    }
}

void selection_sort(int arr[], int len){
    for(int i = 0; i < len-1; i++){
        int min_index = i;
        for(int j = i+1; j < len; j++){
            if(arr[j] < arr[min_index]){
                min_index = j;
            }
        }
        int tmp = arr[i];
        arr[i] = arr[min_index];
        arr[min_index] = tmp;
    }
}

void insertion_sort(int arr[], int len){
    for(int i = 1; i < len; i++){
        int tmp = arr[i];
        int j;
        for(j = i; j > 0 && arr[j-1] > tmp; j--){
            arr[j] = arr[j-1];
        }
        arr[j] = tmp;
    }
}

void shell_sort(int arr[], int len) {
    for (int gap = len / 2; gap > 0; gap = gap / 2) {
        for (int i = gap; i < len; i++) {
            int temp = arr[i];
            int j;
            for (j = i - gap; j >= 0 && arr[j] > temp; j -= gap) {
                arr[j + gap] = arr[j];
            }
            arr[j + gap] = temp;
        }
    }
}
static void merge_sort_recursive(int arr[], int arr_extra[], int start, int end){
    if(start >= end){
        return;
    }
    int mid = (end-start) / 2 + start;
    int start1 = start, end1 = mid;
    int start2 = mid+1;
    int end2 = end;
    merge_sort_recursive(arr, arr_extra, start1, end1);
    merge_sort_recursive(arr, arr_extra, start2, end2);
    int k = start;
    while (start1 <= end1 && start2 <= end2){
        arr_extra[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
    }
    while (start1 <= end1){
        arr_extra[k++] = arr[start1++];
    }
    while (start2 <= end2){
        arr_extra[k++] = arr[start2++];
    }
    for(k = start; k <= end; k++){
        arr[k] = arr_extra[k];
    }
}
#define MIN(x, y) ((x) < (y) ? (x) : (y))
static void merge_sort_iterative(int arr[], int len){
    int *a = arr;
    int *b = (int *)malloc(len * sizeof(int));
    for(int step = 1; step < len; step += step){
        for(int start = 0; start < len; start += (step+step)){
            int low = start, mid = MIN(start+step, len), high = MIN(start+step+step, len);
            int k = low;
            int start1 = low, end1 = mid;
            int start2 = mid, end2 = high;
            while(start1 < end1 && start2 < end2){
                b[k++] = a[start1] < a[start2] ? a[start1++]: a[start2++];
            }
            while(start1 < end1){
                b[k++] = a[start1++];
            }
            while(start2 < end2){
                b[k++] = a[start2++];
            }
        }
        int *tmp = a;
        a = b;
        b = tmp;
    }
    if(a != arr){
        for(int i = 0; i < len; i++){
            b[i] = a[i];
        }
        b = a;
    }
    free(b);
}
void merge_sort(int arr[], int len){
//    int arr_extra[len];
//    merge_sort_recursive(arr, arr_extra, 0, len-1);
    merge_sort_iterative(arr, len);
}
static void quick_sort_recursive(int arr[], int start, int end){
    if(start >= end){
        return;
    }
    int mid = arr[end];
    int left = start, right = end-1;
    while (left < right){
        while(arr[left] < mid && left < right){
            left++;
        }
        while (arr[right] >= mid && left < right){
            right--;
        }
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
    if(arr[left] >= arr[end]){
        arr[end] = arr[left];
        arr[left] = mid;
    } else{
        left++;
    }
    quick_sort_recursive(arr, start, left-1);
    quick_sort_recursive(arr, left+1, end);
}
typedef struct {
    int start, end;
} Range;
static Range new_range(int s, int e) {
    Range r;
    r.start = s;
    r.end = e;
    return r;
}
static void quick_sort_iterative(int arr[], int len){
    if (len <= 0)
        return;
    //r[] 是用来取代递归调用的栈
    Range r[len];
    int p = 0;
    r[p++] = new_range(0, len - 1);
    while (p) {
        Range range = r[--p];
        if (range.start >= range.end)
            continue;
        int mid = arr[(range.start + range.end) / 2];
        int left = range.start, right = range.end;
        do{
            while (arr[left] < mid) ++left;
            while (arr[right] > mid) --right;
            if (left <= right){
                int tmp = arr[right];
                arr[right] = arr[left];
                arr[left] = tmp;
                left++;
                right--;
            }
        } while (left <= right);
        if (range.start < right) r[p++] = new_range(range.start, right);
        if (range.end > left) r[p++] = new_range(left, range.end);
    }
}
void quick_sort(int arr[], int len){
    //quick_sort_recursive(arr, 0, len-1);
    quick_sort_iterative(arr, len);
}

