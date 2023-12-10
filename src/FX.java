import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
public class FX extends Application {
    private boolean toggleValue;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Пример JavaFX");

        // Создаем поле для ввода
        TextField textField = new TextField();
        textField.setMinWidth(180);
        textField.setMaxWidth(180);
        Label label = new Label("↓ Введите имя входного файла ↓");
        ToggleButton toggleButton = new ToggleButton("Зашифрован");
        // Создаем кнопки
        Button button1 = new Button("Ввод");
        Button button2 = new Button("Нажми меня 2!");

        // Устанавливаем обработчики событий для кнопок
        button1.setOnAction(e -> handleButtonClick(textField, "1"));
        toggleButton.setOnAction(e -> handleToggleButtonClick(toggleButton));


        // Создаем StackPane и добавляем элементы с явным указанием координат
        Pane pane = new Pane();
        pane.getChildren().addAll(textField, button1, label, toggleButton);


        // Устанавливаем координаты каждого элемента
        textField.setLayoutX(30); // X-координата
        textField.setLayoutY(30);
        label.setLayoutX(30);
        label.setLayoutY(10);
        button1.setLayoutX(240);
        button1.setLayoutY(30);
        toggleButton.setLayoutX(300);
        toggleButton.setLayoutY(30);

        // Создаем сцену, добавляем StackPane и устанавливаем размеры
        Scene scene = new Scene(pane, 800, 600);

        // Отображаем primaryStage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // Обработчик событий для кнопок
    private  void handleToggleButtonClick(ToggleButton toggleButton){
        toggleValue = true;
    }
    private void handleButtonClick(TextField textField, String buttonNumber) {
        String fileName = textField.getText();
        fileProccessor fileP = new fileProccessor(fileName,toggleValue);
        fileP.Process();
    }
}
