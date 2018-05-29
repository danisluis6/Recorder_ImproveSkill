package tutorial.lorence.started.other;


import android.content.Context;

import javax.inject.Inject;

import tutorial.lorence.started.R;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

public class TitleStringUtils {

    private Context mContext;

    @Inject
    public TitleStringUtils(Context context) {
        mContext = context;
    }

    public String[] getGroupTitleFragment() {
        return new String[]{mContext.getResources().getString(R.string.tab_title_record), mContext.getString(R.string.tab_title_saved_recordings)};
    }


}
