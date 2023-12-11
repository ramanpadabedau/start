import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import net.objecthunter.exp4j.*;

public class XmlArithmeticProcessor {
    private static Document document;
    public static void main(String[] args) {

    }

    public static void processXmlFile(String inputFilePath) {
        try {
            // Чтение XML-файла
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(inputFilePath));
            processArithmeticOperations(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void processOutXML(String OutFileName){
        try {
        writeXmlFile(document, OutFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void processArithmeticOperations(Document document) {
        NodeList operationNodes = document.getElementsByTagName("operation");
        for (int i = 0; i < operationNodes.getLength(); i++) {
            Node operationNode = operationNodes.item(i);
            if (operationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element operationElement = (Element) operationNode;
                String expression = operationElement.getTextContent();
                if (!expression.isEmpty()) {
                    double result = evaluateArithmeticExpression(expression);


                    operationElement.setTextContent(Double.toString(result));
                }
                logDocumentContent(document);
            }
        }
    }

    private static double evaluateArithmeticExpression(String expression) {
        try {
            Expression exp = new ExpressionBuilder(expression).build();
            return exp.evaluate();
        } catch (ArithmeticException e) {

            e.printStackTrace();
            return 0;
        }
    }

    private static void logDocumentContent(Document document) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(sw));
            String documentContent = sw.toString();


            System.out.println("Содержимое документа после обработки:");
            System.out.println(documentContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeXmlFile(Document document, String outputPath) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(outputPath));
        transformer.transform(source, result);
        System.out.println("Изменения записаны в файл: " + outputPath);
    }
}