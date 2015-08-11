package com.convenientmedical.frag;

import com.convenientmedical.main.LogIn;
import com.convenientmedical.main.R;
import com.convenientmedical.me.MyBankCard;
import com.convenientmedical.me.MyHistory;
import com.convenientmedical.me.MyInfo;
import com.convenientmedical.me.SocialCard;
import com.convenitentmedical.savedata.SharePreferenceUtil;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @author Mr.Codey 个人信息fragment
 */
public class Me extends Fragment implements OnClickListener {
	private View meView;
	private TextView myInfo, mHistory, mSocialCard, mBankCard;
	private ImageButton mSocialImg;
	private Button mbtLogOut;
	private static final int LOG_IN_STATUS = 0;// 登出状态值
	private SharedPreferences preferences;// 保存登录状态
	public static final int MODE_PRIVATE = 0x0000;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		meView = inflater.inflate(R.layout.personal_information, container,
				false);
		return meView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		preferences = getActivity().getSharedPreferences("myshare",
				MODE_PRIVATE);
		initView();
	}

	private void initView() {
		myInfo = (TextView) meView.findViewById(R.id.Tv_Basic_informationl);
		mHistory = (TextView) meView
				.findViewById(R.id.Tv_History_of_diagnosis_and_treatment);
		mSocialCard = (TextView) meView
				.findViewById(R.id.Tv_My_social_security_card);
		mBankCard = (TextView) meView.findViewById(R.id.Tv_My_bank_card);
		mSocialImg = (ImageButton) meView.findViewById(R.id.Ib_Right2);
		mbtLogOut = (Button) meView.findViewById(R.id.BT_Cancellation);
		myInfo.setOnClickListener(this);
		mHistory.setOnClickListener(this);
		mSocialCard.setOnClickListener(this);
		mBankCard.setOnClickListener(this);
		mbtLogOut.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.Tv_Basic_informationl:
			intent=new Intent(getActivity(), MyInfo.class);
			startActivity(intent);
			break;
		case R.id.Tv_History_of_diagnosis_and_treatment:
			intent=new Intent(getActivity(), MyHistory.class);
			startActivity(intent);
			break;
		case R.id.Tv_My_social_security_card:
			intent = new Intent(getActivity(), SocialCard.class);
			startActivity(intent);
			break;
		case R.id.Tv_My_bank_card:
			intent=new Intent(getActivity(), MyBankCard.class);
			startActivity(intent);
			break;
		case R.id.BT_Cancellation:
		default:
			SharePreferenceUtil.getInstanse().putIntData(preferences,
					"LOG_IN_STATUS", LOG_IN_STATUS);
			intent = new Intent(getActivity(), LogIn.class);
			startActivity(intent);
			getActivity().finish();
		}
	}
}
