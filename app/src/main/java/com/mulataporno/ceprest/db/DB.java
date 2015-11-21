package com.mulataporno.ceprest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mulataporno.ceprest.model.CEP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Digitador01 on 20/11/2015.
 */
public class DB {
    private static final String NOME_TABLE = "listaceps";

    private static final String COLUNA_ID = "_id";
    private static final String COLUNA_CEP = "cep";
    private static final String COLUNA_LOGRADOURO = "logradouro";
    private static final String COLUNA_COMPLEMENTO = "complemento";
    private static final String COLUNA_BAIRRO = "bairro";
    private static final String COLUNA_LOCALIDADE = "localidade";
    private static final String COLUNA_UF = "uf";
    private static final String COLUNA_IBGE = "ibge";


    private DBCore dbCore;

    private SQLiteDatabase db;

    private Context context;

    public DB(Context context) {
        this.context = context;
        dbCore = new DBCore(this.context);
        db = dbCore.getWritableDatabase();
    }

    public void salvar(CEP cep) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUNA_CEP, cep.getCep());
        contentValues.put(COLUNA_LOGRADOURO, cep.getLogradouro());
        contentValues.put(COLUNA_COMPLEMENTO, cep.getComplemento());
        contentValues.put(COLUNA_BAIRRO, cep.getBairro());
        contentValues.put(COLUNA_LOCALIDADE, cep.getLocalidade());
        contentValues.put(COLUNA_UF, cep.getUf());
        contentValues.put(COLUNA_IBGE, cep.getIbge());

        db.insert(NOME_TABLE, null, contentValues);
    }

    public CEP getSingleCep(int _id) {
        Cursor cursor = db.query(NOME_TABLE, new String[]{COLUNA_ID, COLUNA_CEP, COLUNA_LOGRADOURO, COLUNA_COMPLEMENTO,
                COLUNA_BAIRRO, COLUNA_LOCALIDADE, COLUNA_UF, COLUNA_IBGE}, COLUNA_ID + "=?",
                new String[]{String.valueOf(_id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        CEP cep = new CEP(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
        return cep;
    }

    public  void delete(int _id){
        db.delete(NOME_TABLE, "_id = ?", new String[]{""+_id});
    }

    public List<CEP> listarPlacas() {
        List<CEP> list = new ArrayList<>();
        String[] colunas = {"_id", COLUNA_CEP};

        Cursor cursor = db.query(NOME_TABLE, colunas, null, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            do {
                CEP cep = new CEP();

                cep.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id"))));
                cep.setCep(cursor.getString(cursor.getColumnIndex(COLUNA_CEP)));

                list.add(cep);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
