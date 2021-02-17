package com.example.cleanarchitecture.persistence.sqlite;

import com.example.cleanarchitecture.model.ObservableTextField;
import com.example.cleanarchitecture.persistence.Repository;

public class SQLiteDatabase implements Repository {


    public SQLiteDatabase() {
        subscribe();
    }

    @Override
    public void persistText(String s) {

    }

    @Override
    public String loadLastText() {
        return null;
    }

    @Override
    public void subscribe() {
        ObservableTextField.TEXT_FIELD.getText().subscribe(this::persistText);
    }
}
