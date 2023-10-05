package com.example.emicalculator;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
public class PaymentResult extends AppCompatActivity{

    TextView mortgage;
    TextView interest;
    TextView tenure;
    TextView emi_result;
    TextView balance_label;
    TextView total_paid;
    TextView interest_paid;
    TextView princ_paid;

    Button b_start_over;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_results);

        mortgage = findViewById(R.id.mortgage);
        interest = findViewById(R.id.interest_rate);
        tenure = findViewById(R.id.tenure_months);
        emi_result = findViewById(R.id.emi_result);

        balance_label = findViewById(R.id.bal_label);
        total_paid = findViewById(R.id.total_paid);
        interest_paid = findViewById(R.id.interest_paid);
        princ_paid = findViewById(R.id.princ_paid);
        b_start_over = findViewById(R.id.b_start_over);

        mortgage.setText(getIntent().getStringExtra("mortgage"));
        interest.setText(getIntent().getStringExtra("interest"));
        tenure.setText(getIntent().getStringExtra("tenure"));
        double emi = getIntent().getDoubleExtra("emi_result", 0.0);

        // Create a SpannableStringBuilder
        SpannableStringBuilder builder = new SpannableStringBuilder();

        String perMonthText = "/month";

        // Set the font size for perMonthText to be smaller
        SpannableString spannableString2 = new SpannableString(perMonthText);
        spannableString2.setSpan(new RelativeSizeSpan(0.5f), 0, perMonthText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Combine the parts
        builder.append(String.format("$%.2f" , emi));
        builder.append(spannableString2);

        emi_result.setText(builder);

        balance_label.setText("Balance after " + tenure.getText().toString() + "-month period");
        total_paid.setText(getIntent().getStringExtra("total_paid"));
        interest_paid.setText(getIntent().getStringExtra("interest_paid"));
        princ_paid.setText(getIntent().getStringExtra("mortgage"));


        b_start_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentResult.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


}