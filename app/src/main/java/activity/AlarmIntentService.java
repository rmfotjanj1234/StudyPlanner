package activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.studyplanner.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class AlarmIntentService extends IntentService {
//    public int NOTIFICATION_ID = 1001;

    public AlarmIntentService() {
        super("AlarmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences pref;
        SharedPreferences.Editor editor;

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        int NOTIFICATION_ID = intent.getIntExtra("id", 1001);
        Log.e("####", Integer.toString(NOTIFICATION_ID) + " AlarmIntentService 실행 됨");

        new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        Bitmap mLargeIconForNoti = BitmapFactory.decodeResource(this.getResources(),R.drawable.common_google_signin_btn_icon_dark);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setLargeIcon(mLargeIconForNoti)
                .setContentTitle("알림 타이틀")
                .setContentText("알림 서브텍스트")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());

        Intent alarmBroadcastReceiverintent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
        alarmBroadcastReceiverintent.putExtra("id", 456);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmBroadcastReceiverintent, 0);

        AlarmManager alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, pref.getLong("MyInt02", 0), pendingIntent2);
    }
}