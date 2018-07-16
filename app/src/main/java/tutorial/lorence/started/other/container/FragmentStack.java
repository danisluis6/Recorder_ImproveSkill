package tutorial.lorence.started.other.container;

import android.support.v4.app.Fragment;

public class FragmentStack {
    private final Fragment mFragment;
    private final String mTag;

    FragmentStack(Fragment fragment, String key) {
        this.mFragment = fragment;
        mTag = key;
    }

    Fragment getFragment() {
        return mFragment;
    }

    String getTag() {
        return mTag;
    }
}
