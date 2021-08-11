package com.alc.echange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class OTPVerificationActivity extends AppCompatActivity {

    EditText mEditText1, mEditText2, mEditText3, mEditText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        mEditText1 = findViewById(R.id.edit_text_one);
        mEditText2 = findViewById(R.id.edit_text_two);
        mEditText3 = findViewById(R.id.edit_text_three);
        mEditText4 = findViewById(R.id.edit_text_four);

        mEditText1.setInputType(InputType.TYPE_NULL);
        mEditText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditText1.setInputType(InputType.TYPE_CLASS_NUMBER);
                mEditText1.requestFocus();
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.showSoftInput(mEditText1, InputMethodManager.SHOW_FORCED);

            }
        });

        mEditText1.addTextChangedListener(new GenericTextWatcher(mEditText1));
        mEditText2.addTextChangedListener(new GenericTextWatcher(mEditText2));
        mEditText3.addTextChangedListener(new GenericTextWatcher(mEditText3));
        mEditText4.addTextChangedListener(new GenericTextWatcher(mEditText4));
    }

    public class GenericTextWatcher implements TextWatcher
    {
        private View view;
        private GenericTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch(view.getId())
            {
                case R.id.edit_text_one:
              