package com.example.a2class_15.newmydaily.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a2class_15.newmydaily.R;
import com.example.a2class_15.newmydaily.vo.DailyListItem;

import java.util.Calendar;
import java.text.SimpleDateFormat;
public class DailyInsertView extends AppCompatActivity {
    EditText inputTitile,inputContent;
    Button insertBtn,cancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_insert_view);

        inputTitile=findViewById(R.id.editText);
        inputContent=findViewById(R.id.editText2);
        insertBtn=findViewById(R.id.button2);
        cancleBtn=findViewById(R.id.button3);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

                DailyListItem dailyListItem =new DailyListItem();
                dailyListItem.setTitle(inputTitile.getText().toString());
                dailyListItem.setContent(inputContent.getText().toString());
                dailyListItem.setDate(format1.format(cal.getTime()));

                DailyListView.myDBHelper.Insert(dailyListItem);
                DailyListView.dailyListAdapter.addItem(dailyListItem);
                DailyListView.dailyListAdapter.notifyDataSetChanged();
                finish();
            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
