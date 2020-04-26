package com.example.dialler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FastDial extends AppCompatActivity {
    Bundle extras;
    String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_dial);

        extras = getIntent().getExtras();

        EditText number_text = findViewById(R.id.editText_number);
        EditText name_text = findViewById(R.id.editText_name);

        if(extras != null)
        {
            number_text.setText(extras.getString("number"));
            name_text.setText(extras.getString("name"));
            index = extras.getString("index");
            System.out.println("-------------- Index: " + index);
        }

    }

    public void updateFastDial(View view) {
        EditText number_text = findViewById(R.id.editText_number);
        EditText name_text = findViewById(R.id.editText_name);

        String phone_number = number_text.getText().toString();
        String name = name_text.getText().toString();

        if(index != null)
        {
            if(index.equals("1"))
            {
                MainActivity.setFast_dial_1(phone_number, name);
                MainActivity.settext1();
            }
            if(index.equals("2"))
            {
                MainActivity.setFast_dial_2(phone_number, name);
                MainActivity.settext2();
            }
            if(index.equals("3"))
            {
                MainActivity.setFast_dial_3(phone_number, name);
                MainActivity.settext3();
            }
        }
        else
        {
            MainActivity.setFast_dial_1(phone_number, name);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Pretende guardar as definições?");
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cancel, do nothing
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
