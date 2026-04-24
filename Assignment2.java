public class Assignment2 {

    // ==========================================
    // 1. Insertion Sort -> 預期時間複雜度 O(n^2)
    // ==========================================
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

    // ==========================================
    // 2. Bubble Sort -> 預期時間複雜度 O(n^2)
    // ==========================================
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 優化：若某次掃描沒有發生任何交換，代表已排序完成，可提早結束
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // ==========================================
    // 3. Quick Sort -> 預期平均時間複雜度 O(n log n)
    // ==========================================
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // ==========================================
    // 輔助方法 (不依賴任何內建 Package)
    // ==========================================

    // 產生隨機陣列
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            // 利用 System.nanoTime() 產生簡單的偽隨機數
            arr[i] = (int) (System.nanoTime() % 100000); 
        }
        return arr;
    }

    // 手動複製陣列，確保每次排序的初始狀態完全相同
    public static int[] copyArray(int[] source) {
        int[] dest = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            dest[i] = source[i];
        }
        return dest;
    }

    // ==========================================
    // 主程式 (實驗測量區)
    // ==========================================
    public static void main(String[] args) {
        // 設定要測試的資料規模 n
        int[] sizes = {10000, 50000, 100000};

        System.out.println("Assignment 2: Empirical Observation of Sort Algorithms (Time in ms)\n");
        System.out.printf("%-10s | %-16s | %-16s | %-16s\n", "Size (n)", "Insertion Sort", "Bubble Sort", "Quick Sort");
        System.out.println("---------------------------------------------------------------------");

        for (int i = 0; i < sizes.length; i++) {
            int n = sizes[i];
            int[] original = generateRandomArray(n);

            // 測量 Insertion Sort
            int[] arr1 = copyArray(original);
            long start1 = System.nanoTime();
            insertionSort(arr1);
            double time1 = (System.nanoTime() - start1) / 1000000.0;

            // 測量 Bubble Sort
            int[] arr2 = copyArray(original);
            long start2 = System.nanoTime();
            bubbleSort(arr2);
            double time2 = (System.nanoTime() - start2) / 1000000.0;

            // 測量 Quick Sort
            int[] arr3 = copyArray(original);
            long start3 = System.nanoTime();
            quickSort(arr3, 0, arr3.length - 1);
            double time3 = (System.nanoTime() - start3) / 1000000.0;

            // 印出結果
            System.out.printf("%-10d | %-16.4f | %-16.4f | %-16.4f\n", n, time1, time2, time3);
        }
    }
}