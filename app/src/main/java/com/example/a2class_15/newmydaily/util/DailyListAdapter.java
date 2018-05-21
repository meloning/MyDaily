package com.example.a2class_15.newmydaily.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a2class_15.newmydaily.R;
import com.example.a2class_15.newmydaily.vo.DailyListItem;

import java.util.ArrayList;
import java.util.List;

public class DailyListAdapter extends BaseAdapter{
    private List<DailyListItem> dailyListItems;

    public DailyListAdapter(){

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DailyListItem dailyListItem = dailyListItems.get(position);
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dailylistitem, parent, false);
        }

        TextView title = convertView.findViewById(R.id.title);
        TextView date = convertView.findViewById(R.id.date);

        title.setText(dailyListItem.getTitle());
        date.setText(dailyListItem.getDate());

        return convertView;
    }


    public void addItem(DailyListItem dailyListItem){
        dailyListItems.add(dailyListItem);
    }


    public void updateItem(DailyListItem dailyListItem){
        DailyListItem temp;
        for(int i=0;i<getCount();i++){
            temp =dailyListItems.get(i);
            Log.d("temp",temp.toString());
            Log.d("updateDailyListItem",dailyListItem.toString());
            if(temp.getNum().equals(dailyListItem.getNum())){
                dailyListItems.remove(i);
                dailyListItems.add(i,dailyListItem);
                notifyDataSetChanged();
            }
        }
    }


    public void deleteItem(String num){
        DailyListItem temp;
        for(int i=0;i<getCount();i++){
            temp =dailyListItems.get(i);
            Log.d("temp",temp.toString());
            if(temp.getNum().equals(num)){
                dailyListItems.remove(i);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getCount() {
        return (dailyListItems!=null)?dailyListItems.size():0;
    }

    @Override
    public DailyListItem getItem(int position) {
        return dailyListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void clearItem(){
        if(dailyListItems!=null){
            dailyListItems.clear();
        }
    }


}
