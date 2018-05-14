# 运行截图
![image](https://github.com/breakadmin/Face/blob/master/0.png)
![image](https://github.com/breakadmin/Face/blob/master/1.png)
# 使用
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.breakadmin:Face:2.2.1'
	}
Dmeo 布局


  <?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    >

    <EditText
        android:id="@+id/Text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@android:drawable/editbox_background_normal"
        android:ems="10"
        android:hint="请输入"
        android:inputType="textPersonName" />

    <TextView
        android:id="@+id/emojitext"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true" />
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        />



    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:id="@+id/container"
        android:orientation="vertical">


    </RelativeLayout>


</RelativeLayout>



MainActivity



public class MainActivity extends AppCompatActivity {


    EditText Text;
    ImageView imageView;
    TextView emoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Text = (EditText) findViewById(R.id.Text);
        imageView = (ImageView) findViewById(R.id.imageView);

        emoji = findViewById(R.id.emojitext);

        Text.setSelection(Text.length());

        FaceFragment fragmentA=new FaceFragment();
        fragmentA.bind(Text,emoji);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentA).commit();

    }

}

