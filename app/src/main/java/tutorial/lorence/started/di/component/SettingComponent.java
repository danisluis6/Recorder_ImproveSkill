package tutorial.lorence.started.di.component;

import dagger.Subcomponent;
import tutorial.lorence.started.di.module.SettingModule;
import tutorial.lorence.started.di.scope.ActivityScope;
import tutorial.lorence.started.view.activity.Setting.SettingActivity;
import tutorial.lorence.started.view.activity.Setting.SettingFragment;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@ActivityScope
@Subcomponent(

        modules = {
                SettingModule.class
        }
)
public interface SettingComponent {

    SettingActivity inject(SettingActivity activity);
    SettingFragment inject(SettingFragment fragment);
}