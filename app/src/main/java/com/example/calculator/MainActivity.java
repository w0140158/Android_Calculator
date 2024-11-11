package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    // EditText fields for user inputs and the answer display
    private EditText editTextNum1, editTextNum2, editTextAnswer;

    // ImageButton variables for calculator operations and clear function
    private ImageButton buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the UI layout for this activity
        setContentView(R.layout.activity_main_activity_screen_size);

        // Initialize EditText fields by finding them by ID
        editTextNum1 = findViewById(R.id.editText_Num1);
        editTextNum2 = findViewById(R.id.editText_Num2);
        editTextAnswer = findViewById(R.id.editText_Answer);

        // Make the answer EditText non-editable by the user
        editTextAnswer.setKeyListener(null);

        // Initialize ImageButtons by finding them by ID
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

    // Method to perform the calculation based on the operator
    private void performCalculation(char operator) {
        double num1, num2, result = 0;

        try {
            // Check if either input field is empty
            if (editTextNum1.getText().toString().isEmpty() || editTextNum2.getText().toString().isEmpty()) {
                editTextAnswer.setText("Please enter both numbers");
                return;
            }

            // Parse the input numbers from the EditText fields
            num1 = Double.parseDouble(editTextNum1.getText().toString());
            num2 = Double.parseDouble(editTextNum2.getText().toString());

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
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        // Handle division by zero
                        editTextAnswer.setText("Cannot divide by zero");
                        return;
                    }
                    break;
            }

            // Display the result in the answer EditText field
            editTextAnswer.setText(String.valueOf(result));
            Log.d("Calculator", "Operation: " + num1 + " " + operator + " " + num2 + " = " + result);

        } catch (NumberFormatException e) {
            // Handle invalid number inputs
            editTextAnswer.setText("Invalid input");
            Log.e("Calculator", "Error: Invalid input", e);
        }
    }

    // Method to clear all input and output fields
    private void clearFields() {
        Log.d("ClearFields", "Clearing input and answer fields");
        editTextNum1.setText("");
        editTextNum2.setText("");
        editTextAnswer.setText("");
    }
}
