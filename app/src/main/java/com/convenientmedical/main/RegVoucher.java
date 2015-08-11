package com.convenientmedical.main;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 挂号凭证，用户可以取消挂号，或者签到
 * 
 * @author Mr.Codey
 *
 */
public class RegVoucher extends Activity {
	private Button mbtCalcel;
	private Button mbtSign;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interrogation);
		//取消问诊
		mbtCalcel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
			}
		});
		
		//签到
		mbtSign.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void initView() {
		mbtCalcel = (Button) findViewById(R.id.BT_Cancel_the_interrogation);
		mbtSign=(Button)findViewById(R.id.BT_Inquiry_into);
	}
}
