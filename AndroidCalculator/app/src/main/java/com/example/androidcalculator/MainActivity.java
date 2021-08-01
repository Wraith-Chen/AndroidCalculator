package com.example.androidcalculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int left_brackets_flag = 0;//左括号数量，与有括号匹配使用
    boolean minus_add = false;//负号添加
    boolean brackets_add = false;//括号添加
    boolean zero = false;//判断0的数量
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {//状态栏透明
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();//隐藏标题栏
        if (actionBar != null) {
            actionBar.hide();
        }
        Button button_0 = (Button) findViewById(R.id.button_0);
        Button button_1 = (Button) findViewById(R.id.button_1);
        Button button_2 = (Button) findViewById(R.id.button_2);
        Button button_3 = (Button) findViewById(R.id.button_3);
        Button button_4 = (Button) findViewById(R.id.button_4);
        Button button_5 = (Button) findViewById(R.id.button_5);
        Button button_6 = (Button) findViewById(R.id.button_6);
        Button button_7 = (Button) findViewById(R.id.button_7);
        Button button_8 = (Button) findViewById(R.id.button_8);
        Button button_9 = (Button) findViewById(R.id.button_9);
        Button button_point = (Button) findViewById(R.id.button_point);
        Button button_add = (Button) findViewById(R.id.button_add);
        Button button_mul = (Button) findViewById(R.id.button_mul);
        Button button_div = (Button) findViewById(R.id.button_div);
        Button button_minus = (Button) findViewById(R.id.button_minus);
        Button button_delete = (Button) findViewById(R.id.button_delete);
        Button button_AC = (Button) findViewById(R.id.button_AC);
        Button button_equal = (Button) findViewById(R.id.button_equal);
        Button button_left_bracket = (Button) findViewById(R.id.button_left_bracket);
        Button button_right_bracket = (Button) findViewById(R.id.button_right_bracket);
        textView = (TextView) findViewById(R.id.textView);

        //调用监听器
        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_mul.setOnClickListener(this);
        button_div.setOnClickListener(this);
        button_equal.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_delete.setOnClickListener(this);
        button_AC.setOnClickListener(this);
        button_left_bracket.setOnClickListener(this);
        button_right_bracket.setOnClickListener(this);
    }

    //数字输出
    public String figure(int number, String str, boolean zero){
        if (str.length() != 0 && str.charAt(str.length() - 1) == ')')
            str+="*"+number;
        else {
            if (zero == true)
                str=str.substring(0,str.length()-1);
                str+=number;
        }
        return str;
    }

    @Override
    public void onClick(View view) {
        int number;
        Character dig = '0';
        String str = null;
        str = textView.getText().toString();//获取textView里的字符串

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);//警告

        if (str.length() == 0 || str.charAt(str.length() - 1) != '0') {//判断数字前零的存在。
            zero = false;
        }

        switch (view.getId()) {
            case R.id.button_0:
                if (str.length() == 0 || str.charAt(str.length() - 1) != '/') {//判断除数是否为零
                    if (str.length() == 0 || (!dig.isDigit(str.charAt(str.length() - 1)) && str.charAt(str.length() - 1) != '.')) {
                        str += "0";
                        zero = true;
                    }
                    if (zero == false) {//清除多余的0
                        if (str.length() == 1 && str.charAt(0) == '0') {
                            zero = true;
                            break;
                        }
                        str += "0";
                    }
                    textView.setText(str);
                } else {
                    dialog.setTitle("注意！");
                    dialog.setMessage("除数不能为0！");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.
                            OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
//                        dialog. setNegativeButton( "Cancel",new DialogInterface .
//                                OnClickListener( ) {
//                            @Override
//                            public void onClick(DialogInterface dialog,int which) {
//                            }
//                        });
                    dialog.show();
                }
                break;
            case R.id.button_1:
                str = figure(1, str, zero);
                textView.setText(str);
                break;
            case R.id.button_2:
                str = figure(2, str, zero);
                textView.setText(str);
                break;
            case R.id.button_3:
                str = figure(3, str, zero);
                textView.setText(str);
                break;
            case R.id.button_4:
                str = figure(4, str, zero);
                textView.setText(str);
                break;
            case R.id.button_5:
                str = figure(5, str, zero);
                textView.setText(str);
                break;
            case R.id.button_6:
                str = figure(6, str, zero);
                textView.setText(str);
                break;
            case R.id.button_7:
                str = figure(7, str, zero);
                textView.setText(str);
                break;
            case R.id.button_8:
                str = figure(8, str, zero);
                textView.setText(str);
                break;
            case R.id.button_9:
                str = figure(9, str, zero);
                textView.setText(str);
                break;
            case R.id.button_point:
                if (str.length() > 0 && !dig.isDigit(str.charAt(str.length() - 1)))
                    break;
                if (str.length() == 0) {
                    str += "0.";
                } else {//遍历字符串，防止输入第二个”.“
                    int len = str.length() - 1;
                    while (len >= 0 && dig.isDigit(str.charAt(len))) {
                        len--;
                    }
                    if (len >= 0 && str.charAt(len) == '.') {
                        Toast.makeText(MainActivity.this, "不能再添加小数点！", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        str += ".";
                    }
                }
                textView.setText(str);
                break;
            case R.id.button_add:
                if(str.length() != 0 && str.charAt(str.length()-1)=='.')
                    str+="0"+"+";
                if (str.length() != 0 && (str.charAt(str.length() - 1) == ')' || dig.isDigit(str.charAt(str.length() - 1)))) {//加号补全
                    if (brackets_add) {
                        str += ")+";
                        brackets_add = false;
                    } else
                        str += "+";
                }
                textView.setText(str);
                break;
            case R.id.button_minus:
                if(str.length() != 0 && str.charAt(str.length()-1)=='.')
                    str+="0"+"-";
                if (str.length() == 0 || str.charAt(str.length() - 1) == '(') {
                    minus_add = true;
                    str += "-";
                } else if (str.charAt(str.length() - 1) == ')' || dig.isDigit(str.charAt(str.length() - 1))) {
                    if (brackets_add) {
                        str += ")-";
                        left_brackets_flag--;
                        brackets_add = false;

                    } else {
                        str += "-";
                        minus_add = false;
                    }
                } else if (str.length() - 2 >= 0 &&
                        (dig.isDigit(str.charAt(str.length() - 2)) || str.charAt(str.length() - 2) == ')')) {//负数补全
                    str += "(-";
                    left_brackets_flag++;
                    brackets_add = true;
                    minus_add = true;
                }
                textView.setText(str);
                break;
            case R.id.button_mul:
                if(str.length() != 0 && str.charAt(str.length()-1)=='.')
                    str+="0"+"*";
                if (str.length() != 0 && (str.charAt(str.length() - 1) == ')' || dig.isDigit(str.charAt(str.length() - 1)))) {
                    if (brackets_add) {
                        str += ")*";
                        left_brackets_flag--;
                        brackets_add = false;
                    } else {
                        str += "*";
                    }

                }
                textView.setText(str);
                break;
            case R.id.button_div:
                if(str.length() != 0 && str.charAt(str.length()-1)=='.')
                    str+="0"+"/";
                if (str.length() != 0 && (str.charAt(str.length() - 1) == ')' || dig.isDigit(str.charAt(str.length() - 1)))) {
                    if (brackets_add) {
                        str += ")/";
                        left_brackets_flag--;
                        brackets_add = false;
                    } else
                        str += "/";
                }
                textView.setText(str);
                break;
            case R.id.button_equal:
                if (left_brackets_flag != 0) {
                    dialog.setTitle("注意！");
                    dialog.setMessage("未添加右括号！");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.
                            OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                    break;
                }
                if (str.length() == 0)
                    break;
                if (brackets_add) {
                    str += ")";
                    left_brackets_flag--;
                    brackets_add = false;
                }
                String s = InfixToSuffix.Suffix(InfixToSuffix.Infix(str));
                textView.setText(s);
                str = s;
                break;
            case R.id.button_left_bracket:
                if (str.length() == 0) {
                    str += "(";
                    left_brackets_flag++;
                } else if (str.charAt(str.length() - 1) != ')') {
                    if (!dig.isDigit(str.charAt(str.length() - 1)))
                        str += "(";
                    else {
                        str += "*(";
                        brackets_add = true;
                    }
                    left_brackets_flag++;
                }
                textView.setText(str);
                break;
            case R.id.button_right_bracket:
                if (str.length() != 0 && left_brackets_flag > 0 && (str.charAt(str.length() - 1) == ')'
                        || dig.isDigit(str.charAt(str.length() - 1)))) {
                    if (brackets_add) {
                        brackets_add = false;
                    }
                    str += ")";
                    left_brackets_flag--;
                }
                textView.setText(str);
                break;
            case R.id.button_AC:
                str = "";
                left_brackets_flag = 0;
                brackets_add = false;
                textView.setText(str);
                break;
            case R.id.button_delete:
                if (str.length() == 0 || str.length() == 1) {
                    str = "";
                    left_brackets_flag = 0;
                } else {
                    if (str.charAt(str.length() - 1) == '(') {
                        if (brackets_add)
                            brackets_add = false;
                        left_brackets_flag--;
                    } else if (str.charAt(str.length() - 1) == ')')
                        left_brackets_flag++;
                    str = str.substring(0, str.length() - 1);
                    if (str.charAt(str.length() - 1) == '-' && minus_add)
                        str = str.substring(0, str.length() - 1);
                }
                textView.setText(str);
                break;
        }
    }
}

//dialog.setTitle("This is Dialog");
//                        dialog.setMessage ("Something important.");
//                        dialog.setCancelable(false);
//                        dialog.setPositiveButton ("0K",new DialogInterface.
//                                OnClickListener(){
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        });
//                        dialog. setNegativeButton( "Cancel",new DialogInterface .
//                                OnClickListener( ) {
//                            @Override
//                            public void onClick(DialogInterface dialog,int which) {
//                            }
//                        });
//                        dialog. show() ;
