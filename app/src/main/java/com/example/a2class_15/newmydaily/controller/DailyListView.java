package com.example.a2class_15.newmydaily.controller;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.a2class_15.newmydaily.dao.DBManager;
import com.example.a2class_15.newmydaily.util.DailyListAdapter;
import com.example.a2class_15.newmydaily.R;
import com.example.a2class_15.newmydaily.vo.DailyListItem;

import java.util.ArrayList;

public class DailyListView extends AppCompatActivity{
    ListView listView;
    public static DailyListAdapter dailyListAdapter;
    public static DBManager myDBHelper;

    FloatingActionButton fabtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailylist);

        fabtn=findViewById(R.id.btnFAB);

        listView=findViewById(R.id.listview);

        myDBHelper=DBManager.getInstance(this);

        listView.setAdapter(dailyListAdapter);

        LoadAllSelect();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(),DailyDetailView.class);
                Bundle bundle =new Bundle();
                bundle.putSerializable("dailyItem",(DailyListItem)dailyListAdapter.getItem(position));
                it.putExtras(bundle);
                startActivity(it);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            DailyListItem tempDailyItem;
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tempDailyItem = (DailyListItem)dailyListAdapter.getItem(position);

                //getApplicationContext() AlertDialog Constructor에 넘길시
                //android.view.WindowManager$BadTokenException 오류발생
                //따라서, Class명.this로 context객체로 넘기기!!
                AlertDialog.Builder builder = new AlertDialog.Builder(DailyListView.this);
                builder.setTitle("삭제");
                builder.setMessage(tempDailyItem.getTitle()+"\n" +
                        "정말로 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDBHelper.Delete(tempDailyItem.getNum());
                        dailyListAdapter.deleteItem(tempDailyItem.getNum());
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                // 다이얼로그 생성
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                //setOnItemClickListener와 함께 구현되어있을경우, return true로 각각 이벤트를 제어
                return true;
            }
        });
        fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),DailyInsertView.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        dailyListAdapter.clearItem();

        LoadAllSelect();
    }

    //FLAG_ACTIVITY_SINGLE_TOP의 코드로 인해 전달받은 인텐트의 부가데이터를 이용할때 호출
    //재사용되기 때문에 onCreate() 메소드 호출이 안됨 따라서, onNewIntent()를 따로 호출.
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void LoadAllSelect(){
        ArrayList<DailyListItem> dailyListItems = myDBHelper.getAllSelect();
        for(DailyListItem dailyListItem:dailyListItems){
            dailyListAdapter.addItem(dailyListItem);
        }
        dailyListAdapter.notifyDataSetChanged();
        Log.d("LoadAllSelect","true");
    }


}
