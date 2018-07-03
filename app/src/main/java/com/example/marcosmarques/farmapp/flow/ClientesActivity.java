package com.example.marcosmarques.farmapp.flow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.marcosmarques.farmapp.R;
import com.example.marcosmarques.farmapp.control.ClienteAdapter;
import com.example.marcosmarques.farmapp.model.Cliente;

import io.realm.Realm;

public class ClientesActivity extends AppCompatActivity {

    private Intent intent;
    private Realm realm;
    private RecyclerView recyclerView;
    private ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        setTitle("Clientes");
        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.list_clientes);
        montarLista();
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_menu_cliente_adicionar:
                intent = new Intent(ClientesActivity.this , AdicionarClienteActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_menu_cliente_apagar_todos:
                Toast.makeText(this, "Apagar todos em construção", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_menu_cliente_inicio:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void montarLista() {
        adapter = new ClienteAdapter(realm.where(Cliente.class).findAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
        realm.close();
    }

    private class TouchHelperCallback extends ItemTouchHelper.SimpleCallback {

        TouchHelperCallback() {
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    }
}
