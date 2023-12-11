import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.File;
import java.io.IOException;

public class JsonArithmeticProcessor {
    private static JsonNode rootNode;
    private static ObjectMapper objectMapper;

    public static void processJsonFile(String inputFilePath) {
        try {
            objectMapper = new ObjectMapper();
            rootNode = objectMapper.readTree(new File(inputFilePath));
            processArithmeticOperations(rootNode, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processJsonFileOut(String outputFilePath) {
        try {
            objectMapper.writeValue(new File(outputFilePath), rootNode);
            System.out.println("Изменения записаны в файл: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processArithmeticOperations(JsonNode node, ObjectNode parentNode, String fieldName) {
        if (node.getNodeType() == JsonNodeType.OBJECT) {
            ObjectNode objectNode = (ObjectNode) node;
            objectNode.fields().forEachRemaining(entry -> {
                JsonNode fieldValue = entry.getValue();
                processArithmeticOperations(fieldValue, objectNode, entry.getKey());
            });
        } else if (node.getNodeType() == JsonNodeType.ARRAY) {
            ArrayNode arrayNode = (ArrayNode) node;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode item = arrayNode.get(i);
                processArithmeticOperations(item, null, null);
            }
        } else if (node.getNodeType() == JsonNodeType.STRING) {
            String originalValue = node.asText();

            if (originalValue.contains("+") || originalValue.contains("-") ||
                    originalValue.contains("*") || originalValue.contains("/")) {
                double result = evaluateArithmeticExpression(originalValue);
                if (parentNode != null && fieldName != null) {
                    parentNode.replace(fieldName, objectMapper.convertValue(result, JsonNode.class));
                }
            }
        }
    }

    private static double evaluateArithmeticExpression(String expression) {
        try {
            Expression exp = new ExpressionBuilder(expression).build();
            return exp.evaluate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
