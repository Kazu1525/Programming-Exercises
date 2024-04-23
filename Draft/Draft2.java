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

public class Draft extends Application
{
    @Override
    public void start(Stage stage)
    {
        // create and configure a text field for user entry
        TextField pushMeTextField = new TextField();
        pushMeTextField.setMaxWidth(250);        
        
        // create and configure a label to display the output
        Label pushMeLabel= new Label();
        pushMeLabel.setText("You are in USeP Campus");
        pushMeLabel.setTextFill(Color.GREY);
        pushMeLabel.setFont(Font.font("Arial", 20));
        
        Label label2 = new Label();
        label2.setText("WHAT WOULD YOU LIKE TO DO?");
        label2.setTextFill(Color.GREEN);
        label2.setFont(Font.font("Arial", 15));
        
        Label result = new Label();
        result.setText("RESULT:");
        result.setTextFill(Color.GREY);
        result.setFont(Font.font("Arial", 10));
        
        Label label3 = new Label();
        label3.setTextFill(Color.GREEN);
        label3.setFont(Font.font("Arial", 15));
        
        // create and configure a label which will cause the text to be displayed
        Button pushMeButton = new Button();
        pushMeButton.setText("Mag cutting tas mag dulag flynn");
        pushMeButton.setOnAction(e -> {
                           label3.setText("NABAGSAK KA!!! (Skill Issue)");
                           label3.setTextFill(Color.RED);
                           });
        
        
        //
        Button toLowButton = new Button();
        toLowButton.setText("Adto food park tas magkaon gamay dako");
        toLowButton.setOnAction(e -> {
                           label3.setText("Hurot Kwarta, Ala na allowance");
                           label3.setTextFill(Color.RED);
                           });
        
        //
        Button toUpperButton = new Button();
        toUpperButton.setText("Sulod klase para good student");
        toUpperButton.setOnAction(e -> {
                           label3.setText("Good Job: Dean Lister ka, pero karon rapud");
                           label3.setTextFill(Color.GREEN);
                           });    
        
       

        // create and configure a VBox to hold our components       
        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        
        //add the components to the VBox
        root.getChildren().addAll(pushMeLabel, label2, pushMeButton,  toLowButton, toUpperButton, result, label3);
       
  
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