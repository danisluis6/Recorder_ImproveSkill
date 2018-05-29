package tutorial.lorence.started.di.component;

import javax.inject.Singleton;

import dagger.Component;
import tutorial.lorence.started.di.module.AppModule;
import tutorial.lorence.started.di.module.FragmentModule;
import tutorial.lorence.started.di.module.MainModule;
import tutorial.lorence.started.di.module.SettingModule;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                FragmentModule.class
        }
)
public interface AppComponent {
        MainComponent plus(MainModule module1);
        SettingComponent plus(SettingModule module1);
}