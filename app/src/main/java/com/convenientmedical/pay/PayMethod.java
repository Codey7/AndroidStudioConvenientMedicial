package com.convenientmedical.pay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.convenientmedical.diagnoses.IllDetailsActivity;
import com.convenientmedical.main.MainActivity;
import com.convenientmedical.main.R;
import com.convenientmedical.main.RegistratInfo;
import com.convenitentmedical.savedata.SharePreferenceUtil;

public class PayMethod extends Activity {
private Button mbtPay;
private RadioGroup mGroup;
private ImageButton mibBack;

private RadioButton mraButton1,mraButton2,mraButton3,mraButton4;
private SharedPreferences preferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_method);
		initView();
		preferences=getSharedPreferences("myshare", MODE_PRIVATE);
		mibBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		getGroupChecked();
		payClick();
	}
	private void payClick() {
		mbtPay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (SharePreferenceUtil.getInstanse().getIntData(preferences, "pay_from")==0) {
					/*SharePreferenceUtil.getInstanse().putIntData(preferences, "pay_success", 1);
					Intent intent=new Intent(getApplicationContext(), MainActivity.class);
					intent.putExtra("info", 1);
					startActivity(intent);
					finish();*/
					Intent intent=new Intent(getApplicationContext(),AliPay.class);
					startActivity(intent);
					finish();
				}
				else{

					Intent intent=new Intent(getApplicationContext(), IllDetailsActivity.class);
					intent.putExtra("pay_status", 1);
					startActivity(intent);
					finish();
				}
			}
		});
	}
	/**
	 * 获取缴费方式
	 */
	private void getGroupChecked() {
		mGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==mraButton1.getId())
				{
					 Toast.makeText(getApplicationContext(),mraButton1.getText().toString(), Toast.LENGTH_LONG).show();
				}
				if(checkedId==mraButton2.getId())
				{
					 Toast.makeText(getApplicationContext(),mraButton2.getText().toString(), Toast.LENGTH_LONG).show();
				}
				if(checkedId==mraButton3.getId())
				{
					 Toast.makeText(getApplicationContext(),mraButton3.getText().toString(), Toast.LENGTH_LONG).show();
				}
				if(checkedId==mraButton4.getId())
				{
					 Toast.makeText(getApplicationContext(),mraButton4.getText().toString(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	/**
	 * 实例化
	 */
	private void initView() {
		mbtPay=(Button)findViewById(R.id.BT_sure);
		mibBack=(ImageButton)findViewById(R.id.Ib_back);
		mGroup=(RadioGroup)findViewById(R.id.Rg_car_select);
		mraButton1=(RadioButton)findViewById(R.id.RB_Alipay);
		mraButton2=(RadioButton)findViewById(R.id.RB_Social_security_card);
		mraButton3=(RadioButton)findViewById(R.id.RB_UnionPay_payment);
		mraButton4=(RadioButton)findViewById(R.id.RB_Quick_payment);
	}

}
