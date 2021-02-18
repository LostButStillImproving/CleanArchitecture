package com.example.cleanarchitecture.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.model.ObservableTextField;
import com.example.cleanarchitecture.persistence.DatabaseEnums;
import com.example.cleanarchitecture.persistence.Repository;
import com.example.cleanarchitecture.persistence.firebase.FireStoreImpl;
import com.example.cleanarchitecture.persistence.sqlite.SQLiteImpl;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import static com.example.cleanarchitecture.persistence.DatabaseEnums.*;
import static com.example.cleanarchitecture.view.PresenterEnums.*;

public class MainActivity extends AppCompatActivity  {

    final DatabaseEnums database = FIREBASE;  /// SET EITHER SQLITE OR FIREBASE
    final PresenterEnums presenter = LOWER_CASE; /// SET EITHER TRUECASE, LOWERCASE, UPPERCASE

    Presenter casePresenter;
    Repository repository;

    public void setPresenter() {
        if (presenter.equals(TRUE_CASE)) {
            casePresenter = new TrueCasePresenter();
        }
        if (presenter.equals(LOWER_CASE)) {
            casePresenter = new LowerCasePresenter();
        }
        if (presenter.equals(UPPER_CASE)) {
            casePresenter = new UpperCasePresenter();
        }
    }
    public void setDatabase() {
        if (database.equals(SQLITE)) {
            repository = new SQLiteImpl(this);
        }

        if (database.equals(FIREBASE)) {
            repository = new FireStoreImpl();
            attachFireBaseChangeListener();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setPresenter();
        setDatabase();
        TextView textView = findViewById(R.id.textView);
        repository.fetchLatestText(textView::setText);
        super.onCreate(savedInstanceState);
    }



    public void onButtonClick(View view) {
        EditText simpleEditText = findViewById(R.id.simpleEditText);
        String text = simpleEditText.getText().toString();
        ObservableTextField.TEXT_FIELD.setText(text);
        TextView textView = findViewById(R.id.textView);
        textView.setText(casePresenter.getText());
    }

    private void attachFireBaseChangeListener() {
        FirebaseFirestore instance = FirebaseFirestore.getInstance();
        instance.collection("texts").addSnapshotListener((value, e) -> {
            if (e != null) {
                Log.w("snapshotlistener", "Listen failed.", e);
                return;
            }
            TextView textView = findViewById(R.id.textView);
            repository.fetchLatestText(textView::setText);
        });
    }
}