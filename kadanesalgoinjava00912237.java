import java.util.*;
public class array{

    public static void printarray(int arr[]){
        for(int i = 0 ; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static void rev(int arr[]){
        int a=0;
        int b=arr.length-1;
        
        while(a<b){
            int temp = arr[b];
            arr[b]=arr[a];
            arr[a]=temp;
            a++;
            b--;
        }
        
    }

    public static void pairs(int num[]){
        int tp =0;
        for(int i =0 ; i<num.length ; i++){
            int curr = num[i];
            for(int j = i+1 ; j<num.length;j++){
                System.out.print("("+curr+","+num[j]+")");
                tp++;
            }
            System.out.println();
        }
        System.out.println(tp);
    }

    public static void subarray0(int arr[]){
        
        for(int i = 0 ;i<arr.length;i++){
            for(int j = i;j<arr.length;j++){
                for(int k = i;k<=j;k++){
                    System.out.print("{"+arr[k]+"}");
                    
                    
                }
                
                System.out.println();
                
            }
            System.out.println();
        }
        // System.out.print(sum);
    }
    public static void maxsubarraySum(int arr[]){
        int currsum=0;
        int max = Integer.MIN_VALUE;
        for(int i = 0 ;i<arr.length;i++){
            for(int j = i;j<arr.length;j++){
                currsum=0;
                for(int k = i;k<=j;k++){
                    currsum+=arr[k];
                }    
                System.out.println("CURRENT SUM IS :- "+currsum+" ");
                if(currsum>max){
                    max=currsum;
                }
                
            }
            
            // System.out.println();
            
            
        }
        System.out.print("max SUM IS :-  "+max+" ");
        // System.out.print(sum);
    }


    public static void maxsubarraySumprefix(int arr[]){
        int currsum=0;
        int max = Integer.MIN_VALUE;
        int prefix[] = new int[arr.length];
        prefix[0]=arr[0];
        for(int i= 1; i<prefix.length;i++){
            prefix[i]=prefix[i-1]+arr[i];
        }
        for(int i = 0 ;i<arr.length;i++){
            for(int j = i;j<arr.length;j++){
                currsum=0;
                currsum = i == 0 ? prefix[j]:prefix[j]-prefix[i-1];
                if(currsum>max){
                    max=currsum;
                }
                
            }
        }
        System.out.print("max SUM IS :-  "+max+" ");
        // System.out.print(sum);
    }

    public static void kadanesalgo(int arr[]){
        int maxsum = Integer.MIN_VALUE;
        int currsum = 0;
        for(int i = 0 ; i<arr.length;i++){
            currsum+=arr[i];
            if(currsum<0){
                currsum=0;
            }
            maxsum = Math.max(currsum,maxsum);
        }
        System.out.print("max sum is "+maxsum);
    }





    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        // int m = sc.nextInt();
        int arr[] = {k,0ojtgf};
        // int arr[] = {1,-2,6,-1,3};

        // rev(arr);
        // printarray(arr);
        // pairs(arr);
        // subarray0(arr)
        // maxsubarraySum(arr);
        // maxsubarraySumprefix(arr);
        kadanesalgo(arr);  
        
    }
}