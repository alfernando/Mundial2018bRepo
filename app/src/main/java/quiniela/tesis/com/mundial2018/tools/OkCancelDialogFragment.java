package quiniela.tesis.com.mundial2018.tools;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

import quiniela.tesis.com.mundial2018.R;


/**
 * Created by Javier on 17/08/2015.
 */
public class OkCancelDialogFragment extends DialogFragment {

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick();
        public void onDialogNegativeClick();
    }

    public NoticeDialogListener getmListener() {
        return mListener;
    }

    public void setmListener(NoticeDialogListener mListener) {
        this.mListener = mListener;
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    public static OkCancelDialogFragment newInstance(Bundle arguments){
        OkCancelDialogFragment f = new OkCancelDialogFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(getString(R.string.app_name));
        String msj = getArguments().getString("msj","...");
        alert.setMessage(msj);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mListener.onDialogPositiveClick();
            }
        });
        // Make a "Cancel" button that simply dismisses the alert
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mListener.onDialogNegativeClick();
            }
        });
        return alert.create();

     }

}
