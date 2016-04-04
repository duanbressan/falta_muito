package duan.example.faltamuito.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import duan.example.faltamuito.MaterialsActivity;


public class MyPagerAdapter extends FragmentStatePagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        MaterialsActivity.MyFragment myFragment = MaterialsActivity.MyFragment.newInstance(position);
        return myFragment;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab " + (position + 1);
    }
}