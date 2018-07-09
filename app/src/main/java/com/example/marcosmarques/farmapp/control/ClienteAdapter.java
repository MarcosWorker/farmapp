package com.example.marcosmarques.farmapp.control;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcosmarques.farmapp.R;
import com.example.marcosmarques.farmapp.flow.AdicionarClienteActivity;
import com.example.marcosmarques.farmapp.model.Cliente;

import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.exceptions.RealmError;

public class ClienteAdapter extends RealmRecyclerViewAdapter<Cliente, ClienteAdapter.ViewHolder> {

    private Realm realm;

    public ClienteAdapter(RealmResults<Cliente> data) {
        super(data, true);
        // Only set this if the model class has a primary key that is also a integer or long.
        // In that case, {@code getItemId(int)} must also be overridden to return the key.
        // See https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html#hasStableIds()
        // See https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html#getItemId(int)
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_clientes, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Cliente cliente = getItem(position);
        viewHolder.nome.setText(cliente.getNome());
        viewHolder.telefone.setText(cliente.getFone());

        viewHolder.imgBtSetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("O deseja fazer com esse cliente?")
                        .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(view.getContext(), AdicionarClienteActivity.class);
                                intent.putExtra("tela", "editar");
                                intent.putExtra("id", cliente.getId());
                                view.getContext().startActivity(intent);
                            }
                        })
                        .setNegativeButton("Deletar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                realm = Realm.getDefaultInstance();
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        try {
                                            cliente.deleteFromRealm();
                                        } catch (RealmError ex) {
                                            Toast.makeText(view.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                                        } finally {
                                            Toast.makeText(view.getContext(), "Cliente excluído com sucesso", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }
                        });
                builder.show();
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private TextView telefone;
        private ImageButton imgBtSetings;


        public ViewHolder(View v) {
            super(v);

            nome = v.findViewById(R.id.nome_adapter_list_cliente);
            telefone = v.findViewById(R.id.telefone_adapter_list_cliente);
            imgBtSetings = v.findViewById(R.id.setings_adapter_list_cliente);
        }

    }
}
