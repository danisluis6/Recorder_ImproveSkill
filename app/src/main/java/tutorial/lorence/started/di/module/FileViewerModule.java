package tutorial.lorence.started.di.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.started.di.scope.FragmentScope;
import tutorial.lorence.started.view.activity.home.HomeActivity;
import tutorial.lorence.started.view.fragment.FileViewerFragment.FileViewerFragment;
import tutorial.lorence.started.view.fragment.FileViewerFragment.FileViewerView;
import tutorial.lorence.started.view.fragment.FileViewerFragment.adapter.FileViewerAdapter;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class FileViewerModule {

    private FileViewerFragment mFragment;
    private HomeActivity mActivity;
    private FileViewerView mView;

    public FileViewerModule(HomeActivity homeActivity, FileViewerFragment recordFragment, FileViewerView view) {
        mFragment = recordFragment;
        mActivity = homeActivity;
        mView = view;
    }

    @Provides
    @FragmentScope
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(mActivity);
    }

    @Provides
    @FragmentScope
    FileViewerAdapter provideFileViewerAdapter(Context context, LinearLayoutManager linearLayoutManager) {
        return new FileViewerAdapter(context, linearLayoutManager);
    }
}
