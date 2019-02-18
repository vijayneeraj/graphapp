package mobileapp.leadgraph.com.leadgraph.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobileapp.leadgraph.com.leadgraph.MainActivity;
import mobileapp.leadgraph.com.leadgraph.R;


/**
 * home screen fragment added on container from main activity
 * home screen form for submit for booking a truck in celebi
 *
 * @author neeraj on 22/11/18.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            initViews();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setSelected(false,false,false,false,false,false,false,false,false,false);
        ((MainActivity)getActivity()).setBottomNavigation(false,false,false);

        ((MainActivity) getActivity()).setTitle("DashBoard");
    }

    private void initViews() {

    }



    @Override
    public void onClick(View view2) {
        switch (view2.getId()) {

        }
    }



}
