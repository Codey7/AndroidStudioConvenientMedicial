package com.convenientmedical.me;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.convenientmedical.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyHistory extends Activity {
	private ListView mListView;
	private HashMap<String, String> mHash;
	private List<HashMap<String, String>> mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_treatment_list);
		mListView = (ListView) findViewById(R.id.history_list);
		mList = new ArrayList<HashMap<String, String>>();
		addData();
		setAdapter();
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
                Log.i("sdf","no");
				Intent intent=new Intent(getApplicationContext(),MyCase.class);
				startActivity(intent);
			}
		});
	}

	private void addData() {
		mHash = new HashMap<String, String>();
		mHash.put("time", "2015/6/1");
		mHash.put("hospital", "东方医院");
		mList.add(mHash);
        mHash = new HashMap<String, String>();
        mHash.put("time", "2015/6/1");
        mHash.put("hospital", "东方医院");
        mList.add(mHash);mHash = new HashMap<String, String>();
        mHash.put("time", "2015/6/1");
        mHash.put("hospital", "东方医院");
        mList.add(mHash);

	}
	private void setAdapter()
	{
		SimpleAdapter adapter=new SimpleAdapter(getApplicationContext(), mList, R.layout.history_list,
				new String[]{"time","hospital"} , new int[]{R.id.Tv_History_treatment_list_Date,R.id.Tv_Hospital_name});
		mListView.setAdapter(adapter);
	}
}
