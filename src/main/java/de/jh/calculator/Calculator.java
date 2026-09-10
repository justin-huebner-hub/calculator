package de.jh.calculator;

/**
 * Calculator-class for this application.
 *
 * @author Justin Hübner
 * @version 1.0 / September 2026
 */
public class Calculator
{
    private double _solution;
    private double _firstOperand;
    private String _pendingOperator;
    private boolean _startNewInput;

    private CalculatorUI _ui;

    /**
     * Creates a new calculator-object.
     *
     * @param calculatorUI the UI-class for this object
     */
    public Calculator(CalculatorUI calculatorUI) {
        _solution = 0;
        _firstOperand = 0;
        _pendingOperator = "";
        _startNewInput = true;
        _ui = calculatorUI;

        if (_ui != null) {
            registerUIActions();
            _ui.show();
        }
    }

    /**
     * Registers all event handlers for the UI controls.
     */
    private void registerUIActions() {
        _ui.getExitButton().setOnAction(ae -> _ui.close());

        // Register digit buttons 0-9
        for (int i = 0; i <= 9; i++) {
            final int digit = i;
            _ui.getNumberButton(digit).setOnAction(ae -> handleDigitInput(String.valueOf(digit)));
        }

        // Decimal point
        _ui.getDecimalButton().setOnAction(ae -> handleDecimalInput());

        // Operators
        _ui.getPlusButton().setOnAction(ae -> handleOperatorInput("+"));
        _ui.getMinusButton().setOnAction(ae -> handleOperatorInput("-"));
        _ui.getTimesButton().setOnAction(ae -> handleOperatorInput("*"));
        _ui.getDivideButton().setOnAction(ae -> handleOperatorInput("/"));

        // Equals & Clear
        _ui.getEqualsButton().setOnAction(ae -> handleEqualsInput());
        _ui.getClearButton().setOnAction(ae -> handleClearAll());
        _ui.getClearEntryButton().setOnAction(ae -> handleClearEntry());
    }

    private void handleDigitInput(String digit) {
        String currentText = _ui.getDisplayValue();
        if (_startNewInput || "0".equals(currentText) || "Fehler".equals(currentText)) {
            _ui.setDisplayValue(digit);
            _startNewInput = false;
        } else {
            _ui.setDisplayValue(currentText + digit);
        }
    }

    private void handleDecimalInput() {
        String currentText = _ui.getDisplayValue();
        if (_startNewInput || "Fehler".equals(currentText)) {
            _ui.setDisplayValue("0.");
            _startNewInput = false;
        } else if (!currentText.contains(".")) {
            _ui.setDisplayValue(currentText + ".");
        }
    }

    private void handleOperatorInput(String operator) {
        String currentText = _ui.getDisplayValue();

        if ("Fehler".equals(currentText)) {
            return;
        }

        if (!_pendingOperator.isEmpty() && !_startNewInput) {
            handleEqualsInput();
            currentText = _ui.getDisplayValue();
        }

        try {
            _firstOperand = Double.parseDouble(currentText);
            _pendingOperator = operator;
            _startNewInput = true;
        } catch (NumberFormatException e) {
            _ui.setDisplayValue("Fehler");
        }
    }

    private void handleEqualsInput() {
        if (_pendingOperator.isEmpty()) {
            return;
        }

        String currentText = _ui.getDisplayValue();
        if ("Fehler".equals(currentText)) {
            return;
        }

        try {
            double secondOperand = Double.parseDouble(currentText);
            double result = calculate(_firstOperand, secondOperand, _pendingOperator);

            if (Double.isNaN(result) || Double.isInfinite(result)) {
                _ui.setDisplayValue("Fehler");
            } else {
                _solution = result;
                _ui.setDisplayValue(formatResult(result));
            }
        } catch (NumberFormatException e) {
            _ui.setDisplayValue("Fehler");
        }

        _pendingOperator = "";
        _startNewInput = true;
    }

    private void handleClearAll() {
        _solution = 0;
        _firstOperand = 0;
        _pendingOperator = "";
        _startNewInput = true;
        _ui.setDisplayValue("0");
    }

    private void handleClearEntry() {
        _startNewInput = true;
        _ui.setDisplayValue("0");
    }

    /**
     * Performs arithmetic operations (+, -, *, /).
     *
     * @param firstOperand first number
     * @param secondOperand second number
     * @param operator arithmetic operator string
     * @return operation result or Double.NaN on division by zero
     */
    public double calculate(double firstOperand, double secondOperand, String operator) {
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                if (secondOperand == 0) {
                    return Double.NaN;
                }
                return firstOperand / secondOperand;
            default:
                throw new IllegalArgumentException("Unbekannter Operator: " + operator);
        }
    }

    /**
     * Formats a double value for display (removes trailing .0 for whole numbers).
     */
    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.valueOf(result);
        }
    }

    /**
     * @return the _solution
     */
    public double get_solution()
    {
        return _solution;
    }

    /**
     * @param _solution the _solution to set
     */
    public void set_solution(double _solution)
    {
        this._solution = _solution;
    }
}
