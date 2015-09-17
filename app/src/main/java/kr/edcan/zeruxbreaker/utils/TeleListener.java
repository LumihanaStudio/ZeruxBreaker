package kr.edcan.zeruxbreaker.utils;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import kr.edcan.zeruxbreaker.activity.MakeGesture;

/**
 * Created by Junseok on 2015-09-11.
 */
public class TeleListener extends PhoneStateListener {
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                // CALL_STATE_IDLE;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                // CALL_STATE_OFFHOOK;
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                // CALL_STATE_RINGING
                break;
            default:
                break;
        }
    }
}