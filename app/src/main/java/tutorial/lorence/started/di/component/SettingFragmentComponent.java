package tutorial.lorence.started.di.component;

import dagger.Subcomponent;
import tutorial.lorence.started.di.module.SettingFragmentModule;
import tutorial.lorence.started.di.scope.FragmentScope;
import tutorial.lorence.started.view.fragment.SettingFragment.SettingFragment;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@FragmentScope
@Subcomponent(
        modules = SettingFragmentModule.class
)

public interface SettingFragmentComponent {

    SettingFragment inject(SettingFragment fragment);

}