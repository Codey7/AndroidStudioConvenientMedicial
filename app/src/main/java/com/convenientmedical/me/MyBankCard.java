package com.convenientmedical.me;

import com.convenientmedical.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MyBankCard extends Activity {
private ImageButton mIbAdd;
private ImageButton mibBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_bank_card);
		mIbAdd=(ImageButton)findViewById(R.id.bt_Edit);
		mIbAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(), AddBankCard.class);
				startActivity(intent);
			}
		});
		mibBack=(ImageButton)findViewById(R.id.Ib_left);
		mibBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
