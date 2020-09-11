package com.vince.gadsleaderboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vince.gadsleaderboard.R;
import com.vince.gadsleaderboard.adapters.SkillIQLeadersRecyclerAdapter;
import com.vince.gadsleaderboard.models.SkilIQLeaders;
import com.vince.gadsleaderboard.network.ApiServiceHandler;
import com.vince.gadsleaderboard.network.ApiServices;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillIQLeadersFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SkillIQLeadersFragment() {
    }

    public static SkillIQLeadersFragment newInstance(String param1, String param2) {
        SkillIQLeadersFragment fragment = new SkillIQLeadersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recyclerview, container, false);

        final ProgressBar progressBar = rootView.findViewById(R.id.progressBar);

        final RecyclerView skillList =  rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        skillList.setLayoutManager(layoutManager);

        ApiServices apiServices = ApiServiceHandler.buildApiService(ApiServices.class);
        Call<ArrayList<SkilIQLeaders>> topSkillsIQRequest = apiServices.getTopSkillIQScores();

        topSkillsIQRequest.enqueue(new Callback<ArrayList<SkilIQLeaders>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<SkilIQLeaders>> call, @NotNull Response<ArrayList<SkilIQLeaders>> response) {
                SkillIQLeadersRecyclerAdapter skillIQLeadersRecyclerAdapter = new SkillIQLeadersRecyclerAdapter(getActivity(), response.body());
                progressBar.setVisibility(View.GONE);
                skillList.setAdapter(skillIQLeadersRecyclerAdapter);
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<SkilIQLeaders>> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), "Failed to retrieve Skill IQ", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}