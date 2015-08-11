package com.convenientmedical.main;

import java.util.List;

import com.convenientmedical.json.ParseJson;
import com.convenientmedicial.DataList.SelectAreaData;
import com.convenitentmedical.savedata.RegInfo;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * 选择地区
 * @author Mr.Codey
 *
 */
public class SelectAreaList extends Activity {

	private ListView listView;
	private ArrayAdapter<String> adapter;
	private List<String> data = null;// 表示数据源
	private ImageButton mibBack;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_area);
//		overridePendingTransition(R.anim.zoom_in,  R.anim.zoom_out);
		listView = (ListView) this.findViewById(R.id.LV_select_area);
		mibBack=(ImageButton)findViewById(R.id.Ib_left);
		mibBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		selectAreaAdapter();
		
		String s="[{"+"\"name\""+":\"named\"}]";
		Log.e("string", s);
		ParseJson.getInstanse().parseSelJson(s);
	}

	private void selectAreaAdapter() {
		data=SelectAreaData.getDataSource(0);
		adapter = new ArrayAdapter<String>(SelectAreaList.this,
				R.layout.select_list_text, data);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				RegInfo.getInstanse().setArea(parent.getItemAtPosition(position).toString().trim());
				Intent intent=new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
