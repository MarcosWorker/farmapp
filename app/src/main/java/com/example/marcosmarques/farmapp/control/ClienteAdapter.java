package com.example.marcosmarques.farmapp.control;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marcosmarques.farmapp.R;
import com.example.marcosmarques.farmapp.model.Cliente;

import io.realm.OrderedRealmCollection;
import io.realm.RealmList;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class ClienteAdapter extends RealmRecyclerViewAdapter<Cliente, ClienteAdapter.ViewHolder> {

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
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private TextView telefone;


        public ViewHolder(View v) {
            super(v);

            nome = (TextView) v.findViewById(R.id.nome_adapter_list_cliente);
            telefone = (TextView) v.findViewById(R.id.telefone_adapter_list_cliente);
        }

    }
}
