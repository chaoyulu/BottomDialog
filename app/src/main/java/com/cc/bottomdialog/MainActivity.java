package com.cc.bottomdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cc.library.BaseSmartDialog;
import com.cc.library.BindViewListener;
import com.cc.library.Item;
import com.cc.library.OnItemClickListener;
import com.cc.library.OutsideClickListener;
import com.cc.library.SmartDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<Item> items = new ArrayList<>();
        for (int i = 1; i < 24; i++) {
            items.add(new Item(R.mipmap.ic_launcher, "列表项" + i));
        }

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SmartDialog().init(MainActivity.this)
                        .recyclerViewOrientation(BaseSmartDialog.ORIENTATION_GRID).spanCount(5)
                        .items(items)
//                        .dialogHeight(WindowUtils.getWindowHeight(MainActivity.this))
                        .onItemClick(new OnItemClickListener() {
                            @Override
                            public void onItemClick(int position, Item item) {
                                Toast.makeText(MainActivity.this, "短击 " + item.getName(), Toast.LENGTH_SHORT).show();
                            }
                        })
//                        .layoutRes(R.layout.dialog_test1)
                        .title("广告")
                        .backgroundResEnable(false)
                        .animEnable(true)
                        .cancelVisible(false)
                        .titleVisible(true)
                        .cancelableOutside(true)
                        .titleGravity(Gravity.CENTER)
                        .gravity(Gravity.CENTER)
                        .titleColor(R.color.colorAccent)
                        .titleSize(20)
                        .padding(0, 0, 0, 0).itemOrientation(LinearLayout.VERTICAL)
                        .display().animDuration(400)
                        .onOutsideClick(new OutsideClickListener() {
                            @Override
                            public void outsideClick(boolean isOutside, BaseSmartDialog dialog) {
                                Toast.makeText(MainActivity.this, "点击了外部 " + isOutside, Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        })
                        .bindViewListener(new BindViewListener() {
                            @Override
                            public void bind(View dialogView, final BaseSmartDialog dialog) {
                            }
                        });
            }
        });
    }
}
