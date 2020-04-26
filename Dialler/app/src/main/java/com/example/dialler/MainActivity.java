package com.example.dialler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String number_to_dial = "";

    public static Button button1;
    public static Button button2;
    public static Button button3;

    public static String fast_dial_1 = "";
    public static String fast_dial_1_text = "";
    public static String fast_dial_2 = "";
    public static String fast_dial_2_text = "";
    public static String fast_dial_3 = "";
    public static String fast_dial_3_text = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setScreen();
    }

    public void setScreen() {
        button1 = findViewById(R.id.fastdial1);
        button1.setText((fast_dial_1_text.equals("")) ? "Not defined" : fast_dial_1_text);
        button1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getApplicationContext(), FastDial.class);
                i.putExtra("number", fast_dial_1);
                i.putExtra("name", fast_dial_1_text);
                i.putExtra("index", "1");
                startActivity(i);
                return true;
            }
        });

        button2 = findViewById(R.id.fastdial2);
        button2.setText((fast_dial_2_text.equals("")) ? "Not defined" : fast_dial_2_text);
        button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getApplicationContext(), FastDial.class);
                i.putExtra("number", fast_dial_2);
                i.putExtra("name", fast_dial_2_text);
                i.putExtra("index", "2");
                startActivity(i);
                return true;
            }
        });

        button3 = findViewById(R.id.fastdial3);
        button3.setText((fast_dial_3_text.equals("")) ? "Not defined" : fast_dial_3_text);
        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getApplicationContext(), FastDial.class);
                i.putExtra("number", fast_dial_3);
                i.putExtra("name", fast_dial_3_text);
                i.putExtra("index", "3");
                startActivity(i);
                return true;
            }
        });

        Button button_clear = findViewById(R.id.clear);
        button_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView editText = findViewById(R.id.input);

                number_to_dial = "";
                editText.setText(number_to_dial.toString());
                return true;
            }
        });
    }

    public void numberClicked(View view) {
        TextView editText = findViewById(R.id.input);

        switch (view.getId()) {
            case R.id.dial_0:
                number_to_dial += "0";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.dial_1:
                number_to_dial += "1";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.dial_2:
                number_to_dial += "2";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.dial_3:
                number_to_dial += "3";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.dial_4:
                number_to_dial += "4";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.dial_5:
                number_to_dial += "5";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.dial_6:
                number_to_dial += "6";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.dial_7:
                number_to_dial += "7";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.dial_8:
                number_to_dial += "8";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.dial_9:
                number_to_dial += "9";
                editText.setText(number_to_dial.toString());
                break;
            case R.id.clear:
                if (number_to_dial.length() > 0) {
                    number_to_dial = number_to_dial.substring(0, number_to_dial.length() - 1);
                    editText.setText(number_to_dial.toString());
                }
                break;
            case R.id.fastdial1:
                number_to_dial = fast_dial_1;
                editText.setText(number_to_dial.toString());
                break;
            case R.id.fastdial2:
                number_to_dial = fast_dial_2;
                editText.setText(number_to_dial.toString());
                break;
            case R.id.fastdial3:
                number_to_dial = fast_dial_3;
                editText.setText(number_to_dial.toString());
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void callNumber(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CALL_PHONE
            }, 1);
        }
        else
        {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number_to_dial)));
        }
    }

    public static void setFast_dial_1(String fast_dial_1, String name) {
        MainActivity.fast_dial_1 = fast_dial_1;
        MainActivity.fast_dial_1_text = name;
    }

    public static void setFast_dial_2(String fast_dial_2, String name) {
        MainActivity.fast_dial_2 = fast_dial_2;
        MainActivity.fast_dial_2_text = name;
    }

    public static void setFast_dial_3(String fast_dial_3, String name) {
        MainActivity.fast_dial_3 = fast_dial_3;
        MainActivity.fast_dial_3_text = name;
    }

    public static void settext3() {
        button3.setText((fast_dial_3_text.equals("")) ? "Not defined" : fast_dial_3_text);
    }
    public static void settext2() {
        button2.setText((fast_dial_2_text.equals("")) ? "Not defined" : fast_dial_2_text);
    }
    public static void settext1() {
        button1.setText((fast_dial_1_text.equals("")) ? "Not defined" : fast_dial_1_text);
    }


}
