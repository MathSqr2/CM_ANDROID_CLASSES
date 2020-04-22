package com.example.dialler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String number_to_dial = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void numberClicked(View view) {
        EditText editText = findViewById(R.id.input);

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
                number_to_dial = number_to_dial.substring(0, number_to_dial.length() - 1);
                editText.setText(number_to_dial.toString());
                break;
        }
    }

    public void callNumber(View view) {
        System.out.println("CALL NUMBER: "+number_to_dial.toString());
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number_to_dial, null)));
    }

}
