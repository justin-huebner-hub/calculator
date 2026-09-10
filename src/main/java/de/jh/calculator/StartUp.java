package de.jh.calculator;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Start-Up for the application.
 */
public class StartUp extends Application
{

    /**
     * the main-method to start the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args)
    {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage)
    {
        final CalculatorUI calUI = new CalculatorUI();
        new Calculator(calUI);
    }

}
