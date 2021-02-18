package com.example.cleanarchitecture.persistence.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.cleanarchitecture.model.ObservableTextField;
import com.example.cleanarchitecture.persistence.Repository;
import com.example.cleanarchitecture.persistence.CallBack;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class SQLiteImpl extends SQLiteOpenHelper implements Repository {

    private static final String TEXTS_TABLE = "TEXTS_TABLE";
    private static final String COLUMN_CONTENT = "CONTENT";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TEXTS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CONTENT + " TEXT )";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public SQLiteImpl(@Nullable Context context) {
        super(context, "texts.db", null, 1);
        subscribe();
    }

    @Override
    public void persistText(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CONTENT, s);
        db.insert(TEXTS_TABLE, null, contentValues);
    }

    @Override
    public void fetchLatestText(CallBack callBack) {
        String selectLastRowStatement = "SELECT " + COLUMN_CONTENT + " FROM " + TEXTS_TABLE + " ORDER BY ID DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectLastRowStatement, null);

        if (cursor.moveToNext()) {
            String lastText = cursor.getString(0);
            cursor.close();
            callBack.onCallback(lastText);
        }
    }

    @Override
    public void subscribe() {
        ObservableTextField.TEXT_FIELD.getText().subscribe(this::persistText);
    }
}
