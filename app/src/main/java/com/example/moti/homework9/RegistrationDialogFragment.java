package com.example.moti.homework9;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by moti on 3/13/16.
 */
public class RegistrationDialogFragment extends DialogFragment {
    public static final String MESSAGE_ID = "message";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle message = getArguments();

        if (message != null)
            return new AlertDialog
                    .Builder(getActivity())
                    .setTitle(R.string.alert_registration_success_title)
                    .setMessage(message.getString(MESSAGE_ID))
                    .setPositiveButton(R.string.alert_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
        else return null;
    }
}
