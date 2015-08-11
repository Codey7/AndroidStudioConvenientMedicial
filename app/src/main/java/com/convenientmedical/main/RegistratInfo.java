package com.convenientmedical.main;

import com.convenientmedical.diagnoses.DecodeQRCode;
import com.convenientmedical.pay.PayDetails;
import com.convenitentmedical.savedata.RegInfo;
import com.convenitentmedical.savedata.SharePreferenceUtil;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistratInfo extends Fragment implements OnClickListener{
	private Button mbtCancel, mbtPay;
//	private ImageButton mibBack;
	private TextView mtvHosName,mtvDept,mtvDoc,mtvTime,mtvRegTime;
	private  int status;
	private View InfoFrag;
	private SharedPreferences preferences;
	
	


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		InfoFrag=inflater.inflate(R.layout.registered_information, container, false);
		return InfoFrag;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		preferences=getActivity().getSharedPreferences("myshare", 0);
		initView();
		//设置相关内容
		mtvHosName.setText(RegInfo.getInstanse().getHospital());
		mtvDept.setText("科别："+RegInfo.getInstanse().getDepartment());
		mtvDoc.setText("医生姓名："+RegInfo.getInstanse().getDoctor());
        //挂号时间
        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("MM月dd日    HH:mm");
        Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        String    str    =    formatter.format(curDate);
        mtvTime.setText("挂号时间 ："+str);
        //挂号日期
        SimpleDateFormat formatter1    =   new    SimpleDateFormat    ("yy/MM/dd");
        Date curDate1    =   new    Date(System.currentTimeMillis());//获取当前时间
        String    str1    =    formatter1.format(curDate1);
        mtvRegTime.setText("挂号日期 ："+str1);
	}

/*	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registered_information);
		preferences=getSharedPreferences("myshare", MODE_PRIVATE);
		initView();
		mtvHosName.setText(RegInfo.getInstanse().getHospital());
	}*/
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setButtonFromStatus();
	}


	private void setButtonFromStatus() {
	int a=	
		SharePreferenceUtil.getInstanse().getIntData(preferences, "pay_success");
	if(a==1)
	{
		//mbtPay.setVisibility(View.GONE);
		mbtPay.setText("签到");
		mbtCancel.setText("取消问诊");
	}
		/*if(getActivity().getIntent()!=null)
		{
		Intent intent=getActivity().getIntent();
		status=intent.getIntExtra("status", 0);
		if(status==1)
		{
			mbtPay.setVisibility(View.GONE);
			mbtCancel.setText("取消问诊");
		}
		}*/
		
	}

	private void initView() {
		mtvHosName=(TextView)InfoFrag.findViewById(R.id.Tv_Hospital_name);
		mtvDept=(TextView)InfoFrag.findViewById(R.id.Tv_Division);
		mtvDoc=(TextView)InfoFrag.findViewById(R.id.Tv_Doctor_name);
		mtvTime= (TextView) InfoFrag.findViewById(R.id.Tv_Visiting_time);
        mtvRegTime=(TextView)InfoFrag.findViewById(R.id.Tv_Registration_date);
//		mibBack=(ImageButton)InfoFrag.findViewById(R.id.Ib_back);
		mbtCancel = (Button) InfoFrag.findViewById(R.id.BT_Cancel_registration);
		mbtPay = (Button) InfoFrag.findViewById(R.id.BT_To_pay_for);
		mbtCancel.setOnClickListener(this);
		mbtPay.setOnClickListener(this);
//		mibBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		/*case R.id.Ib_back:
			finish();*/
		case R.id.BT_Cancel_registration:
			if(mbtCancel.getText().equals("取消问诊"))
			{
				Intent intent=new Intent(getActivity(),MainActivity.class);
				intent.putExtra("info", 0);
				startActivity(intent);
			}
			else
			{
				Intent intent=new Intent(getActivity(),MainActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.BT_To_pay_for:
			if(mbtPay.getText().equals("签到"))
			{
				Intent intent=new Intent(getActivity(), DecodeQRCode.class);
				startActivity(intent);
			}
			else
			{
			SharePreferenceUtil.getInstanse().putIntData(preferences, "pay_from", 0);
			Intent intent1=new Intent(getActivity(), PayDetails.class);
			Log.i("item", RegInfo.getInstanse().getDepartment());
			String[] s={RegInfo.getInstanse().getDepartment(),"挂号"};
			intent1.putExtra("item", s);
			startActivity(intent1);}
		default:
			break;
		}
	}
}
