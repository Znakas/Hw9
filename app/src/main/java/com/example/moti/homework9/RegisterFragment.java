package com.example.moti.homework9;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by moti on 3/13/16.
 */
public class RegisterFragment extends Fragment {

    private EditText mTextLogin;
    private EditText mTextPassword;
    private EditText mTextFirstName;
    private EditText mTextLastName;
    private RadioGroup mRadioGender;
    private Button mBtnRegister;

    private RegisterEventHandler mRegisterEventHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_register, container, false);

        mTextLogin = (EditText) view.findViewById(R.id.text_login_FR);
        mTextPassword = (EditText) view.findViewById(R.id.text_password_FR);
        mTextFirstName = (EditText) view.findViewById(R.id.text_firstname_FR);
        mTextLastName = (EditText) view.findViewById(R.id.text_lastname_FR);
        mRadioGender = (RadioGroup) view.findViewById(R.id.radiogroup_gender);
        mBtnRegister = (Button) view.findViewById(R.id.btn_register_FR);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFieldsEmpty()) {
                    String gender = null;

                    switch (mRadioGender.getCheckedRadioButtonId()) {
                        case R.id.radio_male_FR:
                            gender = "male";
                            break;
                        case R.id.radio_female_FR:
                            gender = "female";
                            break;
                    }

                    User user = new User(
                            mTextLogin.getText().toString(),
                            mTextPassword.getText().toString(),
                            mTextFirstName.getText().toString(),
                            mTextLastName.getText().toString(),
                            gender);

                    mRegisterEventHandler.onBtnConfirmPressed(user);
                } else
                    Toast.makeText(getContext(), R.string.toast_incompletefields, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mRegisterEventHandler = (RegisterEventHandler) context;
    }

    private boolean isFieldsEmpty() {
        return TextUtils.isEmpty(mTextLogin.getText())
                && TextUtils.isEmpty(mTextPassword.getText())
                && TextUtils.isEmpty(mTextFirstName.getText())
                && TextUtils.isEmpty(mTextLastName.getText());
    }

}
