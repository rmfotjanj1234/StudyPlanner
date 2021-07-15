package fragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.example.studyplanner.R;

import java.util.Calendar;

import activity.AlarmBroadcastReceiver;

public class SearchFragment extends Fragment {
    private Button button;
    TimePicker timePicker;
    int hour, minute;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        timePicker = view.findViewById(R.id.tp_timepicker);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(onClickListener);

        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    SharedPreferences pref;
                    SharedPreferences.Editor editor;

                    pref = getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
                    editor = pref.edit();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        hour=timePicker.getHour();
                        minute=timePicker.getMinute();
                    }

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    editor.putLong("MyInt01", calendar.getTimeInMillis());
                    editor.apply();
                    editor.putLong("MyInt02", calendar.getTimeInMillis() + 60 * 1000);
                    editor.apply();


                    createNotificationChannel();
                    alarmBroadcastReceiver();
                    break;
            }
        }
    };
    public void alarmBroadcastReceiver() {
        SharedPreferences pref;
        SharedPreferences.Editor editor;

        pref = getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        Intent alarmBroadcastReceiverintent = new Intent(getContext(), AlarmBroadcastReceiver.class);
        alarmBroadcastReceiverintent.putExtra("id", 123);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, alarmBroadcastReceiverintent, 0);

        AlarmManager alarmMgr = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.

        alarmMgr.set(AlarmManager.RTC_WAKEUP, pref.getLong("MyInt01", 0), pendingIntent);
    }

    //    API26(Oreo)+ notification 작동을 위해서는 channel을 생성해야 함
    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "알림설정에서의 제목";
            String description = "Oreo Version 이상을 위한 알림(알림설정에서의 설명)";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("channel_id", name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(getActivity(), c);
        startActivityForResult(intent, 0);
    }
}
