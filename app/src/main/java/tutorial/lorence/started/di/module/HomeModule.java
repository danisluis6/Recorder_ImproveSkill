package tutorial.lorence.started.di.module;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.started.di.scope.ActivityScope;
import tutorial.lorence.started.other.TitleStringUtils;
import tutorial.lorence.started.view.activity.home.HomeActivity;
import tutorial.lorence.started.view.activity.home.adapter.PagerAdapterPushed;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class HomeModule {

    private HomeActivity homeActivity;

    public HomeModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @ActivityScope
    HomeActivity provideMainActivity() {
        return this.homeActivity;
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() { return this.homeActivity.getSupportFragmentManager(); }

    @Provides
    @ActivityScope
    PagerAdapterPushed providePagerAdapterPushed(FragmentManager fragmentManager, TitleStringUtils titleStringUtils) { return new PagerAdapterPushed(fragmentManager, titleStringUtils); }

}
