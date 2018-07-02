package com.addit.drjainsoils.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v4.view.ViewPager;

import com.addit.drjainsoils.R;
import com.addit.drjainsoils.adapters.MyListViewAdapter;
import com.addit.drjainsoils.adapters.pairs.SingleTextPair;
import com.addit.drjainsoils.fragment.FragmentConditions;
import com.addit.drjainsoils.fragment.FragmentHow;
import com.addit.drjainsoils.fragment.FragmentOils;
import com.addit.drjainsoils.utils.XAppCompatActivity;

public class MainActivity extends XAppCompatActivity {

    private VH vh;
    public MainActivity() {
        super(R.layout.activity_main, 0);
    }

    private class VH {

        private MyListViewAdapter<SingleTextPair, SingleTextPair> list;

        private Toolbar toolbar;
        private boolean setfragment = false;
        private int pageno=1;
        private SectionsPagerAdapter mSectionsPagerAdapter;
        private int tabId = -1;

        private ViewPager mViewPager;

        private FragmentHow fragmentHow = null;
        private FragmentConditions fragmentConditions = null;
        private FragmentOils fragmentOils = null;

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        vh = null;
    }
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Contitions").setIcon(R.drawable.condition));
        tabLayout.addTab(tabLayout.newTab().setText("Essentials Oils").setIcon(R.drawable.oil));
        tabLayout.addTab(tabLayout.newTab().setText("How & Why").setIcon(R.drawable.how));
        //  tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
*/
    @Override
    protected boolean getXmlControls() {
        vh = new VH();
        vh.toolbar = (Toolbar) findViewById(R.id.toolbar);
        vh.mViewPager = (ViewPager) findViewById(R.id.container);
        return true;
    }

    @Override
    protected void initXmlControls() throws Exception {
        vh.mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        Toolbar tool=(Toolbar)findViewById(R.id.toolbar);
        tool.setTitle("Dr.Jains Oils");
        vh.mViewPager.setAdapter(vh.mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vh.mViewPager);
        // set search on keyboard pressing enter
        if (!vh.setfragment)
            vh.mViewPager.setCurrentItem(1);
    }


    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Bundle data = new Bundle();
            switch (position) {
                case 0:vh.pageno=0;
                    // vh.fab_filter.setVisibility(View.VISIBLE);
                    if (vh.fragmentConditions == null)
                        vh.fragmentConditions = FragmentConditions.newInstance();

                    return vh.fragmentConditions;
                case 1:vh.pageno=1;
                    //vh.fab_filter.setVisibility(View.VISIBLE);
                    if (vh.fragmentOils == null)
                        vh.fragmentOils = FragmentOils.newInstance();
                    return vh.fragmentOils;
                case 2:vh.pageno=2;
                    //   vh.fab_filter.setVisibility(View.GONE);
                    if (vh.fragmentHow == null)
                        vh.fragmentHow = FragmentHow.newInstance();
                    return vh.fragmentHow;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Conditions";
                case 1:
                    return "Oils";
                case 2:
                    return "How & Why";
            }
            return null;
        }
    }


}
