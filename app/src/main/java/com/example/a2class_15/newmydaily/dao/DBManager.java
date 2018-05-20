package com.example.a2class_15.newmydaily.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.a2class_15.newmydaily.vo.DailyListItem;

import java.util.ArrayList;

/**
 * @author JUNSU
 * @content DB생성, 연결, 테이블 제어 가능한 SQLiteOpenHelper
 */
public class DBManager extends SQLiteOpenHelper {
    SQLiteDatabase sqlDB;
    Cursor cursor;
    private static DBManager instance;
    public static DBManager getInstance(Context context){
        if(instance == null) {
            synchronized (DBManager.class) {
                if(instance == null)
                    instance = new DBManager(context);
            }
        }

        return instance;
    }

    //생성자를 통한 DB생성, 연결
    public DBManager(Context context) {
        super(context, "testDB4", null, 3);
        Log.d("SQLiteDBCreate","true");
    }
    //테이블 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE daily(" +
                        "num Integer PRIMARY KEY AUTOINCREMENT," +
                        "d_title varchar(20)," +
                        "d_content varchar(2000)," +
                        "d_date date);");
        Log.d("SQLiteTableCreate","true");
    }
    //테이블 삭제 or 다시생성
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS daily");
        Log.d("SQLiteTableDrop","true");
        onCreate(db);
    }

    public ArrayList<DailyListItem> getAllSelect(){
        ArrayList<DailyListItem> arrayList = new ArrayList<DailyListItem>();
        DailyListItem dailyListItem = null;

        sqlDB=instance.getWritableDatabase();
        Log.d("SQLite",sqlDB.toString());

        cursor=sqlDB.rawQuery("SELECT * FROM daily;",null);
        Log.d("SQLiteCursor",cursor.toString());

        while(cursor.moveToNext()){
            dailyListItem = new DailyListItem();
            dailyListItem.setNum(cursor.getString(0));
            dailyListItem.setTitle(cursor.getString(1));
            dailyListItem.setContent(cursor.getString(2));
            dailyListItem.setDate(cursor.getString(3));

            // 값 로그 체크
            Log.d("SQLiteCursorData1",cursor.getString(0));
            Log.d("SQLiteCursorData2",cursor.getString(1));
            Log.d("SQLiteCursorData3",cursor.getString(2));
            Log.d("SQLiteCursorData4",cursor.getString(3));

            arrayList.add(dailyListItem);
        }

        cursor.close();
        sqlDB.close();
        return arrayList;
    }

    public void Insert(DailyListItem dailyListItem){
        sqlDB=instance.getWritableDatabase();
        sqlDB.execSQL("INSERT INTO daily(d_title,d_content,d_date) " +
                "VALUES('"+dailyListItem.getTitle()+"', '"+dailyListItem.getContent()+"', '"+dailyListItem.getDate()+"');");
        sqlDB.close();
        Log.d("SQLiteInsert","true");
    }

    public void Update(DailyListItem dailyListItem){
        sqlDB=instance.getWritableDatabase();
        sqlDB.execSQL("UPDATE daily SET d_title='"+dailyListItem.getTitle()+"'," +
                " d_content='"+dailyListItem.getContent()+"' " +
                "WHERE num='"+dailyListItem.getNum()+"';");
        sqlDB.close();
        Log.d("SQLiteUpdate","true");
    }

    public void Delete(String num){
        sqlDB=instance.getWritableDatabase();
        sqlDB.execSQL("DELETE FROM daily WHERE num='"+num+"';");
        sqlDB.close();
        Log.d("SQLiteDelete","true");
    }
}
