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
import java.util.Random;

public class T504 extends Application
{
    @Override
    public void start(Stage stage)
    {  
        RandomNumber randos = new RandomNumber();
        
        // create and configure a text field for user entry
        TextField pushMeTextField = new TextField();
        pushMeTextField.setMaxWidth(40);        
        
        // create and configure a label to display the output
        Label pushMeLabel= new Label();
        pushMeLabel.setTextFill(Color.RED);
        pushMeLabel.setFont(Font.font("Arial", 20));
        
        // create and configure a label which will cause the text to be displayed
        Button guessThisButton = new Button();
        guessThisButton.setText("Guess the number in the box then push me!");
        guessThisButton.setOnAction(e -> {
         String inputnum = pushMeTextField.getText();
            try{
               int numinput = Integer.parseInt(inputnum);
               if(numinput == randos.getValue()) {
                  pushMeLabel.setText("You guessed it right!");
                  pushMeLabel.setTextFill(Color.GREEN);
               }
                  else if (numinput > randos.getValue()) {
                  pushMeLabel.setText("You guessed higher");
                  pushMeLabel.setTextFill(Color.ORANGE);
               }
               else if (numinput < randos.getValue()) {
                  pushMeLabel.setText("You guessed lower");
                  pushMeLabel.setTextFill(Color.YELLOW);
               }               
            }
            catch (NumberFormatException ex){
                  pushMeLabel.setText("Invalid, input a number");         
            }        
      }
);

            Button newNumButton = new Button("                 Guess Another Number                ");
            newNumButton.setOnAction(e -> {
                                 randos.generateNewValue();
                                       pushMeTextField.clear();
                                             pushMeLabel.setText("");
                                                 System.out.println(randos.getValue());
         });                                    
        
       

        // create and configure a VBox to hold our components       
        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        
        //add the components to the VBox
        root.getChildren().addAll(pushMeTextField, pushMeLabel, guessThisButton, newNumButton);
       
  
        // create a new scene
        Scene scene = new Scene(root, 500, 450);
        
        //add the scene to the stage, then configure the stage and make it visible
        stage.setScene(scene);
        stage.setTitle("Guess da Number");
        stage.show();        
        System.out.println(randos.getValue());
    }
    
    private static class RandomNumber {
        private int value;

        private Random random = new Random();

        public RandomNumber() {
            this.value = random.nextInt(1000);
        }

        public int getValue() {
            return value;
        }

        public void generateNewValue() {
            this.value = random.nextInt(1000);
        }
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}