import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        FX.launch(FX.class, args);

        String file_type = GetInputString();

        String archived_info = GetInputString();
        boolean archived;
        archived = (archived_info.length() == 3);

        String encrypted_info = GetInputString();
        boolean encrypted;
        encrypted = (encrypted_info.length() == 3);
        Integer order_info = 0;
        if(encrypted && archived){
            System.out.println("If the file was first encrypted and then archived, enter 1, otherwise enter 2");
            order_info = GetInputInteger();

        }

    }
    private static String GetInputString(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
    private  static Integer GetInputInteger(){
        Scanner scanner = new Scanner(System.in);
        Integer input = scanner.nextInt();
        return  input;
    }

}