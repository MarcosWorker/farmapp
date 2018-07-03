package com.example.marcosmarques.farmapp.flow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.marcosmarques.farmapp.R;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_menu_main_carrinho:
                Toast.makeText(this, "Tela Carrinho em construção", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_menu_main_clientes:
                intent = new Intent(MainActivity.this ,ClientesActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_menu_main_historico:
                Toast.makeText(this, "Tela Historico em construção", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_menu_main_produtos:
                Toast.makeText(this, "Tela Produtos em construção", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_menu_main_sair:
                Toast.makeText(this, "Tela Sair em construção", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
