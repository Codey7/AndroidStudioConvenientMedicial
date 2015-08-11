package com.convenientmedical.diagnoses;

import com.convenientmedical.main.R;
import com.convenientmedical.pay.PayDetails;
import com.convenitentmedical.savedata.RegInfo;
import com.convenitentmedical.savedata.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class IllDetailsActivity extends Activity {
	private ImageButton mibBack;
	private Button mButton;
	private TextView mtvPay, mtvCheck, mtvPayItem,mtvMoney,mtvCome;
	private String payString, checkString;
	private int payStatus;
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blood_test);
		initView();

		Intent intent = getIntent();
		if (intent != null) {
			payStatus = intent.getIntExtra("pay_status", 0);
			if (payStatus == 1)
				mtvPay.setText("已缴费");
			if (intent.getStringExtra("details") != null) {
				String s = intent.getStringExtra("details");
				mtvPayItem.setText(s);
			}
		}
		
		preferences = getSharedPreferences("myshare", MODE_PRIVATE);
		
		
		if(SharePreferenceUtil.getInstanse().getIntData(preferences, "report")==1)
		{
			mtvCheck.setText("已签到");
		}
		
		setButtonText();
		
		mibBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (payString.equals("未缴费")) {
					// mButton.setText("缴费");
					SharePreferenceUtil.getInstanse().putIntData(preferences,
							"pay_from", 1);
					Intent intent = new Intent(getApplicationContext(),
							PayDetails.class);
					String[] s={mtvMoney.getText().toString(),mtvPayItem.getText().toString()};
					intent.putExtra("money",s);
					startActivity(intent);
					finish();
				} else if (checkString.equals("未签到")) {
					// mButton.setText("签到");
					Intent intent = new Intent(getApplicationContext(),
							DecodeQRCode.class);
					SharePreferenceUtil.getInstanse().putIntData(preferences, "report", 1);
					startActivity(intent);
					finish();
				} else if (checkString.equals("已签到")){
					// mButton.setText("查看检查报告");
					Intent intent = new Intent(getApplicationContext(),
							IllDetailsResportActivity.class);
					startActivity(intent);
				}
			}
		});
	}

	private void initView() {
		mibBack = (ImageButton) findViewById(R.id.Ib_left);
		mButton = (Button) findViewById(R.id.BT_Inspection_report);
		mtvPay = (TextView) findViewById(R.id.Tv_Payment_status_result);
		mtvCheck = (TextView) findViewById(R.id.Tv_Sign_in_state_result);
		mtvPayItem = (TextView) findViewById(R.id.Tv_test_blood);
		mtvMoney=(TextView) findViewById(R.id.Tv_Cost_result);
	}

	/**
	 * 设置字体
	 */
	private void setButtonText() {
		payString = mtvPay.getText().toString().trim();
		checkString = mtvCheck.getText().toString().trim();
		if (payString.equals("未缴费")) {
			mButton.setText("缴费");
		} else if (checkString.equals("未签到")) {
			mButton.setText("签到");
		} else if(checkString.equals("已签到")){
			mButton.setText("查看检查报告");
		}
	}
}
