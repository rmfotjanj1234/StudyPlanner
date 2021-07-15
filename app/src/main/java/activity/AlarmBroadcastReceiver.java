package activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("####", "AlarmBroadcastReceiver 받음");
        Intent alarmIntentServiceIntent = new Intent(context, AlarmIntentService.class);
        alarmIntentServiceIntent.putExtra("id", intent.getIntExtra("id", 1));
        context.startService(alarmIntentServiceIntent);
    }
}