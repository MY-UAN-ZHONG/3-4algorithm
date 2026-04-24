public class Assignment1 {

    // 1. Linear Scan (Array Sum) -> 預期 O(n)
    public static long linearScan(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    // 2. Insertion Sort -> 預期 O(n^2)
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 3. Merge Sort -> 預期 O(n log n)
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // 自製輔助方法：產生隨機陣列 (不使用 java.util.Random)
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (System.nanoTime() % 100000); 
        }
        return arr;
    }

    // 自製輔助方法：複製陣列 (確保每次測試的初始狀態相同)
    public static int[] copyArray(int[] source) {
        int[] dest = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            dest[i] = source[i];
        }
        return dest;
    }

    public static void main(String[] args) {
        int[] sizes = {10000, 50000, 100000};

        System.out.println("Assignment 1: Empirical Observation of Time Complexity (ms)\n");
        System.out.printf("%-10s | %-15s | %-15s | %-15s\n", "Size (n)", "Linear Scan", "Insertion Sort", "Merge Sort");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < sizes.length; i++) {
            int n = sizes[i];
            int[] original = generateRandomArray(n);

            // 測量 Linear Scan
            int[] arr1 = copyArray(original);
            long start1 = System.nanoTime();
            linearScan(arr1);
            double t1 = (System.nanoTime() - start1) / 1000000.0;

            // 測量 Insertion Sort
            int[] arr2 = copyArray(original);
            long start2 = System.nanoTime();
            insertionSort(arr2);
            double t2 = (System.nanoTime() - start2) / 1000000.0;

            // 測量 Merge Sort
            int[] arr3 = copyArray(original);
            long start3 = System.nanoTime();
            mergeSort(arr3, 0, arr3.length - 1);
            double t3 = (System.nanoTime() - start3) / 1000000.0;

            System.out.printf("%-10d | %-15.4f | %-15.4f | %-15.4f\n", n, t1, t2, t3);
        }
    }
}