package duan.example.faltamuito.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import duan.example.faltamuito.MaterialsActivity;
import duan.example.faltamuito.models.Category;


public class TabBarAdapter extends FragmentStatePagerAdapter {

    private int count_fragments = 0;
    private List<Category> categoryList;

    public TabBarAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setAmountOfFragment(int count_fragments){
        this.count_fragments = count_fragments;
    }

    public void setListCategory(List<Category> categoryList){
        this.categoryList = categoryList;
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
        return categoryList.get(position).getName();
    }
}