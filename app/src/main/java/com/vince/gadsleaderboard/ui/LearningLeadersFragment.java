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
import com.vince.gadsleaderboard.adapters.LearningLeadersRecyclerAdapter;
import com.vince.gadsleaderboard.models.LearningLeaders;
import com.vince.gadsleaderboard.network.ApiServiceHandler;
import com.vince.gadsleaderboard.network.ApiServices;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningLeadersFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public LearningLeadersFragment() {

    }


    public static LearningLeadersFragment newInstance(String param1, String param2) {
        LearningLeadersFragment fragment = new LearningLeadersFragment();
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

        final RecyclerView learnersList = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        final LinearLayoutManager learningLayoutManager = new LinearLayoutManager(getContext());
        learnersList.setLayoutManager(learningLayoutManager);


        ApiServices apiServices = ApiServiceHandler.buildApiService(ApiServices.class);
        Call<ArrayList<LearningLeaders>> topLearnersRequest = apiServices.getTopLearners();

        topLearnersRequest.enqueue(new Callback<ArrayList<LearningLeaders>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<LearningLeaders>> call, @NotNull Response<ArrayList<LearningLeaders>> response) {
                LearningLeadersRecyclerAdapter learningLeadersRecyclerAdapter =
                        new LearningLeadersRecyclerAdapter(getContext(), response.body());
                progressBar.setVisibility(View.GONE);
                learnersList.setAdapter(learningLeadersRecyclerAdapter);
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<LearningLeaders>> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), "Failed to retrieve Learners", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;


    }

}