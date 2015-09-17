package kr.edcan.zeruxbreaker.utils;

import android.content.Context;

/**
 * Created by kotohana5706 on 15. 7. 13.
 */
public class MyGesture_Data {

    int icon;
    String title, content;
    boolean isEnabled;

    public MyGesture_Data(int icon, String title, String content, boolean isEnabled) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.isEnabled = isEnabled;
    }

    public int getIcon(){
        return icon;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public boolean getisEnabled(){
        return isEnabled;
    }

}
