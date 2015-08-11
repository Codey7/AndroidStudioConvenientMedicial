package com.convenientmedical.adapter;

import java.util.HashMap;
import java.util.List;

import com.convenientmedical.diagnoses.IllDetailsActivity;
import com.convenientmedical.main.R;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class IllDetailsListAdapter extends BaseAdapter {
	private Context mContext;
	private List<HashMap<String, Object>> mList;

	public IllDetailsListAdapter(Context context,
			List<HashMap<String, Object>> mlist) {
		mContext = context;
		mList = mlist;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mList == null) {
			return 0;
		}
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (mList == null) {
			return null;
		}
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HashMap<String, Object> mHash = new HashMap<String, Object>();
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.ill_details_list, parent, false);
			holder.mtvProject = (TextView) convertView
					.findViewById(R.id.tv_project);
			holder.mtvTime = (TextView) convertView.findViewById(R.id.tv_time);
			holder.mtvQueue = (TextView) convertView
					.findViewById(R.id.tv_queue);
			holder.mbtDetails = (Button) convertView
					.findViewById(R.id.bt_details);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mtvProject.setText(mList.get(position).get("project").toString());
		holder.mtvTime.setText(mList.get(position).get("time").toString());
		holder.mtvQueue.setText(mList.get(position).get("queue").toString());
		holder.mbtDetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s=mList.get(position).get("project").toString();
				Intent intent=new Intent(mContext, IllDetailsActivity.class);
				intent.putExtra("details", s);
				mContext.startActivity(intent);
			}
		});

		return convertView;
	}

	public static class ViewHolder {
		TextView mtvProject;
		TextView mtvTime;
		TextView mtvQueue;
		Button mbtDetails;
	}

}
