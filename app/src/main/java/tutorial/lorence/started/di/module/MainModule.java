package tutorial.lorence.started.di.module;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.started.di.scope.ActivityScope;
import tutorial.lorence.started.view.activity.MainActivity;

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

}
