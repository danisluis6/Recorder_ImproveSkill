package tutorial.lorence.started.container;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

import javax.inject.Inject;

import tutorial.lorence.started.R;
import tutorial.lorence.started.view.activity.BaseActivity;

public class FragmentUtils {
    private BaseActivity mActivity;
    private Stack<FragmentStack> mCurrentFrgStack;
    private int mContainerId;

    @Inject
    public FragmentUtils() {
    }

    public void attachInfoFragmentManager(BaseActivity activity, Stack<FragmentStack> currentFrgStack, @IdRes int containerId) {
        this.mActivity = activity;
        this.mCurrentFrgStack = currentFrgStack;
        this.mContainerId = containerId;
    }

    public void peekFragment() {
        try {
            FragmentStack fragment = mCurrentFrgStack.peek();
            FragmentManager manager = mActivity.getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            ft.replace(mContainerId, fragment.getFragment(), fragment.getTag());
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException | ArrayIndexOutOfBoundsException e) {
        }
    }

    public void popFragment() {
        mCurrentFrgStack.pop();
        peekFragment();
    }

    public void pushFragmentV4(PushFrgType type, Fragment fragment, String tag) {
        try {
            FragmentManager manager = mActivity.getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            if (type == PushFrgType.REPLACE) {
                ft.replace(mContainerId, fragment, tag);
                ft.disallowAddToBackStack();
                ft.commitAllowingStateLoss();
            } else if (type == PushFrgType.ADD) {
                ft.add(mContainerId, fragment, tag);
                ft.disallowAddToBackStack();
                ft.commit();
            }
            manager.executePendingTransactions();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void pushFragment(PushFrgType type, android.app.Fragment fragment, String tag) {
        try {
            android.app.FragmentManager manager = mActivity.getFragmentManager();
            android.app.FragmentTransaction ft = manager.beginTransaction();
            if (type == PushFrgType.REPLACE) {
                ft.replace(mContainerId, fragment, tag);
                ft.disallowAddToBackStack();
                ft.commitAllowingStateLoss();
            } else if (type == PushFrgType.ADD) {
                ft.add(mContainerId, fragment, tag);
                ft.disallowAddToBackStack();
                ft.commit();
            }
            manager.executePendingTransactions();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public enum PushFrgType {
        REPLACE, ADD
    }
}
