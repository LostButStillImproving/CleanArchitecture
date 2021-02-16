package com.example.cleanarchitecture.model;

import rx.subjects.PublishSubject;

public enum ObservableTextField {

    TEXT_FIELD;

    private final PublishSubject<String> text = PublishSubject.create();

    public void newText(String text) {
        this.text.onNext(text);
    }
    public rx.Observable<String> getText() {

        return text;
    }
}
