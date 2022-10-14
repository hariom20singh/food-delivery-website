import java.util.Scanner;

public class Productofdigits {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int ans = prod(num);
        System.out.println(ans);
    }

    static int prod(int n) {
        if (n % 10 == n) {
            return n;
        }
        return (n % 10) * prod(n / 10);
    }

}