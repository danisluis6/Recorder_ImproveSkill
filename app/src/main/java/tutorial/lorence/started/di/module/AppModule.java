package tutorial.lorence.started.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.started.app.Application;
import tutorial.lorence.started.other.container.FragmentUtils;
import tutorial.lorence.started.other.TitleStringUtils;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class AppModule {

    private Application mApplication;
    private Context mContext;

    public AppModule(Application application, Context context) {
        this.mApplication = application;
        this.mContext = context;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    TitleStringUtils provideTitleStringUtils(Context context) { return new TitleStringUtils(context); }

    @Provides
    @Singleton
    FragmentUtils provideFragmentUtils(Context context) { return new FragmentUtils(); }
}
