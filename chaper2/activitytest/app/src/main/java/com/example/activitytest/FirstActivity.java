package com.example.activitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
//                public void onClick(View v){//提示消息
//                    Toast.makeText(FirstActivity.this,"You clicked Button 1",
//                            Toast.LENGTH_SHORT).show();
//                }
//                public void onClick(View v){//毁掉活动
//                    finish();
//                }
//                public void onClick(View v){//隐式Intent
//                    Intent intent = new Intent("com.example.activitytest.ACTION_START");
//                    intent.addCategory("com.example.activitytest.MY_CATEGORY");
//                    startActivity(intent);
//                }
//                public void onClick(View v){//程序间共享
//                    Intent intent = new Intent(Intent.ACTION_DIAL);
//                    intent.setData(Uri.parse("tel:10086"));
//                    startActivity(intent);
//                }
//                public void onClick(View v){//向下传递字符串
//                    String data ="Hello SecondActivity";
//                    Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                    intent.putExtra("extra_data",data);
//                    startActivity(intent);
//                }
                public void onClick(View v) {//向上传递数据
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    startActivityForResult(intent, 1);
                }
           });
  }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnedData);
                }
                break;
            default:
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
        }
    }