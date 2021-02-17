package com.example.cleanarchitecture.view;

import com.example.cleanarchitecture.model.ObservableTextField;

public class TrueCasePresenter implements Presenter {

    String text = "";

    public TrueCasePresenter() {
        subscribe();
    }

    @Override
    public void subscribe() {

        ObservableTextField.TEXT_FIELD.getText().subscribe(this::setToTrueCase);
    }

    private void setToTrueCase(String s) {

        text = s;
    }

    @Override
    public String getText() {

        return text;
    }
}
