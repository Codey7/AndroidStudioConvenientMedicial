package com.convenientmedical.pay;

import com.convenientmedical.main.R;
import com.convenitentmedical.savedata.RegInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PayDetails extends Activity implements OnClickListener{
private Button mbtPay;
private Button mbtGiveUp;
private TextView mtvPayItem,mtvPayMethod,mtvMoney,mtvTruePay;
private ImageButton mibBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_details);
		initView();
		
		//缴费项目设置 ，金额
		Intent intent=getIntent();
		if(intent!=null)
		{
			String[] s=intent.getStringArrayExtra("money");
			if(s!=null)
			{
			mtvMoney.setText(s[0]);
			mtvPayItem.setText(s[1]);
			}
			//挂号
			String[] item=intent.getStringArrayExtra("item");
			if(item!=null)
			{
				Log.i("tagitem", item[0]+"    "+item[1]);
				mtvPayItem.setText(item[0]);
				mtvPayMethod.setText(item[1]);
			}
			
			String[] pay_thing=intent.getStringArrayExtra("pay_things");
			if(pay_thing!=null)
			{
				Log.i("tagitem", pay_thing[0]+"    "+pay_thing[1]);
				mtvPayItem.setText(pay_thing[0]);
				mtvPayMethod.setText("检查");
				mtvMoney.setText(pay_thing[1]);
				mtvTruePay.setText("￥28");
			}
		}
		else
		{
		mtvPayItem.setText(RegInfo.getInstanse().getDepartment());
		mtvPayMethod.setText("挂号");}
	}
	private void initView()
	{
		mbtPay=(Button)findViewById(R.id.BT_Pay);
		mbtGiveUp=(Button)findViewById(R.id.BT_To_give_up);
		mibBack=(ImageButton)findViewById(R.id.Ib_back);
		mtvPayItem=(TextView)findViewById(R.id.Tv_Payment_item_result);
		mtvPayMethod=(TextView)findViewById(R.id.Tv_Payment_category_result);
		mtvMoney=(TextView)findViewById(R.id.Tv_Amount_of_money_result);
		mtvTruePay=(TextView)findViewById(R.id.Tv_Paid_result);
		mbtPay.setOnClickListener(this);
		mbtGiveUp.setOnClickListener(this);
		mibBack.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Ib_back:
			finish();
			break;
		case R.id.BT_Pay:
			Intent intent=new Intent(getApplicationContext(), PayMethod.class);
			startActivity(intent);
			finish();
			break;
		case R.id.BT_To_give_up:
		default:
			finish();
			break;
		}
	}

}
