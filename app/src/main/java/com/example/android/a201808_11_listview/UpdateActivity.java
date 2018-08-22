package com.example.android.a201808_11_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UpdateActivity extends AddActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init(){
        // 取得 MainActivity 傳來 寵物序列化資料
        Intent intent = getIntent();
        Pet pet = (Pet) intent.getSerializableExtra(MainActivity.PET_KEY);

        // 將資料顯示 (下面所使用的欄位是繼承來的，在父類別 AddActivity 宣告欄位需使用 protected)
        m_ed_id.setText(pet.getId());
        m_ed_name.setText(pet.getName());
        drawableId = pet.getDrawableId();
        m_ib_imageButton.setImageResource(drawableId);
    }

    @Override
    public void click(View view) {
        switch (view.getId()){
            case R.id.btn_finish:
                // 建立寵物資料
                id = m_ed_id.getText().toString();
                name = m_ed_name.getText().toString();
                Pet pet = new Pet (id, name, drawableId);

                // 更新 Intent 的寵物資料
                Intent intent = getIntent();
                intent.putExtra(MainActivity.PET_KEY, pet);

                setResult(RESULT_OK, intent);
                break;

            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }




}
