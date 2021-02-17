package com.example.cleanarchitecture.persistence;

public interface Repository {

    void persistText(String s);

    String loadLastText();

    void subscribe();
}
