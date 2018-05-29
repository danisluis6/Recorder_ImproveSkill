package tutorial.lorence.started.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.started.view.activity.Setting.SettingFragment;
import tutorial.lorence.started.view.fragment.FileViewerFragment.FileViewerFragment;
import tutorial.lorence.started.view.fragment.RecordFragment.RecordFragment;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class FragmentModule {

    public FragmentModule() {
    }

    @Provides
    @Singleton
    FileViewerFragment provideFileViewerFragment() {
        return new FileViewerFragment();
    }

    @Provides
    @Singleton
    RecordFragment provideRecordFragment() {
        return new RecordFragment();
    }

    @Provides
    @Singleton
    SettingFragment provideSettingFragment() {
        return new SettingFragment();
    }

}
