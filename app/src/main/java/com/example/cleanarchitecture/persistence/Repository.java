package com.example.cleanarchitecture.persistence;

import com.example.cleanarchitecture.persistence.firebase.FireStoreImpl;
import com.google.firebase.firestore.FirebaseFirestore;

public interface Repository {

    void persistText(String s);

    void subscribe();

    void fetchLatestText(CallBack callBack);

    Object getInstance();
}
