package com.iubh.boardgamer.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iubh.boardgamer.Auth.User;
import com.iubh.boardgamer.R;
import com.iubh.boardgamer.events.Teilnehmer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class TeilnehmerAdapter extends RecyclerView.Adapter<TeilnehmerAdapter.SpielterminViewHolder>{

    private Map<Date,ArrayList<Teilnehmer>> map;
    private DateFormat dateFormat = new SimpleDateFormat("dd  MMMM yyyy");
    private OnItemListener mOnItemListener;
    private User id;

    public TeilnehmerAdapter(Map<Date,ArrayList<Teilnehmer>> map, OnItemListener onItemListener) {
        this.map = map;
        this.mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public SpielterminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(com.iubh.boardgamer.R.layout.fragment_events_row,parent, false );

        return new SpielterminViewHolder(view, mOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SpielterminViewHolder holder, int position) {

        //Damit die Liste über ein Index gesucht werden kann, muss die Objektreferenz zuerst in ein ArrayList kopiert werden.
        ArrayList<Map.Entry<Date,ArrayList<Teilnehmer>>> indexedList = new ArrayList<>(map.entrySet());
        Map.Entry<Date,ArrayList<Teilnehmer>> entry = indexedList.get(position);

        String  strDate = dateFormat.format(entry.getKey());
        holder.eventSpielterminTextView.setText(strDate);
        holder.eventFoodSupplierTextView.setText("Participants: ");

        ArrayList<Teilnehmer> teilnehmer = entry.getValue();
        for(int i=0; i < teilnehmer.size(); i++) {
            id = teilnehmer.get(i).getUserId();
            holder.eventFoodSupplierTextView.append((CharSequence) id);
            if(i < teilnehmer.size()-1) {
                holder.eventFoodSupplierTextView.append(", "); //Nach dem letzten Teilnehmer sollte kein Komma gesetzt werden.
            }

        }

        if(teilnehmer.get(0).getSpielterminId() != null) {
            holder.eventFoodSupplierTextView.append(System.getProperty("line.separator"));
            holder.eventFoodSupplierTextView.append("Game: " + teilnehmer.get(0).getSpielterminId());
        }

    }

    @Override
    public int getItemCount() {
        return map.size();
    }


    class SpielterminViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView eventFoodSupplierTextView;
        TextView eventSpielterminTextView;
        OnItemListener onItemListener;

        public SpielterminViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            eventSpielterminTextView=itemView.findViewById(R.id.tv_eventdate);
            eventFoodSupplierTextView=itemView.findViewById(R.id.tv_foodsupplier);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());

        }
    }

    public interface OnItemListener {
        void onFailure();

        void onItemClick(int position);
    }

}