package cn.NTBrave.poetry.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.NTBrave.poetry.R;

//
public class ScreenFootFragment extends Fragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_foot_fragment, container, false);
        textView = view.findViewById(R.id.tv_screen_foot);
        textView.setText("123");
        return view;
    }
}
