package kr.edcan.zeruxbreaker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout my_gesture, download, make_gesture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1E87E4")));
        actionbar.setCustomView(R.layout.actionbar_layout);
        setDefault();
    }

    private void setDefault() {
        my_gesture = (RelativeLayout) findViewById(R.id.main_my_gesture);
        download = (RelativeLayout) findViewById(R.id.main_download);
        make_gesture = (RelativeLayout) findViewById(R.id.main_make_gesture);
        my_gesture.setOnClickListener(this);
        download.setOnClickListener(this);
        make_gesture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_my_gesture:
                startActivity(new Intent(getApplicationContext(), MyGesture.class));
                break;
            case R.id.main_download:
                startActivity(new Intent(getApplicationContext(), DownloadGesture.class));
                break;
            case R.id.main_make_gesture:
                startActivity(new Intent(getApplicationContext(), MakeGesture.class));
                break;
        }
    }
}