package com.example.calculatorhesam2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView tventer , tvresult;

    String firstNumber = "";
    String secondNumber = "";
    String operator = "";
    boolean isClickOperator = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tventer = findViewById(R.id.tventer);
        tvresult = findViewById(R.id.tvresult);

    }

    public void onclickNumber(View view) {
        Button button = (Button) view;
        String number = button.getText().toString();

        String lastValue = tventer.getText().toString();

        if (!isClickOperator) {
            firstNumber += number;
        } else {
            secondNumber += number;
        }

        tventer.setText(lastValue + number);
    }

    public void onclickOperator(View view) {
        try {

            Button button = (Button) view;
            if (!operator.equals(""))
                return;
            operator = button.getText().toString();
            String lastValue = tventer.getText().toString();

            isClickOperator = true;
            tventer.setText(lastValue + " " + operator + " ");
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "error is : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void onclickEquals(View view) {
        try{
            String lastValue = tventer.getText().toString();
            double result = calc(operator, Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));

            tventer.setText(String.valueOf(result));
            tvresult.setText(lastValue);

            firstNumber = String.valueOf(result);
            secondNumber = "";
            operator = "";
            isClickOperator = true;

        }catch (Exception e){
            Toast.makeText(MainActivity.this, "error is : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }

    private double calc(String operator , double num1 , double num2){
        switch (operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }
        return 0;
    }

    public void onclickC(View view) {
        firstNumber = "";
        secondNumber = "";
        operator = "";
        isClickOperator = false;
        tvresult.setText(null);
        tventer.setText(null);

    }

    public void onclickBack(View view) {
        String lastValue = tventer.getText().toString();
        if (!lastValue.equals("")) {
            String newValue = lastValue.substring(0, lastValue.length() - 1);

            tventer.setText(newValue);
            firstNumber = newValue;

        }
    }

    public void onclickdarsad(View view) {
        try{
            double lastValue = Double.parseDouble(tventer.getText()+"");
            tventer.setText(lastValue*0.01+"");
            firstNumber = String.valueOf(lastValue*0.01);

        }catch (Exception e){
            Toast.makeText(MainActivity.this, "error is : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void onclickalamat(View view) {
        try{
            double lastValue = Double.parseDouble(tventer.getText()+"");
            tventer.setText(String.valueOf(lastValue*-1));
            firstNumber = String.valueOf(lastValue*-1);

        }catch (Exception e){
            Toast.makeText(MainActivity.this, "error is : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}