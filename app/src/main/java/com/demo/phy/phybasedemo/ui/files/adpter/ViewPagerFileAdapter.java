package com.demo.phy.phybasedemo.ui.files.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.outim.mechat.ui.fragment.FileTypeFragment;

import java.util.List;

/**
 * Created by phy on 2017/7/10.
 */

public class ViewPagerFileAdapter extends FragmentStatePagerAdapter {
    private List<FileTypeFragment> fragments;
    private List<String> types;
    public ViewPagerFileAdapter(FragmentManager fm, List<FileTypeFragment> fragments, List<String> types) {
        super(fm);
        this.fragments = fragments;
        this.types = types;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //获取头
    @Override
    public CharSequence getPageTitle(int position) {
        if(null != types && types.size() > 0){
            return types.get(position);
        }
        else return null;
    }
}
