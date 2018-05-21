package com.step.realmdemo1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.step.realmdemo1.R;
import com.step.realmdemo1.realmEntities.Valute;

import java.util.ArrayList;
import java.util.List;

public class ValutesAdapter extends RecyclerView.Adapter<ValutesAdapter.ViewHolder> {
    List<Valute> valutes = new ArrayList<>();

    public void setValutes(List<Valute> valutes) {
        this.valutes = valutes;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ValutesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        return new ViewHolder(inflater.inflate(R.layout.valute_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ValutesAdapter.ViewHolder holder, int position) {
        holder.bind(valutes.get(position));
    }

    @Override
    public int getItemCount() {
        return valutes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        TextView valueTV;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.ccy);
            valueTV = itemView.findViewById(R.id.ccyVal);
        }
        public void bind(Valute v){
            nameTV.setText(v.getCcy());
            valueTV.setText(v.getBuy());
        }
    }
}
