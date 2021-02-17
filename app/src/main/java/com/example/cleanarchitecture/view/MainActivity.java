package com.example.cleanarchitecture.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.model.ObservableTextField;
import com.example.cleanarchitecture.persistence.Repository;
import com.example.cleanarchitecture.persistence.sqlite.SQLiteRepository;

public class MainActivity extends AppCompatActivity  {

    Presenter casePresenter;
    Repository repositoryDB;

    public void setPresenter(Presenter presenter) {

        casePresenter = presenter;
    }
    public void setDatabase(Repository repository ) {
        repositoryDB = repository;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        setPresenter(new TrueCasePresenter());
        setDatabase(new SQLiteRepository(this));
        TextView textView = findViewById(R.id.textView);
        textView.setText(repositoryDB.loadLastText());

        super.onCreate(savedInstanceState);
    }

    public void onButtonClick(View view) {

        EditText simpleEditText = findViewById(R.id.simpleEditText);
        String text = simpleEditText.getText().toString();
        ObservableTextField.TEXT_FIELD.setText(text);
        TextView textView = findViewById(R.id.textView);
        textView.setText(casePresenter.getText());
    }
}