package com.example.cleanarchitecture.persistence;

import com.example.cleanarchitecture.persistence.firebase.CallBack;

public interface Repository {

    void persistText(String s);

    void subscribe();

    String callbackLatestText(CallBack callBack);


}
