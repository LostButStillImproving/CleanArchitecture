package com.example.cleanarchitecture.view;
import com.example.cleanarchitecture.model.ObservableTextField;

public class UpperCasePresenter implements Presenter {

    String text = "";

    public UpperCasePresenter() {
        subscribe();
    }

    @Override
    public void subscribe() {
        ObservableTextField.TEXT_FIELD.getText().subscribe(this::setToUpperCase);
    }

    public void setToUpperCase(String s) {
        text = s.toUpperCase();
    }

    @Override
    public String getText() {

        return text;
    }

    @Override
    public String treatAndReturnText(String text) {
        return text.toUpperCase();
    }
}
