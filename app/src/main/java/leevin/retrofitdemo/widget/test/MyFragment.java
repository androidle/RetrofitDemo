package leevin.retrofitdemo.widget.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by leevin on 2016/11/8.
 */
public class MyFragment extends Fragment{

    private static final String KEY = "key";
    private int mIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(KEY)) {
            mIndex = (int) bundle.get(KEY);
        }
    }

    public static Fragment newInstance(int i) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, i);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(container.getContext());
        textView.setText("Fragment :" + mIndex);
        textView.setTextSize(40);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
