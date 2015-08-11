package com.convenientmedical.frag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.convenientmedical.adapter.PayListAdapter;
import com.convenientmedical.main.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;

/**
 * @author Mr.Codey 支付fragment
 */
public class Pay extends Fragment {
	private View payView;
	private ListView mListView;
	private HashMap<String, Object> mHash;
	private List<HashMap<String, Object>> mList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		payView = inflater.inflate(R.layout.pay, container, false);
		mListView = (ListView) payView.findViewById(R.id.pay_list);
		mList = new ArrayList<HashMap<String, Object>>();
		addData();
		setAdapter();
		return payView;
	}

	private void addData() {
		
			mHash = new HashMap<String, Object>();
			mHash.put("project", "CT");
			mHash.put("money", "￥30");
			mList.add(mHash);
			
			mHash = new HashMap<String, Object>();
			mHash.put("project", "验血");
			mHash.put("money", "￥7");
			mList.add(mHash);
			
			mHash = new HashMap<String, Object>();
			mHash.put("project", "B超");
			mHash.put("money", "￥100");
			mList.add(mHash);
			
			mHash = new HashMap<String, Object>();
			mHash.put("project", "X光");
			mHash.put("money", "￥80");
			mList.add(mHash);
	}

	private void setAdapter() {
		PayListAdapter adapter = new PayListAdapter(getActivity(), mList);
		mListView.setAdapter(adapter);
	}
}
