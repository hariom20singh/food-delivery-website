import java.util.Scanner;

public class VolumeOfCone {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Enter 1 to find volume");
            System.out.println("Enter 2 to find radius");
            System.out.println("Enter 3 to find height");
            System.out.println("Enter 0 to exit the code");
            int option = sc.nextInt();

            switch (option){
                case 1:
                    System.out.println("Enter radius of cone");
                    double radius = sc.nextDouble();
                    System.out.println("Enter height of cone");
                    double height = sc.nextDouble();
                    double volume = (Math.PI*Math.pow(radius, 2)*height)/3;
                    System.out.println("Volume of cone is: " + volume);
                    break;
                case 2:
                    System.out.println("Enter volume of cone");
                    volume = sc.nextDouble();
                    System.out.println("Enter height of cone");
                    height = sc.nextDouble();
                    radius = Math.sqrt((volume*3/(Math.PI*height)));
                    System.out.println("The radius of cone is: " +radius);
                    break;
                case 3:
                    System.out.println("Enter volume of cone");
                    volume = sc.nextDouble();
                    System.out.println("Enter radius of cone");
                    radius = sc.nextDouble();
                    height = volume*3/(Math.PI*Math.pow(radius,2));
                    System.out.println("The height of cone is: " + height);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");

            }
        }while(true);
    }
}
