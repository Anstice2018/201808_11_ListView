package com.example.android.a201808_11_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddActivity extends AppCompatActivity {

    // 寵物資料寄放 Intent 所需要的 KEY
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DRAWABLE_ID = "drawableId";

    public static final int 未選圖預設值 = -1;

    // 寵物資料
    private String m_id;
    private String m_name;
    private int m_drawableId;

    private static final int 圖片請求 = 1;      // 請求圖片id

    private ImageButton m_ib_imageButton;
    private EditText m_ed_id;
    private EditText m_ed_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        m_ib_imageButton= findViewById(R.id.ib_imageButton);
        m_ed_id = findViewById(R.id.ed_id);
        m_ed_name = findViewById(R.id.ed_name);
        m_drawableId = 未選圖預設值;
    }

    public void clickPick (View view){
        Intent intent = new Intent(this,ListActivity.class);
        startActivityForResult(intent, 圖片請求);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 圖片請求 && resultCode == RESULT_OK){
            int resId = data.getIntExtra(ListActivity.KEY, 未選圖預設值);  // 取得圖片id
            if(resId != 未選圖預設值){
                m_ib_imageButton.setImageResource(resId);                   // 設定圖片
                m_drawableId = resId;
            }
        }
    }



    public void click(View view){
        switch (view.getId()){
            case R.id.btn_finish:
                m_id = m_ed_id.getText().toString();
                m_name = m_ed_name.getText().toString();
                Intent intent = getIntent();
                intent.putExtra(KEY_ID,m_id);
                intent.putExtra(KEY_NAME, m_name);
                intent.putExtra(KEY_DRAWABLE_ID, m_drawableId);
                setResult(RESULT_OK, intent);
                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }

}
