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
        m_Adapter = new MyListAdapter(this);

        m_lv_item.setEmptyView(findViewById(R.id.tv_empty));    // 設定
        m_lv_item.setAdapter(m_Adapter);                        // 設定 資料轉接
        m_lv_item.setOnItemClickListener(this);                 // 設定 處理點選項目 外包商
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String s = "第" + i + "項 被點選了";
        Log.d("ListActivity", s);

        Intent intent = getIntent();
        int[] drawableIds = m_Adapter.getDrawableIds();     // Adapter 需提供 getter
        intent.putExtra(KEY, drawableIds[i]);               // 放入所選圖片id
        setResult(RESULT_OK, intent);                       // 設定結果

        finish();                                           // 結束目前畫面，返回前一個畫面
    }
}
