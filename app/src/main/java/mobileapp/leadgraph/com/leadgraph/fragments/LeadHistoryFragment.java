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
 * @author neeraj on 5/12/18.
 */
public class LeadHistoryFragment extends Fragment implements View.OnClickListener {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.fragment_history_lead,container,false);
        }
        initViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setSelected(false, false, false, false, false, false, false, true, false, false);
        ((MainActivity)getActivity()).setBottomNavigation(false,false,true);
        ((MainActivity)getActivity()).setTitle("Lead Assign");
    }

    private void initViews(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){


        }
    }


}
