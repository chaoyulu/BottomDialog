# **BottomDialog**

日常需求中通常都会有这样的需求，点击按钮会从底部弹出一个对话框，选择操作。例如分享或者更换头像弹出对话框。

因此抽空将此功能封装成库，方便以后引用。

## **ScreenShot**

![image](https://github.com/SmartCyl/BottomDialog/blob/master/screenshot/bd_001.gif)![image](https://github.com/SmartCyl/BottomDialog/blob/master/screenshot/bd_002.gif)

![image](https://github.com/SmartCyl/BottomDialog/blob/master/screenshot/bd_003.gif)![image](https://github.com/SmartCyl/BottomDialog/blob/master/screenshot/bd_004.gif)

## **Import**

### **Maven**

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

### **Or Gradle**

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

## **Usage**
