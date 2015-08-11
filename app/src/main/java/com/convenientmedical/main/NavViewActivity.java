package com.convenientmedical.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class NavViewActivity extends Activity {
	private ViewPager mViewPager;
	private List<View> viewList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager);
		LayoutInflater mInflater = getLayoutInflater().from(this);

		View v1 = mInflater.inflate(R.layout.nav_layout1, null);
		View v2 = mInflater.inflate(R.layout.nav_layout2, null);
		View v3 = mInflater.inflate(R.layout.nav_layout3, null);

		// 添加页面数据
		viewList = new ArrayList<View>();
		viewList.add(v1);
		viewList.add(v2);
		viewList.add(v3);
		// 实例化适配器
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 3;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(viewList.get(position));// 删除页卡
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub

				container.addView(viewList.get(position));
				/*
				 * weibo_button=(Button) findViewById(R.id.button1);
				 * weibo_button.setOnClickListener(new OnClickListener() {
				 * 
				 * public void onClick(View v) { intent=new
				 * Intent(ViewPagerDemo.this,WeiBoActivity.class);
				 * startActivity(intent); } });
				 */
				return viewList.get(position);

			}
		});

		mViewPager.setCurrentItem(0); // 设置默认当前页

		View view = viewList.get(2);
		final ImageView imageView = (ImageView) view
				.findViewById(R.id.image_nav);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method
				imageView.setImageResource(R.drawable.button_hovered);
				Intent intent = new Intent(getApplicationContext(), LogIn.class);
				startActivity(intent);
				finish();
			}

		});

	}

}
