package tutorial.lorence.started.view.activity.home.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import javax.inject.Inject;

import tutorial.lorence.started.other.TitleStringUtils;
import tutorial.lorence.started.view.fragment.FileViewerFragment.FileViewerFragment;
import tutorial.lorence.started.view.fragment.RecordFragment.RecordFragment;

public class PagerAdapterPushed extends FragmentPagerAdapter {

    private TitleStringUtils titles;

    @Inject
    public PagerAdapterPushed(FragmentManager fm, TitleStringUtils titleStringUtils) {
        super(fm);
        titles = titleStringUtils;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:{
                return RecordFragment.newInstance(position);
            }
            case 1:{
                return FileViewerFragment.newInstance(position);
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.getGroupTitleFragment().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.getGroupTitleFragment()[position];
    }
}
