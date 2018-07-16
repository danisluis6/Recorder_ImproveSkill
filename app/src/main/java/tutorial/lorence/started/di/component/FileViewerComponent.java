package tutorial.lorence.started.di.component;

import dagger.Subcomponent;
import tutorial.lorence.started.di.module.FileViewerModule;
import tutorial.lorence.started.di.scope.FragmentScope;
import tutorial.lorence.started.view.fragment.FileViewerFragment.FileViewerFragment;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@FragmentScope
@Subcomponent(

        modules = {
                FileViewerModule.class
        }
)
public interface FileViewerComponent {
    FileViewerFragment inject(FileViewerFragment fragment);
}


