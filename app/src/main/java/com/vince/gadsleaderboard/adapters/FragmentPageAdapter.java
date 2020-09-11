package com.vince.gadsleaderboard.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vince.gadsleaderboard.R;
import com.vince.gadsleaderboard.ui.LearningLeadersFragment;
import com.vince.gadsleaderboard.ui.SkillIQLeadersFragment;

import org.jetbrains.annotations.NotNull;

public class FragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext; // A variable that holds the name of the current tab

    public FragmentPageAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LearningLeadersFragment();
        } else
            return new SkillIQLeadersFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.learning_fragment_text);
        } else
            return mContext.getString(R.string.skill_fragment_text);
    }

}
