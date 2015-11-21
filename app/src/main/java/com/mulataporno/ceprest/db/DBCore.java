package com.mulataporno.ceprest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Digitador01 on 20/11/2015.
 */
public class DBCore extends SQLiteOpenHelper {

    private static  final String NOME_BANCO = "buscacep.db";
    private static  final int VERSAO_BANCO = 1;


    public DBCore(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE listaceps (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + " cep TEXT NOT NULL,"
        + " logradouro TEXT,"
        + " complemento TEXT,"
        + " bairro TEXT,"
        + " localidade TEXT NOT NULL,"
        + " uf TEXT NOT NULL,"
        + " ibge TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE listaceps;");
        onCreate(db);
    }
}
