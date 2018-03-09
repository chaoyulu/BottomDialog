package com.cc.bottomdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cc.library.SmartDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<String> items = new ArrayList<>();
        for (int i = 1; i < 110; i++) {
            items.add("列表项" + i);
        }
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SmartDialog().init(MainActivity.this).cancelableOutside(true)
                        .items(items)
                        .recyclerViewOrientation(SmartDialog.ORIENTATION_VERTICAL)
                        .layoutRes(R.layout.smart_default_dialog_layout).display();
            }
        });
    }
}
