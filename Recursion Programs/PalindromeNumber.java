import java.util.Scanner;

public class PalindromeNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        int ans = reversenumber(num);
        if (num == ans / 10) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
    }

    static int reversenumber(int n) {
        int digits = (int) (1 + Math.log10(n));
        if (n == 0) {
            return 0;
        }
        return (int) (Math.pow(10, digits) * (n % 10)) + reversenumber(n / 10);
    }
}
