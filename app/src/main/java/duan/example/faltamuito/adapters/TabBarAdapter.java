package duan.example.faltamuito.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import duan.example.faltamuito.MaterialsActivity;


public class TabBarAdapter extends FragmentStatePagerAdapter {

    private int count_fragments = 0;

    public TabBarAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setAmountOfFragment(int count_fragments){
        this.count_fragments = count_fragments;
    }

    @Override
    public Fragment getItem(int position) {

        MaterialsActivity.MyFragment myFragment = MaterialsActivity.MyFragment.newInstance(getNameMaterial(position));
        return myFragment;
    }

    @Override
    public int getCount() {
        return count_fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getNameMaterial(position);
    }

    private String getNameMaterial(int position){
        String name_material = "";
        switch (position){
            case 0: name_material = "Matemática";
                break;
            case 1: name_material = "Programação";
                break;
            case 2: name_material = "Hardware";
                break;
        }

        return name_material;
    }
}