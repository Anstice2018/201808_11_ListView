package com.example.android.a201808_11_listview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainListAdapter extends BaseAdapter{

    private Activity activity;
    private List<Pet> list;

    public MainListAdapter(Activity activity, List<Pet> list){
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // 跟 Activity 借打氣筒，將 layout 灌入 View
        View v = activity.getLayoutInflater().inflate(R.layout.listview2_layout, null);

        TextView m_tv_itemId = v.findViewById(R.id.tv_itemId);
        TextView m_tv_itemName = v.findViewById(R.id.tv_itemName);
        ImageView m_iv_imageView = v.findViewById(R.id.iv_item);

        Pet pet = list.get(i);      // 取得陣列第 i 項物件

        m_tv_itemId.setText(pet.getId());
        m_tv_itemName.setText(pet.getName());
        m_iv_imageView.setImageResource(pet.getDrawableId());

        return v;
    }
}
