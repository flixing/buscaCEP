package com.mulataporno.ceprest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCep = (Button) findViewById(R.id.btnCep);

        btnCep.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuscaCep.class);
                startActivity(intent);
            }
        });

        Button btnHistorico = (Button) findViewById(R.id.btnHistorico);
        btnHistorico.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lista.class );
                startActivity(intent);
            }
        });




    }
}
