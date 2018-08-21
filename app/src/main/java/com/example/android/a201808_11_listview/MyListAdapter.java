package com.example.android.a201808_11_listview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {

    // 圖片資源 id 陣列
    private int[] drawableIds = {
            R.drawable.p01, R.drawable.p02, R.drawable.p03, R.drawable.p04, R.drawable.p05,
            R.drawable.p06, R.drawable.p07, R.drawable.p08, R.drawable.p09, R.drawable.p10,
            R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15,
            R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19, R.drawable.p20
    };

    private Activity activity;

    public MyListAdapter(Activity activity){
        this.activity = activity;
    }

    // getter
    public int[] getDrawableIds(){
        return drawableIds;
    }


    @Override
    public int getCount() {
        return 20;      // 項目 20 個 （如果返回 0 表示沒有資料可提供）
    }

    @Override
    public Object getItem(int i) {  // 結合資料庫才會用到
        return null;
    }

    @Override
    public long getItemId(int i) { // 結合資料庫才會用到
        return 0;
    }

    @Override           // i 代表目前 ListView 要索取的是第幾項
    public View getView(int i, View view, ViewGroup viewGroup) {

        // 跟 Activity 借打氣筒，將 Layout 灌入 View
        View v = activity.getLayoutInflater().inflate(R.layout.layout, null);

        TextView m_tv_itemId = v.findViewById(R.id.tv_itemId);
        ImageView m_iv_itemImage = v.findViewById(R.id.iv_item);

        m_tv_itemId.setText(String.valueOf(i));
        m_iv_itemImage.setImageResource(drawableIds[i]);

        return v;
    }
}
