package com.example.cleanarchitecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        EditText simpleEditText = (EditText) findViewById(R.id.simpleEditText);
        String text = simpleEditText.getText().toString();
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(text);
    }
}