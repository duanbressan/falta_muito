package duan.example.faltamuito;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import duan.example.faltamuito.adapters.TabBarAdapter;


public class MaterialsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private TabBarAdapter tabBarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

        tabBarAdapter = new TabBarAdapter(getSupportFragmentManager());
        tabBarAdapter.setAmountOfFragment(3);

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(tabBarAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setTabsFromPagerAdapter(tabBarAdapter);

        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_materials, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MyFragment extends Fragment {

        private View fragment;
        private static final String KEY = "material";

        public MyFragment() {

        }

        public static MyFragment newInstance(String material_name) {
            MyFragment myFragment = new MyFragment();

            Bundle arguments = new Bundle();
            arguments.putString(KEY, material_name);
            myFragment.setArguments(arguments);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            Bundle arguments = getArguments();
            String name_material = arguments.getString(KEY);

            fragment = inflater.inflate(R.layout.fragment_materials, container, false);

            TextView textView = (TextView) fragment.findViewById(R.id.txt);
            textView.setText(name_material);

            return fragment;
        }
    }
}

