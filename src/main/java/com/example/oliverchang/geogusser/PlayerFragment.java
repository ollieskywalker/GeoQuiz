package com.example.oliverchang.geogusser;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver Chang on 4/2/2017.
 */

public class PlayerFragment extends ListFragment{
    private List<Player> players;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = super.onCreateView(inflater,container,savedInstanceState);

        players = new ArrayList<>();
        populateList();

        PlayerAdapter adapter = new PlayerAdapter(getActivity(), players);

        setListAdapter(adapter);

        return rootView;
    }

    private void populateList() {
        players.add(new Player(123,"Joe"));
        players.add(new Player(323,"Max"));
        players.add(new Player(1,"Cole"));
    }
}
