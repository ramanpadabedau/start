import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class FX extends Application {
    private boolean toggleValue;
    private String fileOutName;
    private  String zipOutName;
    private fileProccessor fileP;
    private Boolean check = false;
    private Boolean check_encr = false;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Пример JavaFX");
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
        Button button6 = new Button("Таймер сна 30 минут");
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
        button6.setOnAction(e -> handleButtonClick(textField, "6"));
        exitButton.setOnAction((e) -> {
            System.exit(0);
        });
        Pane pane = new Pane();
        pane.getChildren().addAll(textField,textField2,textField3, button1,button2,button3,button4,button5,button6, label,label2,label3, exitButton);
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
        button6.setLayoutX(10);
        button6.setLayoutY(570);
        exitButton.setLayoutY(570);
        exitButton.setLayoutX(740);
        Scene scene = new Scene(pane, 800, 600);
        pane.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        primaryStage.setScene(scene);
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
                zipOutName = fileOutNameForZip;
                check = true;
                break;
            case '4':
                fileOutName = textField.getText();
                FileEncryption encryptor = new FileEncryption();
                if(check) {
                    /*int lastDotIndex = fileOutName.lastIndexOf('.');
                    fileOutName = fileOutName.substring(0, lastDotIndex + 1) + "zip";8*/
                    System.out.println(zipOutName);
                    encryptor.Encrypt1(zipOutName);
                    break;
                }
                encryptor.Encrypt1(fileOutName);
                fileOutName = "encrypted_"  + fileOutName;
                check_encr = true;
                break;
            case '5':
                String fileName1 = textField.getText();
                FileEncryption encryptor1 = new FileEncryption();
                fileName = encryptor1.Decrypt(fileName1);
                fileP = new fileProccessor(fileName);
                fileP.inProcess();
                break;
            case '6':
                try {
                    Runtime.getRuntime().exec("shutdown -s -t 1800");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}