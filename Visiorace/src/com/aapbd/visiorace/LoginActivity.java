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

public class LoginActivity extends Activity {
	private TextView register, forgetPass;
	private EditText userName, passWord;
	private Context con;
	private String muserName, mpassWord;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		con = this;
		register = (TextView) findViewById(R.id.Register);
		forgetPass = (TextView) findViewById(R.id.forGetPass);
		userName = (EditText) findViewById(R.id.Username);
		passWord = (EditText) findViewById(R.id.Password);

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent next = new Intent(con, RegisterActivity.class);
				startActivity(next);
			}
		});

		forgetPass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent next = new Intent(con, ForgerpasswordActivity.class);
				startActivity(next);
			}
		});
	}

	public void setBack(View v) {
		finish();
	}

	public void setLogin(View v) {
		loginVaildation();
	}

	public void loginVaildation() {
		if (TextUtils.isEmpty(userName.getText())) {
			AlertMessage.showMessage(con, "Error", "Please Enter Username");
			return;
		}
		if (TextUtils.isEmpty(passWord.getText())) {
			AlertMessage.showMessage(con, "Error", "Please Enter Password");
			return;
		} else {
			// Toast.makeText(con, "Will have Action", 1000).show();
			Intent next = new Intent(con, SingleEventActivity.class);
			startActivity(next);
		}
	}

}
