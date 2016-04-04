package duan.example.faltamuito;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import duan.example.faltamuito.adapters.MyPagerAdapter;


public class MaterialsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(duan.example.faltamuito.R.layout.activity_materials);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mToolbar = (Toolbar) findViewById(duan.example.faltamuito.R.id.app_bar);
        setSupportActionBar(mToolbar);
        mTabLayout = (TabLayout) findViewById(duan.example.faltamuito.R.id.tab_layout);
        mPager = (ViewPager) findViewById(duan.example.faltamuito.R.id.pager);
        mPager.setAdapter(mAdapter);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(duan.example.faltamuito.R.menu.menu_materials, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == duan.example.faltamuito.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MyFragment extends Fragment {
        public static final java.lang.String ARG_PAGE = "arg_page";

        public MyFragment() {

        }

        public static MyFragment newInstance(int pageNumber) {
            MyFragment myFragment = new MyFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(ARG_PAGE, pageNumber + 1);
            myFragment.setArguments(arguments);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle arguments = getArguments();
            int pageNumber = arguments.getInt(ARG_PAGE);
            TextView myText = new TextView(getActivity());
            myText.setText(getActivity().getResources().getString(duan.example.faltamuito.R.string.pagina) + " " + pageNumber);
            myText.setGravity(Gravity.CENTER);
            return myText;
        }
    }
}

