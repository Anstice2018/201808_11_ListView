package com.example.android.a201808_11_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AddActivity extends AppCompatActivity {

    private static final int 圖片請求 = 1;

    private ImageButton m_ib_imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        m_ib_imageButton= findViewById(R.id.ib_imageButton);
    }

    public void clickPick (View view){
        Intent intent = new Intent(this,ListActivity.class);
        startActivityForResult(intent, 圖片請求);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 圖片請求 && resultCode == RESULT_OK){
            int resId = intent.getIntExtra(ListActivity.KEY, -1);   // 取得圖片id
            if(resId != -1){
                m_ib_imageButton.setImageResource(resId);
            }
        }
    }



    public void clickFinish(View view){

    }
    public void clickCancel(View view){

    }
}
