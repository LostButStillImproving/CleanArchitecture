package com.example.cleanarchitecture.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.model.ObservableTextField;
import com.example.cleanarchitecture.persistence.Repository;
import com.example.cleanarchitecture.persistence.firebase.FireStoreImpl;
import com.example.cleanarchitecture.persistence.sqlite.SQLiteRepository;

public class MainActivity extends AppCompatActivity  {
    final String database = "SQLITE";  /// SET EITHER SQLITE OR FIREBASE
    final String presenter = "UPPERCASE"; /// SET TRUECASE, LOWERCASE, UPPERCASE

    Presenter casePresenter;
    Repository repositoryDB;

    public void setPresenter() {
        if (presenter.equals("TRUECASE")) {
            casePresenter = new TrueCasePresenter();
        }
        if (presenter.equals("LOWERCASE")) {
            casePresenter = new LowerCasePresenter();
        }
        if (presenter.equals("UPPERCASE")) {
            casePresenter = new UpperCasePresenter();
        }
    }
    public void setDatabase() {
        if (database.equals("SQLITE")) {
            repositoryDB = new SQLiteRepository(this);
        }
        if (database.equals("FIREBASE")) {
            repositoryDB = new FireStoreImpl();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        setPresenter();
        setDatabase();
        TextView textView = findViewById(R.id.textView);
        repositoryDB.callbackLatestText(textView::setText);
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