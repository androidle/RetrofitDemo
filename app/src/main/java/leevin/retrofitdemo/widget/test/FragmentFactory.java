package leevin.retrofitdemo.widget.test;


import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leevin on 2016/11/8.
 */
public class FragmentFactory {

    public static final Map<Integer, Fragment> fragments = new HashMap<>();

    static {
        for (int i = 0; i < 4; i++) {
            fragments.put(i, MyFragment.newInstance(i));
        }
    }

    public static Fragment getFragmentByIndex(int index) {
        if (!fragments.containsKey(index)) {
            throw new IllegalArgumentException("not contains the key :" + index);
        }
        return fragments.get(index);
    }
}
