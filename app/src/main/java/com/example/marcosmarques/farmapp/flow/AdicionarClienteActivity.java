package com.example.marcosmarques.farmapp.flow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcosmarques.farmapp.R;
import com.example.marcosmarques.farmapp.model.Cliente;

import java.util.UUID;

import io.realm.Realm;

public class AdicionarClienteActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvTelefone;
    private TextView tvEmail;
    private TextView tvEndereco;
    private TextView tvCpf;
    private Button btSalvar;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_cliente);
        setTitle("Novo Cliente");

        realm = Realm.getDefaultInstance();

        tvCpf = findViewById(R.id.cpf_novo_cliente);
        tvEmail = findViewById(R.id.email_novo_cliente);
        tvEndereco = findViewById(R.id.endereco_novo_cliente);
        tvNome = findViewById(R.id.nome_novo_cliente);
        tvTelefone = findViewById(R.id.telefone_novo_cliente);
        btSalvar = findViewById(R.id.botao_salvar_cliente);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });

    }

    private void salvarCliente(){
        //validação do nome
        if(tvNome == null || tvNome.getText().equals("")){
            Toast.makeText(this, "Digite o nome do cliente antes de salvar.", Toast.LENGTH_SHORT).show();
        }else if(tvEndereco == null || tvEndereco.getText().equals("")){
            Toast.makeText(this, "Digite o endereço do cliente antes de salvar", Toast.LENGTH_SHORT).show();
        }else if(tvTelefone == null || tvTelefone.getText().equals("")){
            Toast.makeText(this, "Digite o telefone do cliente antes de salvar", Toast.LENGTH_SHORT).show();
        }else if(tvCpf == null || tvCpf.getText().equals("")){
            Toast.makeText(this, "Digite o cpf do cliente antes de salvar", Toast.LENGTH_SHORT).show();
        }else{
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {

                    Cliente cliente = bgRealm.createObject(Cliente.class, UUID.randomUUID().toString());
                    Log.d("NOME - ",tvNome.getText().toString());
                    cliente.setNome(tvNome.getText().toString());
                    Log.d("CPF - ",tvCpf.getText().toString());
                    cliente.setCpf(tvCpf.getText().toString());
                    Log.d("EMAIL - ",tvEmail.getText().toString());
                    cliente.setEmail(tvEmail.getText().toString());
                    Log.d("ENDERECO - ",tvEndereco.getText().toString());
                    cliente.setEndereco(tvEndereco.getText().toString());
                    Log.d("FONE - ",tvTelefone.getText().toString());
                    cliente.setFone(tvTelefone.getText().toString());
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    Toast.makeText(AdicionarClienteActivity.this, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    Log.d("MENSAGEM DE ERRO - ",error.getMessage());
                    Toast.makeText(AdicionarClienteActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
