package com.example.android.a201808_11_listview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    private final String TAG = this.getClass().getSimpleName();     // 取得類別名稱
    private static final String FILENAME = "UseAnswers.data";       // 存檔名稱


    public static final String PET_KEY = "pet";
    public static final String LIST_KEY = "list";

    private static final int 新增請求碼 = 0;
    private static final int 更新請求碼 = 2;

    private int itemIndex;          // 修改的是第幾項

    private ListView m_listView;
    private MainListAdapter m_adapter;

    // List 介面不是可序列化，ArrayList 才可序列化
    private ArrayList<Pet> m_list = new ArrayList<>();   // 存放寵物物件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 新增請求碼);
            }
        });

        if(savedInstanceState != null){
            m_list = (ArrayList)savedInstanceState.getSerializable(LIST_KEY);
        } else {
            reStoreData();
        }

        init();         // 初始化

    }

    private void init(){
        m_listView = findViewById(R.id.lv_item);
        m_adapter = new MainListAdapter(this, m_list);

        m_listView.setEmptyView(findViewById(R.id.tv_empty));   // 設定
        m_listView.setAdapter(m_adapter);                       // 設定資料轉接
        m_listView.setOnItemClickListener(this);                // 由 MainActivity 自己負責處理項目點選
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(LIST_KEY, m_list);
        super.onSaveInstanceState(outState);
    }





    public void saveData(){

        // openFileOutput() 繼承自 Context，Activity 繼承自 Context
        try{
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);  // MODE_PRIVATE 私有檔案，禁止其他 App 使用
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(m_list);
            oos.close();
        } catch (IOException e){
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }

    }

    public void reStoreData(){
        // openFileInput() 繼承自 Context，Activity 繼承自 Context
        try{
            FileInputStream fis = openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            m_list = (ArrayList) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e){
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 新增請求碼 && resultCode == RESULT_OK){

            Bundle bundle = data.getExtras();

            String id = bundle.getString(AddActivity.KEY_ID);
            String name = bundle.getString(AddActivity.KEY_NAME);
            int drawableId = bundle.getInt(AddActivity.KEY_DRAWABLE_ID);

            Pet pet = new Pet(id, name, drawableId);    // 建立寵物資料庫
            m_list.add(pet);                            // 加入陣列
            m_adapter.notifyDataSetChanged();           // 通知 ListView 資料發生異動，需更新
        } else if (requestCode == 更新請求碼 && resultCode == RESULT_OK){
            // 取得 Intent 傳來寵物序列化資料
            Pet pet = (Pet) data.getSerializableExtra(MainActivity.PET_KEY);
            // 重新設定 陣列中的寵物資料
            m_list.set(itemIndex, pet);
            m_adapter.notifyDataSetChanged();
        }


    }





    @Override                       // 想要 .remove(index) 需改為 final int index
    public void onItemClick(AdapterView<?> adapterView, View view, final int index, long l) {
        String s = "第" + index + "項 被點選了";
        Log.d("MainActivity", s);

        // 記錄目前點選的項目
        itemIndex = index;

        new AlertDialog.Builder(this)
                .setMessage("請選擇動作")
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                        intent.putExtra(MainActivity.PET_KEY, m_list.get(index));
                        startActivityForResult(intent, 更新請求碼);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton("刪除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_list.remove(index);               // 刪除陣列中的 index 項目
                        m_adapter.notifyDataSetChanged();   // 通知 ListView 畫面需要刷新
                    }
                })
                .show();
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
