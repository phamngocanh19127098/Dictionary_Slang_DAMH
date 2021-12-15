import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;
        while (true){
            System.out.println("This slang word already exist in this dictionary, please enter the next action");
            System.out.println("Overwrite it, Enter 1");
            System.out.println("Duplicate it, Enter 2");
            System.out.print("Enter your choice: ");
            try{
                check = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException ex){
                System.out.println("You must enter an integer!!, please enter again\n");
            }
            if(check<1||check>2){
                continue;
            }
            break;

        }
    }
}
