package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        TabPane pane = new TabPane();

        pane.getTabs().addAll(
                getTabWithTextField("Первый график", "a = ", new Graphic1()),
                getTabWithTextField( "Второй график","a = ", new Graphic2()));

        primaryStage.setTitle("Метод приращений");
        primaryStage.setScene(new Scene(pane, 800, 640));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Tab getTabWithTextField(String TabTitle, String FieldTitle, final Graphic graphic){
        final Canvas canvas = new Canvas(800, 640);
        graphic.draw(canvas, 1);

        TextField textField = new TextField("1");
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isValid(newValue)) graphic.draw(canvas, Float.parseFloat(newValue));
            }

            boolean isValid(String newValue){
                return Float.parseFloat(newValue) > 0;
            };
        });

        Label label = new Label(FieldTitle);

        VBox vBox = new VBox();
        HBox hBox = new HBox();

        hBox.getChildren().addAll(label, textField);
        hBox.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().addAll(hBox, canvas);

        Tab tab = new Tab(TabTitle);
        tab.setContent(vBox);
        tab.setClosable(false);
        return tab;
    }




    // метод main в JavaFX приложениях не является обязательным
    public static void main(String[] args) {
        launch(args);
    }
}
