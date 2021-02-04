package com.example.cleanarchitecture.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.model.Model;
import com.example.cleanarchitecture.model.ModelObserver;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements ModelObserver {
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Model.TEXT.attach(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        EditText simpleEditText = findViewById(R.id.simpleEditText);
        String text = simpleEditText.getText().toString();
        Model.TEXT.setTextInEditTextField(text);
    }

    @Override
    public void Update() {

        TextView textView = findViewById(R.id.textView);
        textView.setText(Model.TEXT.getTextInEditTextField());
    }
}