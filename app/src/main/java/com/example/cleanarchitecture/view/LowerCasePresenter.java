package com.example.cleanarchitecture.view;

import com.example.cleanarchitecture.model.ObservableTextField;

public class LowerCasePresenter implements Presenter {

    String text ="";

    public LowerCasePresenter() {
        subscribe();
    }

    public void subscribe() {
        ObservableTextField.TEXT_FIELD.getText().subscribe(this::setToLowerCase);
    }

    private void setToLowerCase(String s) {
        text = s.toLowerCase();
    }

    public String getText() {
        return text;
    }
}
