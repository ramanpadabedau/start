import java.util.Stack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class PlainText {
    private FX fxInstance;
    private String ProcessedFileContent2;
    private StringBuilder fileContent;
    private String filePath;
    private String outFilePath;


    public PlainText( String filePath, FX fxInstance){
        this.filePath = filePath;
        this.fxInstance = fxInstance;
    }
    public void PlainTextProcessor(){
        fileContent = FileReader();
        PlainTextOut(fileContent);
        ProcessedFileContent2 = solveAndReplaceArithmeticExpressions(fileContent.toString());
        for(int i = 0 ; i < 5; i++){
            ProcessedFileContent2 = solveAndReplaceArithmeticExpressions(ProcessedFileContent2);
        }
    }
    public void PlainTextOutProcessor2(String outFileName){
        outFilePath = outFileName;
        StringBuilder PRFC2 = new StringBuilder(ProcessedFileContent2);
        PlainTextOut(PRFC2);
        PlainTextToFileOut2();
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
    public  void PlainTextToFileOut2(){
        try {
            File file = new File(outFilePath);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(ProcessedFileContent2.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Запись в файл успешно завершена.");
            fxInstance.setInfoLabelText("Запись в файл успешно завершена.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String solveAndReplaceArithmeticExpressions(String text) {
        Pattern pattern = Pattern.compile("([\\d.]+\\s*[-+*/]\\s*[\\d.]+)");
        Matcher matcher = pattern.matcher(text);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String expression = matcher.group(1);
            double resultValue = solveArithmeticExpression(expression);
            matcher.appendReplacement(result, String.valueOf(resultValue));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    public static double solveArithmeticExpression(String input) {
        String cleanedInput = cleanExpression(input);
        return evaluateExpression(cleanedInput);
    }

    private static String cleanExpression(String input) {
        return input.replaceAll("\\s+", "");
    }

    private static double evaluateExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder operand = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    operand.append(expression.charAt(i));
                    i++;
                }
                i--;

                values.push(Double.parseDouble(operand.toString()));
            } else if (currentChar == '(') {
                operators.push(currentChar);
            } else if (currentChar == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Pop '('
            } else if (isOperator(currentChar)) {
                while (!operators.isEmpty() && hasPrecedence(currentChar, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(currentChar);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }

    private static double applyOperator(char operator, double operand2, double operand1) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Неподдерживаемая арифметическая операция: " + operator);
        }
    }
}
