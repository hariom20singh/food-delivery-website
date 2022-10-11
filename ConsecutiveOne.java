import java.util.Scanner;

public class ConsecutiveOne {
    static int counter = 0;

    static void acceptConsecutiveOne() {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome In the Finite State Machine !!!");
        System.out.println("Enter the Binary String : ");
        String input = in.next();
        boolean validInfo = checkValidString(input);
        boolean ans = false;
        if (validInfo) {
            ans = working(input);
            if(ans){
                System.out.println("\n\n\n Congratulations , Your String is Accepted $$$ \n\n\n");
            }
            else{
                System.out.println("Your  String is Rejected !!!!");
            }

        } else {
            counter++;
            if (counter <= 1) {

                System.out.println("\n\n\nPlease Enter the Only Binary String ....");
                 acceptConsecutiveOne();

            }

            else if (counter <= 3) {
                System.out.println("\n\n\nDhayan se Binary String Dal...\n\n\n");
                 acceptConsecutiveOne();
            } else {

                System.out.println("Exiting the Program YOU ARE NOT UNDERSTAND AUTOMATA LANGUAGE ..//");

            }

        }

    

    }

    static boolean working(String s) {

        int i = 0, l = s.length();
        while (i < l) {
            if (s.charAt(i) == '1') {
                i++;
                if (i < l && s.charAt(i) == '1') {
                    i++;
                    if (i < l && s.charAt(i) == '1') {
                        return true;
                    }

                }

            }
            i++;
        }

        return false;

    }

    static boolean checkValidString(String s) {
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0' && s.charAt(i) != '1') {
                return false;

            }

        }

        return flag;
    }

    public static void main(String[] args) {
    
       acceptConsecutiveOne();
    }
}