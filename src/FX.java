import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.zip.ZipOutputStream;
import javafx.application.Platform;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FX extends Application {
    private String fileOutName;
    private fileProccessor fileP;
    private final Random random = new Random();
    private final Pane root = new Pane();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX");
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        textField2.setMinWidth(185);
        textField3.setMinWidth(185);
        textField3.setMaxWidth(185);
        textField2.setMaxWidth(185);
        textField.setMinWidth(185);
        textField.setMaxWidth(185);
        Label label = new Label ("↓ Введите имя входного файла  ↓");
        Label label2 = new Label("↓ Введите имя выходного файла ↓");
        Label label3 = new Label("↓       Введите имя архива      ↓");
        Button button5 = new Button("Расшифровать");
        Button button1 = new Button("Ввод");
        Button button3 = new Button("Архивировать");
        Button button2 = new Button("Ввод");
        Button button4 = new Button("Зашифровать");
        Button exitButton = new Button("Выход");
        button1.setOnAction(e -> handleButtonClick(textField, "1"));
        button5.setOnAction(e -> handleButtonClick(textField, "5"));
        button2.setOnAction(e -> handleButtonClick(textField2, "2"));
        button3.setOnAction(e -> handleButtonClick(textField3, "3"));
        button4.setOnAction(e -> handleButtonClick(textField2, "4"));
        exitButton.setOnAction((e) -> {
            System.exit(0);
        });
        Pane pane = new Pane();
        root.getChildren().addAll(textField,textField2,textField3, button1,button2,button3,button4,button5, label,label2,label3, exitButton);
        textField.setLayoutX(30);
        textField.setLayoutY(30);
        textField2.setLayoutX(30);
        textField2.setLayoutY(80);
        textField3.setLayoutX(30);
        textField3.setLayoutY(130);
        label.setLayoutX(30);
        label.setLayoutY(10);
        label2.setLayoutX(30);
        label2.setLayoutY(60);
        label3.setLayoutX(30);
        label3.setLayoutY(110);
        button1.setLayoutX(240);
        button1.setLayoutY(30);
        button2.setLayoutX(240);
        button2.setLayoutY(80);
        button3.setLayoutX(240);
        button3.setLayoutY(130);
        button4.setLayoutX(300);
        button4.setLayoutY(80);
        button5.setLayoutX(300);
        button5.setLayoutY(30);
        exitButton.setLayoutY(570);
        exitButton.setLayoutX(740);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> changeBackgroundColor()),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        primaryStage.show();
    }
    private void handleButtonClick(TextField textField, String buttonNumber) {
        switch (buttonNumber.charAt(0)){
            case '1':
                String fileName = textField.getText();
                fileP = new fileProccessor(fileName);
                fileP.inProcess();
                break;
            case '2':
                fileOutName = textField.getText();
                fileP.outProcess(fileOutName);
                break;
            case '3':
                String fileOutNameForZip = textField.getText();
                zipper zip = new zipper();
                zip.zipTextFile(fileOutName, fileOutNameForZip);
                break;
            case '4':
                fileOutName = textField.getText();
                FileEncryption encryptor = new FileEncryption();
                encryptor.Encrypt(fileOutName);
                break;
            case '5':
                String fileName1 = textField.getText();
                FileEncryption encryptor1 = new FileEncryption();
                fileName = encryptor1.Decrypt(fileName1);
                fileP = new fileProccessor(fileName);
                fileP.inProcess();
                break;
        }
    }
    private void changeBackgroundColor() {
        Color lightColor = generateRandomLightColor();
        root.setStyle("-fx-background-color: " + toHex(lightColor) + ";");
    }
    private Color generateRandomLightColor() {
        double hue = random.nextDouble() * 360; // Случайный оттенок
        double saturation = random.nextDouble() * 0.3 + 0.7; // Случайная насыщенность от 0.7 до 1.0
        double brightness = random.nextDouble() * 0.3 + 0.7; // Случайная яркость от 0.7 до 1.0
        return Color.hsb(hue, saturation, brightness);
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
