package com.example.emicalculator;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    // UI Elements are declared for input fields and calculate button
    EditText et_principal;
    EditText et_tenure;
    EditText et_interest;
    Button b_calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialized UI elements
        et_principal = findViewById(R.id.et_principal);
        et_tenure = findViewById(R.id.et_tenure);
        et_interest = findViewById(R.id.et_interest);
        b_calculate = findViewById(R.id.b_calculate);

        // Set a click listener for the "Calculate EMI" button
        b_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateEMI();
            } // calcualteEMI() method is called
        });

    }

    // Calculate EMI method
    private void calculateEMI() {
        // Get user input
        double principalAmount = Double.parseDouble(et_principal.getText().toString());
        double annualInterestRate = Double.parseDouble(et_interest.getText().toString());
        int tenureMonths = Integer.parseInt(et_tenure.getText().toString());

        // Calculate monthly interest rate
        double monthlyInterestRate = (annualInterestRate / 12) / 100;

        // Calculate EMI using the formula
        double emi = (principalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths))
                / (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);

        // Calculate total paid and interest paid
        double total_paid = emi * tenureMonths;
        double interest_paid = total_paid - principalAmount;

        // Create Intent to navigate to PaymentResult activity
        Intent intent = new Intent(MainActivity.this, PaymentResult.class);

        // Add key-value pairs to Intent to pass onto PaymentResult
        intent.putExtra("mortgage", String.format("$%.2f",principalAmount));
        intent.putExtra("interest", et_interest.getText().toString() + "%");
        intent.putExtra("tenure", et_tenure.getText().toString());
        intent.putExtra("emi_result", emi);
        intent.putExtra("total_paid", String.format("$%.2f",total_paid));
        intent.putExtra("interest_paid", String.format("$%.2f",interest_paid));

        // PaymentResult activity is started
        startActivity(intent);

    }

}