import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class T505 extends Application
{
   
      private StringBuilder currentInput = new StringBuilder();
      private StringBuilder displayText = new StringBuilder();
      private double result = 0;
      private char operation = ' ';
      private Button equalsButton;

    @Override
    public void start(Stage stage)
    {
        // create and configure a text field for user entry
        TextField pushMeTextField = new TextField();
        pushMeTextField.setMaxWidth(250);
        pushMeTextField.setEditable(false);
        pushMeTextField.setAlignment(Pos.CENTER_RIGHT);
        pushMeTextField.setFont(Font.font(20));
        
        // create and configure a label to display the output
        Label pushMeLabel= new Label();
        pushMeLabel.setTextFill(Color.BLACK);
        pushMeLabel.setFont(Font.font("Arial", 20));
        
        /* 
            Calculator
                  Buttons
                           */
                           
        Button num1Button = new Button();                   
        num1Button.setText("1");
        num1Button.setPrefSize(50, 50);
        num1Button.setOnAction(e -> appendToDisplay("1"));
        
        Button num2Button = new Button();                   
        num2Button.setText("2");
        num2Button.setPrefSize(50, 50);
        num2Button.setOnAction(e -> appendToDisplay("2"));
        
        Button num3Button = new Button();                   
        num3Button.setText("3");
        num3Button.setPrefSize(50, 50);
        num3Button.setOnAction(e -> appendToDisplay("3"));
        
        Button num4Button = new Button();                   
        num4Button.setText("4");
        num4Button.setPrefSize(50, 50);
        num4Button.setOnAction(e -> appendToDisplay("4"));
        
        Button num5Button = new Button();                   
        num5Button.setText("5");
        num5Button.setPrefSize(50, 50);
        num5Button.setOnAction(e -> appendToDisplay("5"));
        
        Button num6Button = new Button();                   
        num6Button.setText("6");
        num6Button.setPrefSize(50, 50);
        num6Button.setOnAction(e -> appendToDisplay("6"));
        
        Button num7Button = new Button();                   
        num7Button.setText("7");
        num7Button.setPrefSize(50, 50);
        num7Button.setOnAction(e -> appendToDisplay("7"));
        
        Button num8Button = new Button();                   
        num8Button.setText("8");
        num8Button.setPrefSize(50, 50);
        num8Button.setOnAction(e -> appendToDisplay("8"));
        
        Button num9Button = new Button();                   
        num9Button.setText("9");
        num9Button.setPrefSize(50, 50);
        num9Button.setOnAction(e -> appendToDisplay("9"));
        
        Button num0Button = new Button();                   
        num0Button.setText("0");
        num0Button.setPrefSize(50, 50);
        num0Button.setOnAction(e -> appendToDisplay("0"));
        
        Button addButton = new Button();
        addButton.setText("+");
        addButton.setPrefSize(50, 50);
        addButton.setOnAction(e -> handleOperation('+'));
        
        Button minusButton = new Button();
        minusButton.setText("-");
        minusButton.setPrefSize(50, 50);
        minusButton.setOnAction(e -> handleOperation('-'));
        
        Button multiButton = new Button();
        multiButton.setText("×");
        multiButton.setPrefSize(50, 50);
        multiButton.setOnAction(e -> handleOperation('×'));
        
        Button divideButton = new Button();
        divideButton.setText("÷");
        divideButton.setPrefSize(50, 50);
        divideButton.setOnAction(e -> handleOperation('÷'));
        
        Button clearButton = new Button();
        clearButton.setText("C");
        clearButton.setPrefSize(50, 50);
        clearButton.setOnAction(e -> clearDisplay());
        
        equalsButton = new Button();
        equalsButton.setText("=");
        equalsButton.setPrefSize(50, 50);
        equalsButton.setOnAction(e -> calculateResult());
        
        // Grid
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);
        buttonGrid.setAlignment(Pos.CENTER);
        
        buttonGrid.addRow(0, num1Button, num2Button, num3Button, addButton);
        buttonGrid.addRow(1, num4Button, num5Button, num6Button, minusButton);
        buttonGrid.addRow(2, num7Button, num8Button, num9Button, multiButton);
        buttonGrid.addRow(3, num0Button, clearButton, divideButton, equalsButton);
            
        // create and configure a VBox to hold our components       
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.CENTER);
        
        //add the components to the VBox
        root.getChildren().addAll(pushMeTextField, buttonGrid);
       
  
        // create a new scene
        Scene scene = new Scene(root, 350, 250);
        
        //add the scene to the stage, then configure the stage and make it visible
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();        
    }
    
    private void appendToDisplay(String value) {
         currentInput.append(value);
         displayText.append(value);
         updateTextField();
    }
    
    private void handleOperation(char op) {
         operation = op;
         displayText.append(" ").append(op).append(" ");
               updateTextField();
         result = Double.parseDouble(currentInput.toString());
            currentInput.setLength(0);
    }
    
    private void clearDisplay() {
         currentInput.setLength(0);
            result = 0;
         displayText.setLength(0);
            TextField textField = (TextField) ((VBox) ((Scene) equalsButton.getScene()).getRoot()).getChildren().get(0);
         textField.clear();
    }
    
    private void calculateResult() {
        double secondOperand = Double.parseDouble(currentInput.toString());
        double finalResult = 0;

        switch (operation) {
            case '+':
                finalResult = result + secondOperand;
                break;
            case '-':
                finalResult = result - secondOperand;
                break;
            case '×':
                finalResult = result * secondOperand;
                break;
            case '÷':
                if (secondOperand != 0) {
                    finalResult = result / secondOperand;
                } else {
                             System.out.println("Error: Division by zero");
                             return;
                }
                break;
        }

        
        displayText.setLength(0);
        displayText.append(finalResult);
        updateTextField();
        currentInput.setLength(0);
        result = finalResult;
    }
    
    private void updateTextField() {
        TextField textField = (TextField) ((VBox) ((Scene) equalsButton.getScene()).getRoot()).getChildren().get(0);
        textField.setText(displayText.toString());
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
