/*
 *  * Copyright 2024 Patrick James Callaghan
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 */

/*
 * ----------------------------------------------------------------------------
 * Program Info: Calculator GUI
 * ----------------------------------------------------------------------------
 * Program:Calculator GUI - Android Java
 * Date: November 12th, 2024
 * Author: Patrick Callaghan
 * Operation: The `MainActivity` class provides basic calculator functionality,
 *           allowing users to input two numbers and perform operations
 *           (addition, subtraction, multiplication, and division). It includes
 *           input validation, error handling, and displays the result with
 *           color feedback. The `PatrickButton` class is a custom button that,
 *           when clicked, shows device information such as Android version,
 *           screen size, and DPI metrics, offering users insights into their
 *           device’s specifications.
 *

 * ----------------------------------------------------------------------------
 * Change Log:        November 12th, 2024           Change Log by Patrick Callaghan
 * ----------------------------------------------------------------------------
 * Day             Programmer      Change:
 * ----------------------------------------------------------------------------
 * Program Info: Calculator GUI
 * ----------------------------------------------------------------------------
 * Program: Average Calculator GUI - Android Java
 * Date: November 12th, 2024
 * Author: Patrick Callaghan
 * Operation: The `MainActivity` class provides basic calculator functionality,
 *           allowing users to input two numbers and perform operations
 *           (addition, subtraction, multiplication, and division). It includes
 *           input validation, error handling, and displays the result with
 *           color feedback. The `PatrickButton` class is a custom button that,
 *           when clicked, shows device information such as Android version,
 *           screen size, and DPI metrics, offering users insights into their
 *           device’s specifications.
 *

 * ----------------------------------------------------------------------------
 * Change Log:        November 12th, 2024           Change Log by Patrick Callaghan
 * ----------------------------------------------------------------------------
 * Day             Programmer      Change:
 * ----------------------------------------------------------------------------
 * November 12th   Patrick Callaghan  1) All code blocks are indented using 2 spaces
 *                                       for clarity and consistent formatting.
 *
 *                                     2) The variable names num1Input, num2Input, and
 *                                        answerDisplay are in camelCase; the class name
 *                                        CalculatorApp follows PascalCase and the constant
 *                                        ERROR_MESSAGE follows UPPER_SNAKE_CASE.

 *                                     3) Replaced the number 0.0 with the constant DIVISION_BY_ZERO
 *                                        to make it more readable and easy to maintain. The error message
 *                                        for division by zero is now stored in the constant ERROR_ZERO_DIVISION.
 *                                        This way, if the message needs to be updated, you only need to change
 *                                        it in one place.

 *                                     4) Added Javadoc comment to the performCalculation method,
 *                                        and added more detailed comments throughout the code for
 *                                        clarity and better maintainability.

 *                                     5) Using braces around every if, else, for, while, and switch block,
 *                                        even if there’s only one statement, prevents bugs and ensures that
 *                                        all developers follow the same pattern.

 *                                     6) Using final makes it clear that the values of these variables
 *                                        should not change, providing more safety in the code. This also
 *                                        helps developers quickly identify that these variables are
 *                                        immutable after their initial assignment, reducing the chance
 *                                        of bugs or unintended behavior.
 * ----------------------------------------------------------------------------

 */


package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private EditText num1Input, num2Input, answerDisplay;

    // ImageButton variables for calculator operations and clear function
    private ImageButton buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_screen_size);

        num1Input = findViewById(R.id.editText_Num1);
        num2Input = findViewById(R.id.editText_Num2);
        answerDisplay = findViewById(R.id.editText_Answer);
        answerDisplay.setKeyListener(null);
        buttonAdd = findViewById(R.id.button_Add);
        buttonSubtract = findViewById(R.id.button_Subtract);
        buttonMultiply = findViewById(R.id.button_Multiply);
        buttonDivide = findViewById(R.id.button_Divide);
        buttonClear = findViewById(R.id.button_Clear);

        // Set onClickListener for the Add button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Calculator", "Add button clicked");
                performCalculation('+'); // Perform addition
            }
        });

        // Set onClickListener for the Subtract button
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Calculator", "Subtract button clicked");
                performCalculation('-');
            }
        });

        // Set onClickListener for the Multiply button
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Calculator", "Multiply button clicked");
                performCalculation('*');
            }
        });

        // Set onClickListener for the Divide button
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Calculator", "Divide button clicked");
                performCalculation('/');
            }
        });

        // Set onClickListener for the Clear button
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Calculator", "Clear button clicked");
                clearFields();
            }
        });
    }

    /**
     * This method performs the calculation based on the operator passed.
     * It handles addition, subtraction, multiplication, and division operations.
     *
     * @param operator The operator for the calculation (+, -, *, /).
     */
    private static final double DIVISION_BY_ZERO = 0.0;
    private static final String ERROR_ZERO_DIVISION = "Cannot divide by zero";
    private static final String ERROR_MESSAGE = "Invalid input"; // Already defined

    // Method to perform the calculation based on the operator
    private void performCalculation(char operator) {
        final double num1, num2;
        double result = 0;

        try {
            // Check if either input field is empty
            if (num1Input.getText().toString().isEmpty() || num2Input.getText().toString().isEmpty()) {
                answerDisplay.setText("Please enter both numbers");
                return;
            }

            // Parse the input numbers from the EditText fields
            num1 = Double.parseDouble(num1Input.getText().toString());
            num2 = Double.parseDouble(num2Input.getText().toString());

            // Perform the operation based on the operator
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == DIVISION_BY_ZERO) {
                        answerDisplay.setText(ERROR_ZERO_DIVISION);
                        return;
                    } else {
                        result = num1 / num2;
                    }
                    break;
            }

            // Display the result in the answer EditText field
            answerDisplay.setText(String.valueOf(result));
            Log.d("Calculator", "Operation: " + num1 + " " + operator + " " + num2 + " = " + result);

        } catch (NumberFormatException e) {
            // Handle invalid number inputs
            answerDisplay.setText(ERROR_MESSAGE);
            Log.e("Calculator", "Error: Invalid input", e);
        }
    }


    // Method to clear all input and output fields
    private void clearFields() {
        Log.d("ClearFields", "Clearing input and answer fields");
        num1Input.setText("");
        num2Input.setText("");
        answerDisplay.setText("");
    }
}
