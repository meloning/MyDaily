package com.example.a2class_15.newmydaily.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a2class_15.newmydaily.R;
import com.example.a2class_15.newmydaily.vo.DailyListItem;

public class DailyUpdateView extends AppCompatActivity implements DataSettable{
    EditText inputTitile,inputContent;
    Button checkBtn,cancleBtn;

    DailyListItem dailyListItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_update_view);

        inputTitile=findViewById(R.id.updateTitle);
        inputContent=findViewById(R.id.updateContent);

        checkBtn=findViewById(R.id.checkBtn);
        cancleBtn=findViewById(R.id.cancleBtn);

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailyListItem.setTitle(inputTitile.getText().toString());//setDailyView로 인해 이미 객체정보가 담겨져있음.
                dailyListItem.setContent(inputContent.getText().toString());

                DailyListView.myDBHelper.Update(dailyListItem);
                DailyListView.dailyListAdapter.updateItem(dailyListItem);

                Intent it = new Intent(getApplicationContext(),DailyListView.class);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //넘어온 intent의 부가 데이터를 처리
        setDailyDataView(getIntent().getExtras());
    }

    public void setDailyDataView(Bundle bundle){
        dailyListItem=(DailyListItem) bundle.getSerializable("dailyItem");

        inputTitile.setText(dailyListItem.getTitle());
        inputContent.setText(dailyListItem.getContent());
    }
}
