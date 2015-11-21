package com.mulataporno.ceprest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mulataporno.ceprest.db.DB;
import com.mulataporno.ceprest.model.CEP;

import java.util.List;

/**
 * Created by Digitador01 on 20/11/2015.
 */
public class Lista extends AppCompatActivity {
    private ListView listView;
    private List<CEP> list;
    private ArrayAdapter<CEP> adapter;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listView = (ListView) findViewById(R.id.listView);

        db = new DB(this);

        list = db.listarPlacas();


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CEP cep = (CEP) parent.getItemAtPosition(position);
                int itemId = cep.getId();
                String valor = String.valueOf(itemId);

                Bundle params = new Bundle();
                params.putString("itemId", valor);

                Intent intent = new Intent(getBaseContext(), Detalhes.class);
                intent.putExtras(params);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        CEP cep = list.get(info.position);
        switch (item.getItemId()) {
            case R.id.excluir:
                db.delete(cep.getId());
                list.remove(cep);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
