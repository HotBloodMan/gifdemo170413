package com.example.a1.gifdemo170413;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HandlerCalActivity extends AppCompatActivity {
    static final String UPPER_NUM = "upper";
    private EditText etNum;
    private CalThread calThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_cal);
        etNum = (EditText) findViewById(R.id.etNum);
        calThread = new CalThread();
        calThread.start();
    }
    public void cal(View v){
        Message msg = new Message();
        msg.what=0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM,Integer.parseInt(etNum.getText().toString()));
        msg.setData(bundle);

    }
    class CalThread extends  Thread{
        private Handler h;

        @Override
        public void run() {
            Looper.prepare();
           h= new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==0x123){
                        int upper=msg.getData().getInt(UPPER_NUM);
                        Toast.makeText(HandlerCalActivity.this,"aaa--->> "+upper,Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Looper.loop();
        }
    }

}
