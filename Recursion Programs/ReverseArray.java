import java.util.Scanner;

public class ReverseArray {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }

        reverseorder(arr, n);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void reverseorder(int[] arr, int n) {
        int l = 0;
        int h = arr.length - 1;
        while (l <= h) {
            swap(arr, n, l, h);
            l++;
            h--;
        }

    }

    static void swap(int arr[], int n, int l, int h) {
        int temp = arr[l];
        arr[l] = arr[h];
        arr[h] = temp;
    }

}