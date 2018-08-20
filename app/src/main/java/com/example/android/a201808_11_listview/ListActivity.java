package com.example.android.a201808_11_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity
            implements AdapterView.OnItemClickListener{

    public static final String KEY = "圖片id";

    private ListView m_lv_item;
    private MyListAdapter m_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();
    }

    private void init(){
        m_lv_item = findViewById(R.id.lv_item);
        m_lv_item.setEmptyView(findViewById(R.id.tv_empty));

        m_Adapter = new MyListAdapter(this);
        m_lv_item.setAdapter(m_Adapter);      //設定 資料轉接
        m_lv_item.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String s = "第" + i + "項 被點選了";
        Log.d("ListActivity", s);

        Intent intent = getIntent();
        int[] m_drawableIds = m_Adapter.m_getDrawableIds();
        intent.putExtra(KEY, m_drawableIds[i]);
        setResult(RESULT_OK, intent);

        finish();
    }
}
