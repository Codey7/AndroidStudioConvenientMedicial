package com.convenientmedical.pay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.convenientmedical.main.MainActivity;
import com.convenientmedical.main.R;
import com.convenitentmedical.savedata.SharePreferenceUtil;

/**
 * Created by Mr.Codey on 2015/8/11.
 */
public class AliPay extends Activity
{
    private SharedPreferences preferences;
    private ImageView mivPay;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ali_pay);
        preferences=getSharedPreferences("myshare", MODE_PRIVATE);
        mivPay= (ImageView) findViewById(R.id.ali_pay);
        mivPay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharePreferenceUtil.getInstanse().putIntData(preferences, "pay_success", 1);
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("info", 1);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "缴费成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
