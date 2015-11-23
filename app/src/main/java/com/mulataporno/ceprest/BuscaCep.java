package com.mulataporno.ceprest;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mulataporno.ceprest.db.DB;
import com.mulataporno.ceprest.library.HTTPDataHandler;
import com.mulataporno.ceprest.model.CEP;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Digitador01 on 20/11/2015.
 */
public class BuscaCep extends AppCompatActivity {

    String cepEntrada = "";
    String urlapi = "";

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_cep);


        final Button btnBuscarCEP = (Button) findViewById(R.id.btnChamaBuscaCEP);
        final EditText editTextCep = (EditText) findViewById(R.id.edtCep);

        btnBuscarCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TemConexao()){

                    CEP cep = new CEP();
                    cepEntrada = editTextCep.getText().toString();
                    editTextCep.setText("");
                    urlapi = "https://viacep.com.br/ws/" + cepEntrada + "/json/";
                    if(cepEntrada.length() == 8){
                        progressDialog =ProgressDialog.show(BuscaCep.this, "Caregando . . . ","", true);
                        progressDialog.setCancelable(true);
                        new ProcessJSON().execute(urlapi);
                    }else{
                        Toast.makeText(getApplicationContext(), "Por favor informe um CEP válido.", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Por favor verifique a conectividade de seu dispositivo.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean TemConexao() {
        boolean lblnRet = false;
        try
        {
            ConnectivityManager cm = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
                lblnRet = true;
            } else {
                lblnRet = false;
            }
        }catch (Exception e) {

        }
        return lblnRet;
    }

    public void toast (String msg){
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            return stream;
        }

        protected void onPostExecute(String stream){

            // Toast.makeText(getApplicationContext(), "stream -" + stream, Toast.LENGTH_SHORT).show();

            if(stream !=null){
                try{
                    // Get the full HTTP Data as JSONObject

                    JSONObject reader= new JSONObject(stream);

                    String cep = reader.getString("cep");
                    String logradouro = reader.getString("logradouro");
                    String complemento = reader.getString("complemento");
                    String bairro = reader.getString("bairro");
                    String localidade = reader.getString("localidade");
                    String uf = reader.getString("uf");
                    String ibge = reader.getString("ibge");

                    DB db = new DB(BuscaCep.this);
                    CEP c = new CEP();
                    c.setCep(cep);
                    c.setLogradouro(logradouro);
                    c.setComplemento(complemento);
                    c.setBairro(bairro);
                    c.setLocalidade(localidade);
                    c.setUf(uf);
                    c.setIbge(ibge);
                    db.salvar(c);

                    progressDialog.dismiss();

                    final TextView txtCep = (TextView) findViewById(R.id.txtBC_cep);
                    final TextView txtLogradouro = (TextView) findViewById(R.id.txtBC_logradouro);
                    final TextView txtComplemento = (TextView) findViewById(R.id.txtBC_complemento);
                    final TextView txtBairro = (TextView) findViewById(R.id.txtBC_bairro);
                    final TextView txtLocalidade = (TextView) findViewById(R.id.txtBC_localidade);
                    final TextView txtIbge = (TextView) findViewById(R.id.txtBC_ibge);


                    txtCep.setText("CEP "+cep);
                    if(logradouro != " "){
                        txtLogradouro.setText("LOGRA. "+logradouro);
                    }else{
                        txtLogradouro.setText("SEM LOGRADOURO");
                    }
                    if(complemento != " "){
                        txtComplemento.setText("COMPL. "+complemento);
                    }else{
                        txtComplemento.setText("SEM COMPLEMENTO");
                    }
                    if(bairro != " "){
                        txtBairro.setText("BAIRRO  "+bairro);
                    }else{
                        txtBairro.setText("SEM BAIRRO");
                    }
                    txtLocalidade.setText(localidade + " / " + uf);
                    txtIbge.setText("IBGE "+ibge);

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
