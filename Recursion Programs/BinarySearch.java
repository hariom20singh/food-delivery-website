import java.util.*;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        int target = input.nextInt();

        for (int i=0; i<n; i++){
            arr[i]=input.nextInt();
        }

        binarySearch(arr,target,0,n);

    }

    static void binarySearch(int[] arr, int target, int s, int e){
        int l = 0;
        int h = arr.length-1;

        while (l<h){
            int mid = l+(h-l)/2;
            if(arr[mid]==target){
                System.out.println(mid);
            }
            else if (arr[mid]>target){
                binarySearch(arr,target,s,mid-1);
            }
            else {
                binarySearch(arr,target,mid+1, e);
            }
        }
    }
}
