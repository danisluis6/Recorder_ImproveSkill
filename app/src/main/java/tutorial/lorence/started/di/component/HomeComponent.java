package tutorial.lorence.started.di.component;

import dagger.Subcomponent;
import tutorial.lorence.started.di.module.HomeModule;
import tutorial.lorence.started.di.module.RecordModule;
import tutorial.lorence.started.di.scope.ActivityScope;
import tutorial.lorence.started.view.activity.home.HomeActivity;
import tutorial.lorence.started.view.fragment.RecordFragment.RecordFragment;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@ActivityScope
@Subcomponent(

        modules = {
                HomeModule.class
        }
)
public interface HomeComponent {

    HomeActivity inject(HomeActivity activity);
    RecordComponent plus(RecordModule recordModule);
}