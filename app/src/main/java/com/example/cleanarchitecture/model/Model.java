package com.example.cleanarchitecture.model;

import java.util.ArrayList;

public enum Model {
    TEXT;

    ArrayList<ModelObserver> observers = new ArrayList<>();
    String textInEditTextField = "";

    public void setTextInEditTextField(String text) {
        this.textInEditTextField = text;
        notifyObservers();
    }

    public String getTextInEditTextField() {
        return this.textInEditTextField;
    }

    public void attach(ModelObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ModelObserver observer:observers) {
            observer.Update();
        }
    }
}
