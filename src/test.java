import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfDef = 0;
        while(true){
            try{
                System.out.print("Enter the number of mean: ");
                String str = scanner.nextLine();
                numberOfDef = Integer.parseInt(str);
                if(numberOfDef<=0||numberOfDef>5){
                    continue;
                }
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("The input must be integer, please enter again!!!");
            }
        }
    }
}
