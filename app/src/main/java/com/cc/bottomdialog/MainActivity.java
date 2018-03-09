package com.cc.bottomdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.cc.library.BaseSmartDialog;
import com.cc.library.OnItemClickListener;
import com.cc.library.OnItemLongClickListener;
import com.cc.library.SmartDialog;
import com.cc.library.WindowUtils;

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
                        .recyclerViewOrientation(BaseSmartDialog.ORIENTATION_GRID).spanCount(4)
                        .items(items).dialogHeight(WindowUtils.getWindowHeight(MainActivity.this) / 2)
                        .onItemClick(new OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Toast.makeText(MainActivity.this, "短击" + position, Toast.LENGTH_SHORT).show();
                            }
                        }).onItemLongClick(new OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(int position) {
                        Toast.makeText(MainActivity.this, "长按" + position, Toast.LENGTH_SHORT).show();
                    }
                }).backgroundResEnable(false).cancelVisible(true).titleVisible(false).padding(0, 0, 0, 0)
                        .display().animDuration(100);
            }
        });
    }
}
