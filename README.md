# BottomDialog

日常需求中通常都会有这样的需求，点击按钮会从底部弹出一个对话框，选择操作。例如分享或者更换头像弹出对话框。

因此抽空将此功能封装成库，方便以后引用。

## ScreenShot

![图一](https://github.com/SmartCyl/BottomDialog/blob/master/screenshot/bd_001.gif)![图二](https://github.com/SmartCyl/BottomDialog/blob/master/screenshot/bd_002.gif)

![图三](https://github.com/SmartCyl/BottomDialog/blob/master/screenshot/bd_003.gif)![图四](https://github.com/SmartCyl/BottomDialog/blob/master/screenshot/bd_004.gif)

## Import

### Maven

#### Step 1. Add the JitPack repository to your build file
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

#### Step 2. Add the dependency
```
<dependency>
    <groupId>com.github.SmartCyl</groupId>
    <artifactId>BottomDialog</artifactId>
    <version>1.0.4</version>
</dependency>
```

### Or Gradle

#### Step 1. Add the JitPack repository to your build file
#### Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

#### Step 2. Add the dependency
```
dependencies {
    compile 'com.github.SmartCyl:BottomDialog:1.0.4'
}
```

## Usage

#### 当不需要图片时，Item的构造器第一个参数传0 ：new Item(0,"name")

1、使用前3张图中类似的效果直接使用自带的layout文件即可。

```
new SmartDialog().init(context).items(List<Item>).display();
```

### 默认布局 APIs


方法 | 说明 | 默认值
---|---|---
backgroundRes | 设置背景资源，如圆角（backgroundResEnable为true时生效） | R.drawable.shape_round_corner
backgroundResEnable | 是否启用设置背景资源 | true
onItemClick | ReciclerView的单击监听 | -
onItemLongClick | ReciclerView的长按监听 | -
title | 设置标题 | 标题
titleVisible | 是否显示标题 | true
titleGravity | 标题显示位置 | Gravity.CENTER
titleColor | 标题颜色 | #727272
titleSize | 标题字体大小 | 16
cancelVisible | 是否显示取消按钮 | true
cancle | 隐藏Dialog | -
itemOrientation | RecyclerView子项布局方式(垂直[图二]/水平[图一]) | LinearLayout.VERTICAL
recyclerViewOrientation | RecyclerView排布方式(垂直/Grid[图二]) | ORIENTATION_VERTICAL
spanCount | 列数（recyclerViewOrientation为GRID时生效） | 3
adapter | 设置适配器 | SmartAdapter


2、使用自定义布局（如图四）

```
new SmartDialog().init(context).layoutRes(layout).display();
```

### 共用 APIs
方法 | 说明 | 默认值
---|---|---
items | 设置数据源 | -
display | 显示Dialog | -
layoutRes | 设置自定义布局 | -
dialogHeight | 设置Dialog高度 | 包裹内容
dialogWidth | 设置Dialog宽度 | 包裹内容
padding(padding) | 设置内边距 | 30
padding(l,t,r,b) | 设置内边距 | 30
gravity | Dialog的位置 | Gravity.BOTTOM
animEnable | 是否启用Dialog从底部弹出的动画 | true
animDuration | Dialog从底部弹出动画持续时间 | 500ms
cancelableOutside | 点击Dialog外部是否可隐藏Dialog | true
onOutsideClick | 点击Dialog外部区域事件监听（用于点击外部后判断做什么操作） | -
dimAmount | 设置阴影部分的灰暗度 | 0.5F
alpha | 设置Dialog透明度 | 1F
bindViewListener | 绑定视图回调（可用于自定义layout的点击事件监听） | -


## 示例（默认布局）

```
new SmartDialog().init(this)
    .items(items) // 设置数据源
    .title("标题")
    .onItemClick(new OnItemClickListener() {
        @Override
        public void onItemClick(int position, Item item) {
            // 单击回调
        }
    })
    .onItemLongClick(new OnItemLongCli() {
        @Override
        public void onItemLongClick(int position, Item item){
            //　长按回调
        }
    })
    .onOutsideClick(new OutsideClickListener() {
        @Override
        public void outsideClick(boolean isOutside, BaseSmartDialog dialog) {
            dialog.cancle(); // 使对话框消失
            // 点击外部区域监听                 
        }
    })
    .backgroundResEnable(true)
    .animEnable(false)
    .cancelVisible(true)
    .titleVisible(true)
    .cancelableOutside(true)
    .titleGravity(Gravity.CENTER)
    .gravity(Gravity.CENTER)
    .titleColor(R.color.colorAccent)
    .titleSize(20)
    .padding(20)
    .itemOrientation(LinearLayout.VERTICAL)
    .animDuration(400)
    .display();
```

## 示例（自定义布局）

```
new SmartDialog().init(this)
    .items(items) // 设置数据源
    .onOutsideClick(new OutsideClickListener() {
        @Override
        public void outsideClick(boolean isOutside, BaseSmartDialog dialog) {
            dialog.cancle(); // 使对话框消失
            // 点击外部区域监听                 
        }
    })
    .bindViewListener(new BindViewListener() {
        @Override
        public void bind(View dialogView, final BaseSmartDialog dialog) {
            // xxx为自定义layout的id
            dialogView.findViewById(R.id.xxx).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // do something
                    dialog.cancel();
                }
            });
        }
    });
    .animEnable(false)
    .cancelableOutside(true)
    .gravity(Gravity.CENTER)
    .padding(20)
    .animDuration(400)
    .display();
```

## LICENSE

```
Copyright 2018 SmartCyl

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```