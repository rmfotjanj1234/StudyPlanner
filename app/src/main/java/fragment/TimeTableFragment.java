package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.studyplanner.R;

import activity.BottomSheetDialog;

public class TimeTableFragment extends Fragment {
    private FragmentActivity myContext;

    public CalendarView calendarView;
    public TextView selectTextView;
    public String selectDate;

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
        TextView[][] textViewList = new TextView[19][6];

        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 6; j++){
                textViewList[i][j] = view.findViewById(IDArray[i][j]);
            }
        }

        return view;
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
