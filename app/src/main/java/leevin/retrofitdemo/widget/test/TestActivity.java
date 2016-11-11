package leevin.retrofitdemo.widget.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import leevin.retrofitdemo.R;
import leevin.retrofitdemo.widget.mytablayout.SimpleTabLayout;

public class TestActivity extends AppCompatActivity implements SimpleTabLayout.OnTabSelectedListener, View.OnLongClickListener {
    String[] titles = {


        "中国银行",
            "已产品",
        "历史交易中国人",
            "历史交易"
//            "委托交易",
//            "历史交易",
//            "历史交易",
//            "历史交易",
//            "历史交易"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        SimpleTabLayout tabLayout = (SimpleTabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyPagerAdapter(this));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setOnLongClickListener(this);
    }

    @Override
    public void onTabSelected(SimpleTabLayout.Tab tab) {
//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onTabUnselected(SimpleTabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(SimpleTabLayout.Tab tab) {

    }

    @Override
    public boolean onLongClick(View v) {
//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivity(intent);
        return true;
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(AppCompatActivity activity) {
            super(activity.getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.getFragmentByIndex(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    public static Intent getTestActivityIntent(Context context) {
        return new Intent(context, TestActivity.class);
    }
}
