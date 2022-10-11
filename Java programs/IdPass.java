import java.util.Scanner;

public class IdPass {
//use of while loop
    public static void main(String []arg){
        Scanner scan = new Scanner (System.in);
        System.out.print("UserName: ");
        String userID = scan.nextLine();
        System.out.print("Password: ");
        String password = scan.nextLine();
       
        String userIdO = "ArcheR19";
        
        String passwordO = "Archer@123";
        while(userID.equals(userIdO) || password.equals(passwordO)){
            System.out.println("Welcome");
          
        } 
        System.out.println("Incorrect Credentials. Please try again");
        scan.close();
    }
    
}