package com.convenientmedical.main;

import com.convenientmedical.frag.Me;
import com.convenientmedical.frag.Diagnoses;
import com.convenientmedical.frag.Pay;
import com.convenientmedical.frag.Registration;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author Mr.Codey main 包含四个fragment
 */

public class MainActivity extends Activity implements OnClickListener {

	// 定义四个fragment
	private Registration regFrag;
	private Pay payFrag;
	private Diagnoses newsFrag;
	private Me meFrag;
	private RegistratInfo registratInfo;

	// 四个layout
	private View regLayout;
	private View payLayout;
	private View newsLayout;
	private View meLayout;

	// 四个底部图标
	private ImageView regImage;
	private ImageView payImage;
	private ImageView newsImage;
	private ImageView meImage;

	// 管理fragment
	private FragmentManager fragmentManager;

	// intent
	private Intent myintent;
	private int VIEW_STATUS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myintent = getIntent();
		if (myintent != null) {
			VIEW_STATUS = myintent.getIntExtra("info", 0);
		}

		initViews();
		fragmentManager = getFragmentManager();
		// 二维码签到之后退出时，进入第二个模块
		if (myintent.getBooleanExtra("come", true) == false) {
			setTabSelection(1);
		} else {
			setTabSelection(0);
		}

	}

	private void setTabSelection(int index) {
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (index) {
		case 0:
			// 更改图标
			regImage.setImageResource(R.drawable.chat_logo_hovered);
			if (VIEW_STATUS == 0)
			// 如果为空，则创建
			{
				if (regFrag == null) {
					regFrag = new Registration();
					transaction.add(R.id.content, regFrag);
				} else {
					transaction.show(regFrag);
				}
			} else if (VIEW_STATUS == 1) {
				if (registratInfo == null) {
					registratInfo = new RegistratInfo();
					transaction.add(R.id.content, registratInfo);
				} else {
					transaction.show(registratInfo);
				}
			}
			break;
		case 1:
			payImage.setImageResource(R.drawable.doctor_logo_hovered);
			// 如果为空，则创建
			if (newsFrag == null) {
				newsFrag = new Diagnoses();
				transaction.add(R.id.content, newsFrag);
			} else {
				transaction.show(newsFrag);
			}
			break;
		case 2:
			newsImage.setImageResource(R.drawable.news_logo_hovered);
			// // 如果为空，则创建
			if (payFrag == null) {
				payFrag = new Pay();
				transaction.add(R.id.content, payFrag);
			} else {
				transaction.show(payFrag);
			}
			break;

		case 3:
		default:
			meImage.setImageResource(R.drawable.me_logo_hovered);
			// 如果为空，则创建
			if (meFrag == null) {
				meFrag = new Me();
				transaction.add(R.id.content, meFrag);
			} else {
				transaction.show(meFrag);
			}

			break;
		}
		transaction.commit();
	}

	/**
	 * @param fragmentTransaction
	 *            隐藏所有
	 */
	private void hideFragments(FragmentTransaction fragmentTransaction) {
		if (registratInfo != null) {
			fragmentTransaction.hide(registratInfo);
		}
		if (regFrag != null) {
			fragmentTransaction.hide(regFrag);
		}
		if (payFrag != null) {
			fragmentTransaction.hide(payFrag);
		}
		if (newsFrag != null) {
			fragmentTransaction.hide(newsFrag);
		}
		if (meFrag != null) {
			fragmentTransaction.hide(meFrag);
		}
	}

	private void clearSelection() {
		regImage.setImageResource(R.drawable.chat_logo);
		payImage.setImageResource(R.drawable.doctor_logo);
		newsImage.setImageResource(R.drawable.news_logo);
		meImage.setImageResource(R.drawable.me_logo);
	}

	/**
	 * 初始化控件并设置监听
	 */
	private void initViews() {
		regLayout = findViewById(R.id.reg_layout);
		payLayout = findViewById(R.id.pay_layout);
		newsLayout = findViewById(R.id.news_layout);
		meLayout = findViewById(R.id.me_layout);

		regImage = (ImageView) findViewById(R.id.reg_image);
		payImage = (ImageView) findViewById(R.id.pay_image);
		newsImage = (ImageView) findViewById(R.id.news_image);
		meImage = (ImageView) findViewById(R.id.me_image);

		regLayout.setOnClickListener(this);
		payLayout.setOnClickListener(this);
		newsLayout.setOnClickListener(this);
		meLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.reg_layout:
			setTabSelection(0);
			break;
		case R.id.pay_layout:
			setTabSelection(1);
			break;
		case R.id.news_layout:
			setTabSelection(2);
			break;
		case R.id.me_layout:
			setTabSelection(3);
			break;
		default:
			break;
		}

	}

	// 再按一次返回键退出
	private long firstTime;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (System.currentTimeMillis() - firstTime < 2000) {
			finish();
		} else {
			firstTime = System.currentTimeMillis();

			Toast.makeText(this, R.string.press_again_backrun, 1000).show();
			// T.showShort(this, R.string.press_again_backrun);
		}
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 */
}
