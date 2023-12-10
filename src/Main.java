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
        if("Plain text".equals(file_type)){
            PlainTextProcessor(archived, encrypted, order_info);
        }
        if("Xml".equals(file_type)){
            XMLProcessor(archived, encrypted, order_info);
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
    private static  void PlainTextProcessor(final boolean archived, final boolean encrypted, final Integer order_info){
        PlainText myPlainText = new PlainText(archived, encrypted, order_info, "input1.txt" , "output.txt");
        myPlainText.PlainTextProcessor();
        zipper Zipp =new zipper();
        Zipp.zipTextFile("C:\\Users\\ro\\IdeaProjects\\start\\output.txt", "архип.zip");
    }
    private static void XMLProcessor(final boolean archived, final boolean encrypted, final Integer order_info){
        PlainText Xml_as_Plain_text = new PlainText(archived, encrypted, order_info, "input.xml" , "output.xml");
        Xml_as_Plain_text.PlainTextProcessor();
    }
}