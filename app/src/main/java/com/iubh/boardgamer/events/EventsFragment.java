package com.iubh.boardgamer.events;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iubh.boardgamer.Adapters.TeilnehmerAdapter;
import com.iubh.boardgamer.Game.Spieltermin;
import com.iubh.boardgamer.R;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class EventsFragment extends Fragment implements TeilnehmerAdapter.OnItemListener {

    private ArrayList<Teilnehmer> list = new ArrayList<>();
    private TeilnehmerAdapter teilnehmerAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton addEvent;
    private Map<Spieltermin, ArrayList<Teilnehmer>> map = new TreeMap<Spieltermin, ArrayList<Teilnehmer>>();
    private Spieltermin TreeMapKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView= v.findViewById(R.id.rv_eventlist);
        getTeilnehmer();
        addEvent = v.findViewById(R.id.fbtnAddEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddEvent.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public  void getTeilnehmer() {
        Map<String, String> data = new HashMap<>();
        data.put("include", "spielterminId,userId");
            Response response = null;
            if(response.isSuccessful()) {
                            ResponseBody resp = response.body();
                            Teilnehmer[] teilnehmer = new Teilnehmer[0];
                for(int i=0; i < teilnehmer.length; i++) {

                                if(map.containsKey(TreeMapKey)) {
                                    map.get(TreeMapKey).add(teilnehmer[i]);
                                } else {
                                    ArrayList<Teilnehmer> tnmr = new ArrayList<>();
                                    tnmr.add(teilnehmer[i]);
                                    map.put(TreeMapKey,tnmr);
                                }
                            }
                            viewData(map);
                        } else {
                            Toast.makeText(getActivity(), "Error: something went wrong", Toast.LENGTH_SHORT ).show();
                        }
                    }

    private void viewData(Map<Spieltermin, ArrayList<Teilnehmer>> map) {
    }

    @Override
                    public void onFailure() {
                        Toast.makeText(getActivity(), "Error" , Toast.LENGTH_SHORT).show();
                    }

    @Override
    public void onItemClick(int position) {

    }
}
