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
public class FX extends Application {
    private boolean toggleValue;
    private String fileOutName;
    private fileProccessor fileP;
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
        ToggleButton toggleButton = new ToggleButton("Зашифрован");
        Button button1 = new Button("Ввод");
        Button button3 = new Button("Архивировать");
        Button button2 = new Button("Ввод");
        Button button4 = new Button("Зашифровать");
        Button exitButton = new Button("Выход");
        button1.setOnAction(e -> handleButtonClick(textField, "1"));
        button2.setOnAction(e -> handleButtonClick(textField2, "2"));
        button3.setOnAction(e -> handleButtonClick(textField3, "3"));
        button4.setOnAction(e -> handleButtonClick(textField2, "4"));
        toggleButton.setOnAction(e -> handleToggleButtonClick(toggleButton));
        exitButton.setOnAction((e) -> {
            System.exit(0);
        });
        Pane pane = new Pane();
        pane.getChildren().addAll(textField,textField2,textField3, button1,button2,button3,button4, label,label2,label3, toggleButton, exitButton);
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
        toggleButton.setLayoutX(300);
        toggleButton.setLayoutY(30);
        exitButton.setLayoutY(570);
        exitButton.setLayoutX(740);
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private  void handleToggleButtonClick(ToggleButton toggleButton){
        toggleValue = true;
    }
    private void handleButtonClick(TextField textField, String buttonNumber) {
        switch (buttonNumber.charAt(0)){
            case '1':
                String fileName = textField.getText();
                fileP = new fileProccessor(fileName,toggleValue);
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
        }
    }
}
