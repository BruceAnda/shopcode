package cn.zhaoliang5156.day03mvp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zhaoliang5156.day03mvp.R;
import cn.zhaoliang5156.day03mvp.view.UserView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements UserView {

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onSuccess(String result) {

    }
}
