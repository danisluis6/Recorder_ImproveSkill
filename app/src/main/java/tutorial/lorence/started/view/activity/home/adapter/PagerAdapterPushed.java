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
    private FileViewerFragment mFileViewerFragment;
    private RecordFragment mRecordFragment;

    @Inject
    public PagerAdapterPushed(FragmentManager fm, TitleStringUtils titleStringUtils, FileViewerFragment fileViewerFragment, RecordFragment recordFragment) {
        super(fm);
        titles = titleStringUtils;
        mFileViewerFragment = fileViewerFragment;
        mRecordFragment = recordFragment;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:{
                return mRecordFragment;
            }
            case 1:{
                return mFileViewerFragment.newInstance(mFileViewerFragment, position);
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
