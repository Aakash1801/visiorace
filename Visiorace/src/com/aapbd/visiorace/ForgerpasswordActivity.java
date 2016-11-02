package com.aapbd.visiorace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aapbd.visiorace.utils.AlertMessage;
import com.aapbd.visiorace.utils.ValidateEmail;

public class ForgerpasswordActivity extends Activity {
	// private TextView register;
	private EditText email;
	private Context con;
	private String memail;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.forgetpass);
		con = this;
		// register = (TextView) findViewById(R.id.Register);
		email = (EditText) findViewById(R.id.ForgetPassword);

	}

	public void setBack(View v) {
		finish();
	}

	public void setForget(View v) {
		forgetPassVaildation();
	}

	public void forgetPassVaildation() {
		if (TextUtils.isEmpty(email.getText())) {
			AlertMessage.showMessage(con, "Error",
					"Please Enter Nome utente o email");
			return;
		}
		if (!ValidateEmail.validateEmail(email.getText().toString())) {
			AlertMessage.showMessage(con, "Error",
					"Please enter a valid Email address.");
			return;
		} else {
			Toast.makeText(con, "Will have Action", 1000).show();
		}
	}

}
