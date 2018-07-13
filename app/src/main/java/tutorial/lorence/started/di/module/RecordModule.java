package tutorial.lorence.started.di.module;

import android.os.Environment;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.started.di.scope.FragmentScope;
import tutorial.lorence.started.view.activity.home.HomeActivity;
import tutorial.lorence.started.view.fragment.RecordFragment.RecordFragment;
import tutorial.lorence.started.view.fragment.RecordFragment.RecordView;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class RecordModule {

    private RecordFragment mFragment;
    private HomeActivity mActivity;
    private RecordView mRecordView;

    public RecordModule(HomeActivity homeActivity, RecordFragment recordFragment, RecordView recordView) {
        mFragment = recordFragment;
        mActivity = homeActivity;
        mRecordView = recordView;
    }

    @Provides
    @FragmentScope
    File provideFile() {
        return new File(Environment.getExternalStorageDirectory() + "/resource_audio");
    }
}
