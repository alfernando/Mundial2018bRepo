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
public class LockDialogFragment extends DialogFragment {

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(String msj);
        public void onDialogNegativeClick();
    }

    public NoticeDialogListener getmListener() {
        return mListener;
    }

    public void setmListener(NoticeDialogListener mListener) {
        this.mListener = mListener;
    }

    NoticeDialogListener mListener;

    public static LockDialogFragment newInstance(Bundle arguments){
        LockDialogFragment f = new LockDialogFragment();
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
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setTransformationMethod(new PasswordTransformationMethod());
        alert.setView(input);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Grab the EditText's input
                String inputName = input.getText().toString();
                mListener.onDialogPositiveClick(inputName);
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
