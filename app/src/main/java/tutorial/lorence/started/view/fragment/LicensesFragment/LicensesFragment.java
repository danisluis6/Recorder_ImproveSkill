package tutorial.lorence.started.view.fragment.LicensesFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import tutorial.lorence.started.R;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class LicensesFragment extends DialogFragment {

    public LicensesFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater dialogInflater = getActivity().getLayoutInflater();
        View openSourceLicensesView = dialogInflater.inflate(R.layout.fragment_licenses, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(openSourceLicensesView)
                .setTitle((getString(R.string.dialog_title_licenses)))
                .setNeutralButton(android.R.string.ok, null);

        return dialogBuilder.create();
    }

}
