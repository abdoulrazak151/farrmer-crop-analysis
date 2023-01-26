package com.soft.conseilagricole.outil;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.soft.conseilagricole.R;
import com.soft.conseilagricole.outil.identifierplante.PlanteIdentifierActivity;


public class FragmentOutil extends Fragment {
    MaterialCardView cardIdentifierPlante;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_outil, container, false);
        cardIdentifierPlante=view.findViewById(R.id.card_identifier_plante);
        cardIdentifierPlante.setOnClickListener(click->{
            startActivity(new Intent(getActivity(), PlanteIdentifierActivity.class));
        });
        return view;
    }
}