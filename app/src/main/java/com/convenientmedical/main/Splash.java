package com.convenientmedical.main;

import com.convenitentmedical.savedata.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

/**
 * splash界面
 * 
 * @author Mr.Codey
 *
 */
public class Splash extends Activity {
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LinearLayout ll_splash_main;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		preferences = getSharedPreferences("myshare", MODE_PRIVATE);
		ll_splash_main = (LinearLayout) findViewById(R.id.ll_splash_main);
		AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
		aa.setDuration(3000);
		ll_splash_main.startAnimation(aa);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				setMainActivity();
				SharePreferenceUtil.getInstanse().putIntData(preferences, "report", 0);
			}
		}, 3000);

	}

	/**
	 * 设置主程序入口 如果已经登录过，直接进入主界面  否则进入登录界面
	 */
	private void setMainActivity() {
		Intent intent = new Intent();
		Log.e("logstatus",SharePreferenceUtil.getInstanse().getIntData(preferences,
				"LOG_IN_STATUS")+"");
		if (SharePreferenceUtil.getInstanse().getIntData(preferences,
				"LOG_IN_STATUS") == 1) {
			intent.setClass(getApplicationContext(), MainActivity.class);
			intent.setAction(Intent.ACTION_MAIN);
			startActivity(intent);
			Splash.this.finish();
		} else {
			intent = new Intent(Splash.this, NavViewActivity.class);
			startActivity(intent);
			Splash.this.finish();
		}
	}
}
