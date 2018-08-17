package com.example.android.a201808_11_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity
            implements AdapterView.OnItemClickListener{

    private ListView m_lv_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();
    }

    private void init(){
        m_lv_item = findViewById(R.id.lv_item);
        m_lv_item.setEmptyView(findViewById(R.id.tv_empty));
        m_lv_item.setAdapter(new MyListAdapter(this));      //設定 資料轉接
        m_lv_item.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
