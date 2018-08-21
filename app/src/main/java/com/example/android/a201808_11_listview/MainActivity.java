package com.example.android.a201808_11_listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int 請求 = 0;

    private ListView m_listView;
    private MainListAdapter m_adapter;

    private List<Pet> m_list = new ArrayList<>();   // 存放寵物物件

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
                startActivityForResult(intent, 請求);
            }
        });

        init();         // 初始化

    }

    private void init(){
        m_listView = findViewById(R.id.lv_item);
        m_adapter = new MainListAdapter(this, m_list);

        m_listView.setEmptyView(findViewById(R.id.tv_empty));
        m_listView.setAdapter(m_adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 請求 && resultCode == RESULT_OK){

            Bundle bundle = data.getExtras();

            String id = bundle.getString(AddActivity.KEY_ID);
            String name = bundle.getString(AddActivity.KEY_NAME);
            int drawableId = bundle.getInt(AddActivity.KEY_DRAWABLE_ID);

            Pet pet = new Pet(id, name, drawableId);    // 建立寵物資料庫
            m_list.add(pet);                            // 加入陣列
            m_adapter.notifyDataSetChanged();           // 通知 ListView 資料發生異動，需更新
        }
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
