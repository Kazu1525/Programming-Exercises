import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PushMe extends Application
{
    @Override
    public void start(Stage stage)
    {
        // create and configure a text field for user entry
        TextField pushMeTextField = new TextField();
        pushMeTextField.setMaxWidth(250);        
        
        // create and configure a label to display the output
        Label pushMeLabel= new Label();
        pushMeLabel.setTextFill(Color.RED);
        pushMeLabel.setFont(Font.font("Arial", 20));
        
        // create and configure a label which will cause the text to be displayed
        Button pushMeButton = new Button();
        pushMeButton.setText("Type something in the box then push me");
        pushMeButton.setOnAction(e -> pushMeLabel.setText("You entered: " + pushMeTextField.getText()));
        
        //
        Button toLowButton = new Button();
        toLowButton.setText("                convert to lower case                ");
        toLowButton.setOnAction(e -> pushMeLabel.setText("You entered: " + pushMeTextField.getText().toLowerCase()));
        
        //
        Button toUpperButton = new Button();
        toUpperButton.setText("            CONVERT TO UPPER CASE            ");
        toUpperButton.setOnAction(e -> pushMeLabel.setText("You entered: " + pushMeTextField.getText().toUpperCase()));    
        
       

        // create and configure a VBox to hold our components       
        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        
        //add the components to the VBox
        root.getChildren().addAll(pushMeTextField, pushMeLabel, pushMeButton,  toLowButton, toUpperButton);
       
  
        // create a new scene
        Scene scene = new Scene(root, 500, 450);
        
        //add the scene to the stage, then configure the stage and make it visible
        stage.setScene(scene);
        stage.setTitle("Push Me: Fritch");
        stage.show();        
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}