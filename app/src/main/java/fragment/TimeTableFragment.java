package fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.studyplanner.R;

import java.util.HashMap;
import java.util.Map;

import activity.BottomSheetDialog;

import static com.example.studyplanner.Util.showToast;

public class TimeTableFragment extends Fragment {
    private FragmentActivity myContext;

    public CalendarView calendarView;
    public TextView selectTextView;
    public String selectDate;
    Dialog addDialog;
    TextView[][] textViewList;
    Map<String, String> dayMap;
    Map<String, String> timeMap;
    String day, startTime, finishTime;
    Spinner daySpinner, startTimeSpinner, finishTimeSpinner;

    BottomSheetDialog bottomSheetDialog;

    public TimeTableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time_table, container, false);

        int[][] IDArray = {{R.id.tv00, R.id.tv01, R.id.tv02, R.id.tv03, R.id.tv04, R.id.tv05},
                {R.id.tv10, R.id.tv11, R.id.tv12, R.id.tv13, R.id.tv14, R.id.tv15},
                {R.id.tv20, R.id.tv21, R.id.tv22, R.id.tv23, R.id.tv24, R.id.tv25},
                {R.id.tv30, R.id.tv31, R.id.tv32, R.id.tv33, R.id.tv34, R.id.tv35},
                {R.id.tv40, R.id.tv41, R.id.tv42, R.id.tv43, R.id.tv44, R.id.tv45},
                {R.id.tv50, R.id.tv51, R.id.tv52, R.id.tv53, R.id.tv54, R.id.tv55},
                {R.id.tv60, R.id.tv61, R.id.tv62, R.id.tv63, R.id.tv64, R.id.tv65},
                {R.id.tv70, R.id.tv71, R.id.tv72, R.id.tv73, R.id.tv74, R.id.tv75},
                {R.id.tv80, R.id.tv81, R.id.tv82, R.id.tv83, R.id.tv84, R.id.tv85},
                {R.id.tv90, R.id.tv91, R.id.tv92, R.id.tv93, R.id.tv94, R.id.tv95},
                {R.id.tv100, R.id.tv101, R.id.tv102, R.id.tv103, R.id.tv104, R.id.tv105},
                {R.id.tv110, R.id.tv111, R.id.tv112, R.id.tv113, R.id.tv114, R.id.tv115},
                {R.id.tv120, R.id.tv121, R.id.tv122, R.id.tv123, R.id.tv124, R.id.tv125},
                {R.id.tv130, R.id.tv131, R.id.tv132, R.id.tv133, R.id.tv134, R.id.tv135},
                {R.id.tv140, R.id.tv141, R.id.tv142, R.id.tv143, R.id.tv144, R.id.tv145},
                {R.id.tv150, R.id.tv151, R.id.tv152, R.id.tv153, R.id.tv154, R.id.tv155},
                {R.id.tv160, R.id.tv161, R.id.tv162, R.id.tv163, R.id.tv164, R.id.tv165},
                {R.id.tv170, R.id.tv171, R.id.tv172, R.id.tv173, R.id.tv174, R.id.tv175},
                {R.id.tv180, R.id.tv181, R.id.tv182, R.id.tv183, R.id.tv184, R.id.tv185}};
        textViewList = new TextView[19][6];

        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 6; j++){
                textViewList[i][j] = view.findViewById(IDArray[i][j]);
            }
        }
        dayMap = new HashMap<String, String>();
        String [] Arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18"};
        String [] dayName = {"월요일", "화요일", "수요일", "목요일", "금요일"};
        for(int i = 0; i < dayName.length; i++){
            dayMap.put(dayName[i], Arr[i]);
        }

        timeMap = new HashMap<String, String>();
        String [] timeName = {"9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30", "5:00", "5:30"};
        for(int i = 0; i < timeName.length; i++){
            timeMap.put(timeName[i], Arr[i]);
        }

        addDialog = new Dialog(getContext());       // Dialog 초기화
        addDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        addDialog.setContentView(R.layout.dialog_addsubject);

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(onClickListener);

        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.addButton:
                    showAddDialog();
                    break;
            }
        }
    };

    public void showAddDialog(){
        addDialog.show();

        daySpinner = addDialog.findViewById(R.id.daySpinner);
        startTimeSpinner = addDialog.findViewById(R.id.startTimeSpinner);
        finishTimeSpinner = addDialog.findViewById(R.id.finishTimeSpinner);
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        startTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startTime = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        finishTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                finishTime = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button negativeButton = addDialog.findViewById(R.id.negativeButton);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog.dismiss(); // 다이얼로그 닫기
            }
        });
        // 네 버튼
        Button positiveButton = addDialog.findViewById(R.id.positiveButton);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayNum, startTimeNum, finishTimeNum;
                dayNum = Integer.parseInt(dayMap.get(day));
                startTimeNum = Integer.parseInt(timeMap.get(startTime));
                finishTimeNum = Integer.parseInt(timeMap.get(finishTime));

                for(int i = startTimeNum; i < finishTimeNum; i++){
                    textViewList[i][dayNum].setBackgroundColor(Color.GREEN);
                }
                addDialog.dismiss(); // 다이얼로그 닫기
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
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
