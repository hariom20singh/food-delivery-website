import java.util.*;

public class ReverseNumber {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int ans = reversenumber(n);
        System.out.println(ans / 10);
    }

    static int reversenumber(int n) {
        int digits = (int) (1 + Math.log10(n));
        if (n == 0) {
            return 0;
        }
        return (int) (Math.pow(10, digits) * (n % 10)) + reversenumber(n / 10);
    }
}