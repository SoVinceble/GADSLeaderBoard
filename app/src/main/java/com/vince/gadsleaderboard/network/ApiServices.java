package com.vince.gadsleaderboard.network;

import com.vince.gadsleaderboard.models.LearningLeaders;
import com.vince.gadsleaderboard.models.SkilIQLeaders;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("api/hours")
    Call<ArrayList<LearningLeaders>> getTopLearners();

    @GET("api/skilliq")
    Call<ArrayList<SkilIQLeaders>> getTopSkillIQScores();

}
