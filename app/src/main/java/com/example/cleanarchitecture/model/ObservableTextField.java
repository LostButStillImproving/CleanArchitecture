package com.example.cleanarchitecture.model;

import rx.subjects.PublishSubject;

public enum ObservableTextField {

    TEXT_FIELD;

    private final PublishSubject<String> newText = PublishSubject.create();

    public void newText(String text) {
        newText.onNext(text);
    }
    public rx.Observable<String> getText() {

        return newText;
    }
}
