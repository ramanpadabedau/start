import javax.print.DocFlavor;
import javax.swing.*;
import java.security.PrivateKey;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class PlainText {
    private final boolean archived;
    private final boolean encrypted;
    private final Integer order_info;
    private boolean order;
    private StringBuilder ProcessedFileContent;
    private StringBuilder fileContent;
    private String filePath;
    private String outFilePath;


    public PlainText(boolean archived, boolean encrypted, Integer order_info, String filePath, String outfilePath){
        this.archived = archived;
        this.encrypted = encrypted;
        this.filePath = filePath;
        this.outFilePath = outfilePath;
        this.order_info = order_info;
        if(order_info != 0){
            order = true;
        }
    }
    public void PlainTextProcessor(){

        switch (order_info){
            case 0:
                if(archived){
                    zipper Zipp =new zipper();
                    Zipp.unzipSingleFile("C:\\Users\\ro\\IdeaProjects\\start\\arhip.zip", "C:\\Users\\ro\\IdeaProjects\\start\\" + filePath );
                    fileContent = FileReader();
                }
                if(encrypted){

                }
                if(!archived && !encrypted){
                    fileContent = FileReader();
                    PlainTextOut(fileContent);
                }
                break;
            case 1:

                break;
            case 2:

                break;
        }
        ProcessedFileContent = TextWorker1();
        PlainTextOut(ProcessedFileContent);
        PlainTextToFileOut();
    }
    private StringBuilder FileReader(){
        StringBuilder fileContent = new StringBuilder();
        try{
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
    public  void PlainTextOut(StringBuilder fileContent){
        System.out.println("Содержимое файла:\n" + fileContent.toString());
    }
    public  void PlainTextToFileOut(){
        try {
            File file = new File(outFilePath);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(ProcessedFileContent.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Запись в файл успешно завершена.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean IsNumber(char ch) {
        return ch == '1' ||
                ch == '2' ||
                ch == '3' ||
                ch == '4' ||
                ch == '5' ||
                ch == '6' ||
                ch == '7' ||
                ch == '8' ||
                ch == '9' ||
                ch == '0';
    }
    private boolean IsProbel(char ch) {
        return ch == ' ';
    }
    private boolean IsArithmSign(char ch){
        return ch == '+' ||
                ch == '-' ||
                ch == '*' ||
                ch =='/';
    }

    private StringBuilder TextWorker1(){
        StringBuilder ProcessedFileContent = fileContent;
        StringBuilder str = new StringBuilder();
        int sighs_count = 0;
        for(int i = 0 ; i < ProcessedFileContent.length(); i++){
            if(IsArithmSign(ProcessedFileContent.charAt(i))){
                sighs_count++;
            }
        }
        int j = 0;
        int jj = 0;
        boolean sigh_check = false;
        while (j < ProcessedFileContent.length() && jj < sighs_count) {
            int start = 0;
            int end = 0;
            if (IsNumber(ProcessedFileContent.charAt(j))) {
                start = j;
                if(!IsNumber(ProcessedFileContent.charAt(j+1)) && !IsArithmSign(ProcessedFileContent.charAt(j+1))){
                    j++;
                    continue;
                }
                while (IsNumber(ProcessedFileContent.charAt(j)) || IsArithmSign(ProcessedFileContent.charAt(j))) {
                    str.append(ProcessedFileContent.charAt(j));
                    j++;
                    if(IsArithmSign(ProcessedFileContent.charAt(j))){
                        sigh_check = true;
                    }
                    if (j == ProcessedFileContent.length()) {
                        break;
                    }
                }
                if(!sigh_check){
                    j++;
                    str = new StringBuilder();
                    continue;
                }
                end = j;
                StringBuilder StrB = new StringBuilder();
                StrB.append(Calculations(str));
                if(StrB.length() > str.length()) {
                    StrB.setLength(str.length());
                }
                ProcessedFileContent.replace(start, end,StrB.toString() );
                str = new StringBuilder();
                jj++;
            } else {
                j++;
            }
        }
        return ProcessedFileContent;
    }
    private double Calculations(StringBuilder str){
        double otvet = 0;
        StringBuilder first_elem = new StringBuilder();
        StringBuilder second_elem = new StringBuilder();
        char sigh = '+';
        boolean check = false;
        for(int i  = 0; i < str.length(); i++){
            if(IsNumber(str.charAt(i))){
                if(check){second_elem.append((str.charAt(i)));}
                else {first_elem.append(str.charAt(i));}

            }
            if(IsArithmSign(str.charAt(i))){
                sigh = str.charAt(i);
                check = true;
            }
        }
        System.out.println("первый " + first_elem);
        System.out.println("второй " + second_elem);
        System.out.println("ЗНАК " + sigh);
        otvet = switch (sigh) {
            case '+' -> Integer.parseInt(first_elem.toString()) + Integer.parseInt(second_elem.toString());
            case '-' -> Integer.parseInt(first_elem.toString()) - Integer.parseInt(second_elem.toString());
            case '*' -> Double.parseDouble(first_elem.toString()) * Double.parseDouble(second_elem.toString());
            case '/' -> Double.parseDouble(first_elem.toString()) / Double.parseDouble(second_elem.toString());
            default -> otvet;
        };
        return otvet;

    }

}
