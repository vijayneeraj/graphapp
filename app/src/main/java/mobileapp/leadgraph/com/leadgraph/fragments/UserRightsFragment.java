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
 * @author neeraj on 7/12/18.
 */
public class UserRightsFragment extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      if (view==null){
          view=inflater.inflate(R.layout.fragment_user_right,container,false);
      }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setSelected(false,false,false,false,false,false,false,false,false,true);
        ((MainActivity)getActivity()).setBottomNavigation(false,false,false);
        ((MainActivity)getActivity()).setTitle("User Rights");
    }
}
