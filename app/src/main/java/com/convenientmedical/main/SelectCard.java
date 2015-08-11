package com.convenientmedical.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectCard extends Activity {
	/** Called when the activity is first created. */
	private RadioGroup group;
	private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_method);
		group = (RadioGroup) this.findViewById(R.id.Rg_car_select);
		button = (Button) this.findViewById(R.id.BT_sure);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int len = group.getChildCount();// 获得单选按钮组的选项个数
				String msgString = "";
				for (int i = 0; i < len; i++) {
					RadioButton radioButton = (RadioButton) group.getChildAt(i);
					if (radioButton.isChecked()) {
						msgString = radioButton.getText().toString();
						break;
					}
				}
				Toast.makeText(SelectCard.this, msgString, 1).show();
			}
		});
	}
}
