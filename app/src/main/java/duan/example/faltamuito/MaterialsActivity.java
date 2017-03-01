package duan.example.faltamuito;

import android.content.Intent;
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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import duan.example.faltamuito.DAOs.DAOCategory;
import duan.example.faltamuito.DAOs.DAOSubject;
import duan.example.faltamuito.adapters.SubjectsAdapter;
import duan.example.faltamuito.adapters.TabBarAdapter;
import duan.example.faltamuito.models.Category;
import duan.example.faltamuito.models.Subject;

public class MaterialsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private TabBarAdapter tabBarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

        DAOCategory daoCategory = new DAOCategory(this);
        List<Category> categoryList = daoCategory.findAllCategory();

        tabBarAdapter = new TabBarAdapter(getSupportFragmentManager());
        tabBarAdapter.setAmountOfFragment(categoryList.size());
        tabBarAdapter.setListCategory(categoryList);

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(tabBarAdapter);

        Intent intent = getIntent();
        int page = intent.getIntExtra("page", 0);
        mPager.setCurrentItem(page);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setTabsFromPagerAdapter(tabBarAdapter);

        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        private static final String KEY = "subject";
        private ListView listViewSubject;

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

            this.fragment = inflater.inflate(R.layout.fragment_materials, container, false);
            this.listViewSubject = (ListView) fragment.findViewById(R.id.listViewSubject);

            DAOSubject daoSubject = new DAOSubject(getActivity());
            List<Subject> subjectList = daoSubject.findAllSubjects();
            List<Subject> subjectListAdapter = new ArrayList<>();
            for(int i = 0; i < subjectList.size(); i++){
                if(subjectList.get(i).getCategory().getName().equals(name_material)){
                    subjectListAdapter.add(subjectList.get(i));
                }
            }

            SubjectsAdapter subjectsAdapter = new SubjectsAdapter(getActivity(),subjectListAdapter);
            listViewSubject.setAdapter(subjectsAdapter);

            return fragment;
        }
    }
}