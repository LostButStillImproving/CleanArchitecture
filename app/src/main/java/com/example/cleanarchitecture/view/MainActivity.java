package com.example.cleanarchitecture.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.model.ObservableTextField;

public class MainActivity extends AppCompatActivity  {

    Presenter casePresenter;

    public void setPresenter(Presenter presenter) {

        casePresenter = presenter;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        setPresenter(new TrueCasePresenter());
        super.onCreate(savedInstanceState);
    }

    public void onButtonClick(View view) {

        EditText simpleEditText = findViewById(R.id.simpleEditText);
        String text = simpleEditText.getText().toString();
        ObservableTextField.TEXT_FIELD.newText(text);
        TextView textView = findViewById(R.id.textView);
        textView.setText(casePresenter.getText());
    }
}