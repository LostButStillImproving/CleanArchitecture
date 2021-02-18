package com.example.cleanarchitecture.persistence.firebase;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cleanarchitecture.model.ObservableTextField;
import com.example.cleanarchitecture.persistence.Repository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FireStoreImpl implements Repository {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public FireStoreImpl() {
        subscribe();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void persistText(String s) {

        Map<String, Object> text = new HashMap<>();
        Timestamp timeStamp = new Timestamp(new Date());
        text.put("text", s);
        text.put("time", timeStamp);
        db.collection("texts").add(text);

    }

    @Override
    public String callbackLatestText(CallBack callBack) {
        CollectionReference texts = db.collection("texts");
        Query getLatestText = texts.orderBy("time", Query.Direction.DESCENDING).limit(1);

        getLatestText.get().addOnCompleteListener((OnCompleteListener<QuerySnapshot>) task -> {
            QuerySnapshot querySnapshot = (task.getResult());
            String text = (String) querySnapshot.getDocuments().get(0).getData().get("text");
            callBack.onCallback(text);
        });

        return null;
    }

    @Override
    public void subscribe() {
        ObservableTextField.TEXT_FIELD.getText().subscribe(this::persistText);
    }
}

