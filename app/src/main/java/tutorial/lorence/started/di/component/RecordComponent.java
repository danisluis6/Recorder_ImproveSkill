package tutorial.lorence.started.di.component;

import dagger.Subcomponent;
import tutorial.lorence.started.di.module.RecordModule;
import tutorial.lorence.started.di.scope.FragmentScope;
import tutorial.lorence.started.view.fragment.RecordFragment.RecordFragment;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@FragmentScope
@Subcomponent(

        modules = {
                RecordModule.class
        }
)
public interface RecordComponent {
    RecordFragment inject(RecordFragment fragment);
}


