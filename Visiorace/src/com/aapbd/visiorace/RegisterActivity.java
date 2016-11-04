package com.aapbd.visiorace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.aapbd.visiorace.utils.AlertMessage;
import com.aapbd.visiorace.utils.ValidateEmail;

public class RegisterActivity extends Activity {

	private EditText userName, nome, cogNome, sesso, dateDl, email, passWord;
	private String muserName, mnome, mcogNome, msesso, mdateDl, memail,
			mpassWord;
	private Context con;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		con = this;

		userName = (EditText) findViewById(R.id.Username);
		nome = (EditText) findViewById(R.id.Nome);
		cogNome = (EditText) findViewById(R.id.Cognome);
		sesso = (EditText) findViewById(R.id.Sesso);
		dateDl = (EditText) findViewById(R.id.DateDi);
		email = (EditText) findViewById(R.id.Email);
		passWord = (EditText) findViewById(R.id.Password);

	}

	public void setBack(View v) {
		finish();
	}

	public void setRegister(View v) {
		registerVaildation();
	}

	public void registerVaildation() {
		if (TextUtils.isEmpty(userName.getText())) {
			AlertMessage.showMessage(con, "Error", "Please Enter Username");
			return;
		}
		if (TextUtils.isEmpty(nome.getText())) {
			AlertMessage.showMessage(con, "Error", "Please Enter Nome");
			return;
		}
		if (TextUtils.isEmpty(cogNome.getText())) {
			AlertMessage.showMessage(con, "Error", "Please Enter Cognome");
			return;
		}
		if (TextUtils.isEmpty(sesso.getText())) {
			AlertMessage.showMessage(con, "Error", "Please Enter Sesso");
			return;
		}
		if (TextUtils.isEmpty(dateDl.getText())) {
			AlertMessage.showMessage(con, "Error",
					"Please Enter Date di nascita");
			return;
		}
		if (TextUtils.isEmpty(email.getText())) {
			AlertMessage.showMessage(con, "Error", "Please Enter Email");
			return;
		}
		if (!ValidateEmail.validateEmail(email.getText().toString())) {
			AlertMessage.showMessage(con, "Error",
					"Please enter a valid Email address.");
			return;
		}
		if (TextUtils.isEmpty(passWord.getText())) {
			AlertMessage.showMessage(con, "Error", "Please Enter Password");
			return;
		} else {
			Intent next = new Intent(con, SingleEventActivity.class);
			startActivity(next);
		}
	}

}
