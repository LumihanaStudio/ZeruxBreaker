package kr.edcan.zeruxbreaker.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import kr.edcan.zeruxbreaker.R;
import kr.edcan.zeruxbreaker.utils.MyGesture_Adapter;
import kr.edcan.zeruxbreaker.utils.MyGesture_Data;

public class MyGesture extends AppCompatActivity {

    MyGesture_Adapter adapter;
    ArrayList<MyGesture_Data> arrayList;
    ListView listView;
    int icons[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gesture);
        setActionBar();
        setListView();
    }

    private void setListView() {
        icons = new int[] {R.drawable.ic_shake, R.drawable.ic_turn, R.drawable.ic_zodo, R.drawable.ic_mic};
        listView = (ListView) findViewById(R.id.my_gesture_listview);
        arrayList= new ArrayList<>();
        arrayList.add(new MyGesture_Data(icons[0], "흔들어 카카오톡 열기", "Luminon Canoness"+"님이 제작함", true));
        adapter = new MyGesture_Adapter(MyGesture.this, arrayList);
        listView.setAdapter(adapter);
    }

    public void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1E87E4")));
        actionBar.setTitle("내 제스쳐 관리");
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
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
