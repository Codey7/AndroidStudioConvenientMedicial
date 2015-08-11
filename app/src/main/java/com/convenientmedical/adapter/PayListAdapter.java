package com.convenientmedical.adapter;

import java.util.HashMap;
import java.util.List;

import com.convenientmedical.adapter.IllDetailsListAdapter.ViewHolder;
import com.convenientmedical.diagnoses.IllDetailsActivity;
import com.convenientmedical.main.R;
import com.convenientmedical.pay.PayDetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PayListAdapter extends BaseAdapter {

	private Context mContext;
	private List<HashMap<String, Object>> mList;

	public PayListAdapter(Context context,
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
					R.layout.pay_list, parent, false);
			holder.mtvProject = (TextView) convertView
					.findViewById(R.id.pay_project);
			holder.mtvMoney = (TextView) convertView.findViewById(R.id.pay_money);
			holder.mbtPay = (Button) convertView
					.findViewById(R.id.bt_pay);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mtvProject.setText(mList.get(position).get("project").toString());
		holder.mtvMoney.setText(mList.get(position).get("money").toString());
		holder.mbtPay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(mContext, PayDetails.class);
				String[] s={mList.get(position).get("project").toString(),mList.get(position).get("money").toString()};
				intent.putExtra("pay_things", s);
				mContext.startActivity(intent);
			}
		});

		return convertView;
	}

	public static class ViewHolder {
		TextView mtvProject;
		TextView mtvMoney;
		Button mbtPay;
	}

}
