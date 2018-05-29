package tutorial.lorence.started.app;

import android.content.Context;

import tutorial.lorence.started.di.component.AppComponent;
import tutorial.lorence.started.di.component.DaggerAppComponent;
import tutorial.lorence.started.di.module.AppModule;
import tutorial.lorence.started.di.module.FragmentModule;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

public class Application extends android.app.Application {

    private AppComponent mApplicationComponent;
    private Context mContext;
    private static Application sInstance;

    public static synchronized Application getInstance() {
        if (sInstance == null) {
            sInstance = new Application();
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        sInstance = this;
        initAppComponent();
    }

    private void initAppComponent() {
        mApplicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this,mContext))
                .fragmentModule(new FragmentModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mApplicationComponent;
    }

}

