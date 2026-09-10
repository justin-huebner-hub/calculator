package de.jh.calculator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * UI-class for this application.
 *
 * @author Justin Hübner
 * @version 1.0 / September 2026
 */
public class CalculatorUI
{
    private static final String TITEL = "The Calculterminator";

    private Button _exit;
    private Button _plus;
    private Button _minus;
    private Button _times;
    private Button _divide;
    private Button _equals;
    private Button _clear;
    private Button _clearEntry;
    private Button _decimal;
    private Button[] _numberButtons;

    private TextField _display;
    private Stage _stage;

    public CalculatorUI() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle(TITEL);
        BorderPane contentPane = new BorderPane();

        _display = createDisplay();
        contentPane.setTop(_display);
        contentPane.setCenter(createKeypadPane());
        contentPane.setBottom(closePane());

        Scene scene = new Scene(contentPane, 360, 480);
        primaryStage.setScene(scene);
        _stage = primaryStage;
    }

    public void show() {
        _stage.show();
    }

    public void close() {
        System.exit(0);
    }

    private TextField createDisplay() {
        TextField display = new TextField("0");
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setFont(Font.font("Consolas", FontWeight.BOLD, 28));
        display.setPadding(new Insets(15, 15, 15, 15));
        return display;
    }

    private Pane createKeypadPane() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        _numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            _numberButtons[i] = createStyledButton(String.valueOf(i));
        }

        _decimal = createStyledButton(".");
        _plus = createStyledButton("+");
        _minus = createStyledButton("-");
        _times = createStyledButton("*");
        _divide = createStyledButton("/");
        _equals = createStyledButton("=");
        _clear = createStyledButton("C");
        _clearEntry = createStyledButton("CE");

        grid.add(_clearEntry, 0, 0);
        grid.add(_clear, 1, 0);
        grid.add(_divide, 2, 0);
        grid.add(_times, 3, 0);

        grid.add(_numberButtons[7], 0, 1);
        grid.add(_numberButtons[8], 1, 1);
        grid.add(_numberButtons[9], 2, 1);
        grid.add(_minus, 3, 1);

        grid.add(_numberButtons[4], 0, 2);
        grid.add(_numberButtons[5], 1, 2);
        grid.add(_numberButtons[6], 2, 2);
        grid.add(_plus, 3, 2);


        grid.add(_numberButtons[1], 0, 3);
        grid.add(_numberButtons[2], 1, 3);
        grid.add(_numberButtons[3], 2, 3);
        
        _equals.setPrefHeight(110);
        grid.add(_equals, 3, 3, 1, 2);

        // Row 4: 0 (colspan 2), .
        _numberButtons[0].setPrefWidth(130);
        grid.add(_numberButtons[0], 0, 4, 2, 1);
        grid.add(_decimal, 2, 4);

        return grid;
    }

    private Button createStyledButton(String text) {
        Button btn = new Button(text);
        btn.setFont(Font.font("System", FontWeight.BOLD, 18));
        btn.setPrefSize(60, 50);
        return btn;
    }

    private Pane closePane() {
        FlowPane bottomPane = new FlowPane();
        bottomPane.setAlignment(Pos.CENTER_RIGHT);
        bottomPane.setPadding(new Insets(5, 15, 10, 15));
        _exit = new Button("exit");
        _exit.setFont(Font.font("System", FontWeight.NORMAL, 14));
        bottomPane.getChildren().add(_exit);
        return bottomPane;
    }

    public Button getExitButton() {
        return _exit;
    }

    public Button getPlusButton() {
        return _plus;
    }

    public Button getMinusButton() {
        return _minus;
    }

    public Button getTimesButton() {
        return _times;
    }

    public Button getDivideButton() {
        return _divide;
    }

    public Button getEqualsButton() {
        return _equals;
    }

    public Button getClearButton() {
        return _clear;
    }

    public Button getClearEntryButton() {
        return _clearEntry;
    }

    public Button getDecimalButton() {
        return _decimal;
    }

    public Button getNumberButton(int number) {
        if (number >= 0 && number <= 9) {
            return _numberButtons[number];
        }
        return null;
    }

    public TextField getDisplay() {
        return _display;
    }

    public void setDisplayValue(String value) {
        _display.setText(value);
    }

    public String getDisplayValue() {
        return _display.getText();
    }
}
