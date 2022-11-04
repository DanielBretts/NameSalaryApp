package com.example.namesalaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    TextInputEditText name;
    TextInputEditText salary;
    MaterialButton calculate;
    MaterialTextView name_txt;
    View nameLayout;
    MaterialTextView salary_txt;
    View salaryLayout;
    MaterialTextView error;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculate = (MaterialButton) findViewById(R.id.main_BTN_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismissKeyboard();
                showText();
            }
        });
    }

    private boolean isValidData(String salaryStr, String nameStr) {
        if (salaryStr == null || nameStr == null ) {
            return false;
        }
        try {
            int salary = Integer.parseInt(salaryStr);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void setError(){
        showError();
        showAnimationError();
    }

    private void showAnimationError() {
        salaryLayout = findViewById(R.id.main_TIL_salary);
        nameLayout = findViewById(R.id.main_TIL_name);
        YoYo.with(Techniques.Shake)
                .duration(300)
                .repeat(1)
                .playOn(salaryLayout);
        YoYo.with(Techniques.Shake)
                .duration(300)
                .repeat(1)
                .playOn(nameLayout);
    }


    private void showError() {
        error = findViewById(R.id.main_TXT_error);
        error.setText("Invalid input");
        error.setTextColor(Color.RED);
    }

    private void showText() {
        name = findViewById(R.id.main_TIET_name);
        salary = findViewById(R.id.main_TIET_salary);
        name_txt = findViewById(R.id.main_TXT_name);
        salary_txt = findViewById(R.id.main_TXT_salary);
        String nameStr = name.getText().toString();
        String salaryStr = salary.getText().toString();
        if(isValidData(salaryStr,nameStr)){
            error.setText("");
            name_txt.setText(name.getText());
            salary_txt.setText(salary.getText());
        }
        else setError();
    }

    private void dismissKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}