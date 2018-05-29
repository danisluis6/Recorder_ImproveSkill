package tutorial.lorence.started.di.component;

import dagger.Subcomponent;
import tutorial.lorence.started.di.module.MainModule;
import tutorial.lorence.started.di.scope.ActivityScope;
import tutorial.lorence.started.view.activity.MainActivity;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@ActivityScope
@Subcomponent(

        modules = {
                MainModule.class
        }
)
public interface MainComponent {

    MainActivity inject(MainActivity activity);
}