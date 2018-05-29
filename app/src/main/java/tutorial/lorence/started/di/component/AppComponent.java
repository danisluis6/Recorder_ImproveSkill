package tutorial.lorence.started.di.component;

import javax.inject.Singleton;

import dagger.Component;
import tutorial.lorence.started.di.module.AppModule;
import tutorial.lorence.started.di.module.MainModule;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {
        MainComponent plus(MainModule module);
}