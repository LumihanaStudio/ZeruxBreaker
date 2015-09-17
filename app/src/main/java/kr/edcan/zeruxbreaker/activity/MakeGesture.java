package kr.edcan.zeruxbreaker.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.BatteryManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import kr.edcan.zeruxbreaker.R;
import kr.edcan.zeruxbreaker.utils.TeleListener;

public class MakeGesture extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floatingActionButton;
    RelativeLayout r1, r2, r3, r4, layouts[];
    TextView t1,t2,t3,t4, textViews[];
    int currentState = 0, status, batteryLevel, batteryScale, icons[];
    IntentFilter filter;
    Intent batteryStatus;
    boolean isCharging;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_gesture);
        setDefault();
        setActionBar();
        setBackgroundColor();
        setWhen();
    }

    private void setWhen() {
        new MaterialDialog.Builder(MakeGesture.this)
                .title("언제요?")
                .items(R.array.when)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                        t1.setText(charSequence);
                        currentState++;
                        setBackgroundColor();
                        setType();
                    }
                })
                .show();
    }

    private void setType(){
        new MaterialDialog.Builder(MakeGesture.this)
                .title("어떻게요?")
                .items(R.array.how)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                        t2.setText(charSequence);
                        icon.setImageResource(icons[i]);
                        currentState++;
                        setBackgroundColor();
                    }
                })
                .show();
    }
    private void Listen(){
        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        TelephonyMgr.listen(new TeleListener(), PhoneStateListener.LISTEN_CALL_STATE);
        filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = getApplicationContext().registerReceiver(null, filter);
        status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        batteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        batteryScale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = batteryLevel / (float)batteryScale;
        int percent = (int)(batteryPct*100);
        Toast.makeText(MakeGesture.this, percent+", "+isCharging, Toast.LENGTH_SHORT).show();
    }
    private void setDefault() {
        icon = (ImageView) findViewById(R.id.make_image);
        icons = new int[] {R.drawable.ic_gmake_shake, R.drawable.ic_gmake_turn, R.drawable.ic_gmake_zodo, R.drawable.ic_gmake_mic};
        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_button);
        floatingActionButton.setIcon(R.drawable.btn_make);
        floatingActionButton.setColorNormal(Color.parseColor("#1E87E4"));
        floatingActionButton.setColorPressed(Color.parseColor("#1766BF"));
        r1 = (RelativeLayout) findViewById(R.id.r1);
        r2 = (RelativeLayout) findViewById(R.id.r2);


        r3 = (RelativeLayout) findViewById(R.id.r3);
        r4 = (RelativeLayout) findViewById(R.id.r4);
        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        r4.setOnClickListener(this);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        textViews = new TextView[] {t1,t2,t3,t4};
        layouts = new RelativeLayout[]{r1, r2, r3, r4};
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void setBackgroundColor(){
        for(int i=0;i<4;i++){
            if(i==currentState) {
                layouts[i].setBackgroundResource(R.drawable.new_gesture_bg_selected);
                textViews[i].setTextColor(Color.parseColor("#FFFFFF"));
            }
            else {
                layouts[i].setBackgroundResource(R.drawable.new_gesture_bg_not_selected);
                textViews[i].setTextColor(getResources().getColor(R.color.grayFont));
            }
        }
    }
    public void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1E87E4")));
        actionBar.setTitle("새로운 제스처 등록");
        actionBar.setElevation(0);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.r1:
                setWhen();
                currentState=0;
                setBackgroundColor();
                break;
            case R.id.r2:
                setType();
                currentState=1;
                setBackgroundColor();
                break;
            case R.id.r3:
                break;
            case R.id.r4:
                break;
        }
    }
}
