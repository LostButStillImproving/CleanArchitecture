package com.example.cleanarchitecture.view;

public interface Presenter {

    void subscribe();

    String getText();

    String treatAndReturnText(String text);
}
