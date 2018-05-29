package tutorial.lorence.started.di.module;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.started.di.scope.ActivityScope;
import tutorial.lorence.started.view.activity.Setting.SettingActivity;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class SettingModule {

    private SettingActivity settingActivity;

    public SettingModule(SettingActivity settingActivity) {
        this.settingActivity = settingActivity;
    }

    @Provides
    @ActivityScope
    SettingActivity provideSettingActivity() {
        return this.settingActivity;
    }
}
