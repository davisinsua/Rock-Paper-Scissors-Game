// Rock Paper Scissors Shoot - Driver Code
// Davis Insua

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.*;

public class gameRPS extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    // Create variables to hold score
    int pScore = 0, cScore = 0;

    // Header for UI
    Label title = new Label("Rock Paper Scissors Shoot");

    // String that holds the choices for computer
    String choiceString[] = {"ROCK", "PAPER", "SCISSORS"};

    // Create arraylist for UI buttons using strings, alows for more concise edidting of buttons using loops
    String buttonString[] = {"ROCK", "PAPER", "SCISSORS", "RESET", "QUIT", "?", "?"};
    ArrayList<Button> buttons = new ArrayList<Button>(5);

    // arraylist for labels, same logic as above
    String labelString[] = {"", "", ""};
    ArrayList<Label> labels = new ArrayList<Label>(3);

    @Override
    public void start(Stage myStage)
    {
        // Stage options
        myStage.setTitle("RPS Game");
        myStage.setResizable(false);

        // Edit UI header
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        // Loop to modify UI buttons
        for(int i = 0; i < buttonString.length; i++)
        {
            // Create button with specified string from buttonString
            buttons.add(new Button(buttonString[i]));

            // Adjust size of button
            buttons.get(i).setMinWidth(110);
            buttons.get(i).setMinHeight(65);

            // Adjust font of button
            buttons.get(i).setFont(Font.font("Arial", FontWeight.BOLD, 20));

            // Special cases to set green for player button (i = 5), red for comp button (i = 6)
            if(i == 5)
                buttons.get(i).setStyle("-fx-background-color: #07eb31");
            if(i == 6)
                buttons.get(i).setStyle("-fx-background-color: #db1212");
        }

        // Loop to modify UI labels
        for(int i = 0; i < labelString.length; i++)
        {
            // Create label with specified string from labelString
            labels.add(new Label(labelString[i]));

            // Modify label
            labels.get(i).setFont(Font.font("Arial", FontWeight.BOLD, 17));

            // Special cases to set green for player label (i = 1), red for comp label (i = 2)
            if(i == 1)
                labels.get(i).setTextFill(Color.GREEN);
            else if(i == 2)
                labels.get(i).setTextFill(Color.RED);
        }


        // Hbox options, holds the UI header only
        HBox topTitle = new HBox();
        topTitle.setAlignment(Pos.CENTER);
        topTitle.getChildren().addAll(title);

        // Handle actions for UI buttons. Call handleGameAction with correct string parameter
        buttons.get(0).setOnAction(e->handleGameAction("ROCK"));
        buttons.get(1).setOnAction(e->handleGameAction("PAPER"));
        buttons.get(2).setOnAction(e->handleGameAction("SCISSORS"));
        buttons.get(3).setOnAction(e->handleGameAction("RESET"));
        buttons.get(4).setOnAction(e->handleGameAction("QUIT"));

        // Create gridpane. will hold most UI elements
        GridPane base = new GridPane();
        base.setAlignment(Pos.CENTER);
        base.setHgap(5);
        base.setVgap(5);

        // Add elements to gridpane
        base.add(buttons.get(0), 0, 0);
        base.add(buttons.get(1), 1, 0);
        base.add(buttons.get(2), 2, 0);
        base.add(buttons.get(5), 0, 1);
        base.add(buttons.get(6), 1, 1);
        base.add(labels.get(0), 2, 1);
        base.add(labels.get(1) , 0, 2);
        base.add(labels.get(2) , 1, 2);
        base.add(buttons.get(3), 0, 3);
        base.add(buttons.get(4), 1, 3);

        // Borderpane (root) options
        BorderPane root = new BorderPane();
        root.setTop(topTitle);
        root.setBottom(base);

        // Scene options
        Scene scene = new Scene(root, 450, 285, Color.WHITE);
        myStage.setScene(scene);
        myStage.show();
    }

    // Main function to handle actions in the game
    void handleGameAction(String choice)
    {
        // Make the AI select a random RPS option.
        Random r = new Random();
        // Assign the choses RPS string to choice string. used to compare later
        String compChoice = choiceString[r.nextInt(choiceString.length)];

        // Update the player and comp choice UI boxes with their choices
        buttons.get(5).setText(choice);
        buttons.get(6).setText(compChoice);

        // Switch to handle player choice and outcome
        switch(choice)
        {
            // Player selected rock
            case "ROCK":
                // AI also selected rock
                if(compChoice == "ROCK")
                {
                    // Update flavor text and color
                    labels.get(0).setText("It's a tie!!");
                    labels.get(0).setTextFill(Color.BLACK);
                }
                // AI chose paper (player loses)
                else if(compChoice == "PAPER")
                {
                    // Update flavor text informing the player on their dramatic defeat
                    labels.get(0).setText("You lose!");
                    labels.get(0).setTextFill(Color.RED);
                    // increment the computer's scorecount
                    cScore++;
                }

                // AI chose scissors (player wins)
                else if(compChoice == "SCISSORS")
                {
                    // Update flavor text
                    labels.get(0).setText("You win!");
                    labels.get(0).setTextFill(Color.GREEN);
                    // Increment player's corecount
                    pScore++;
                }
                break;

            // Paper and scissors cases use similar logic to rock.
            case "PAPER":
                if(compChoice == "PAPER")
                {
                    labels.get(0).setText("It's a tie!!");
                    labels.get(0).setTextFill(Color.BLACK);
                }

                else if(compChoice == "SCISSORS")
                {
                    labels.get(0).setText("You lose!");
                    labels.get(0).setTextFill(Color.RED);
                    cScore++;
                }

                else if(compChoice == "ROCK")
                {
                    labels.get(0).setText("You win!");
                    labels.get(0).setTextFill(Color.GREEN);
                    pScore++;
                }
                break;

            case "SCISSORS":
                if(compChoice == "SCISSORS")
                {
                    labels.get(0).setText("It's a tie!!");
                    labels.get(0).setTextFill(Color.BLACK);
                }

                else if(compChoice == "ROCK")
                {
                    labels.get(0).setText("You lose!");
                    labels.get(0).setTextFill(Color.RED);
                    cScore++;
                }

                else if(compChoice == "PAPER")
                {
                    labels.get(0).setText("You win!");
                    labels.get(0).setTextFill(Color.GREEN);
                    pScore++;
                }
                break;

            // User wants to reset
            case "RESET":
                // Erase scores
                pScore = 0;
                cScore = 0;
                // Erase flavor text
                labels.get(0).setText("");
                // Reset choice buttons
                buttons.get(5).setText("?");
                buttons.get(6).setText("?");
                break;

            case "QUIT":
                System.exit(0);
                break;
        }
        // Dispay scorecount after switch statement finishes
        labels.get(1).setText("You = "+pScore);
        labels.get(2).setText("Computer = "+cScore);
    }
}
