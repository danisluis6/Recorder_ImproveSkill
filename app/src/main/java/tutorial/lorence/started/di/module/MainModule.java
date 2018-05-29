package tutorial.lorence.started.di.module;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.started.di.scope.ActivityScope;
import tutorial.lorence.started.other.TitleStringUtils;
import tutorial.lorence.started.view.activity.Main.MainActivity;
import tutorial.lorence.started.view.activity.Main.adapter.PagerAdapterPushed;
import tutorial.lorence.started.view.fragment.FileViewerFragment.FileViewerFragment;
import tutorial.lorence.started.view.fragment.RecordFragment.RecordFragment;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class MainModule {

    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return this.mainActivity;
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() { return this.mainActivity.getSupportFragmentManager(); }

    @Provides
    @ActivityScope
    PagerAdapterPushed providePagerAdapterPushed(FragmentManager fragmentManager, TitleStringUtils titleStringUtils,
                                                 FileViewerFragment fileViewerFragment,
                                                 RecordFragment recordFragment) { return new PagerAdapterPushed(fragmentManager, titleStringUtils, fileViewerFragment, recordFragment); }

}
