package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.studyplanner.R;

import activity.BottomSheetDialog;

public class CalendarFragment extends Fragment {
    private FragmentActivity myContext;

    public CalendarView calendarView;
    public TextView selectTextView;
    public String selectDate;

    BottomSheetDialog bottomSheetDialog;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        selectTextView = view.findViewById(R.id.selectTextView);
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectYear, selectMonth, selectDay;
                selectYear = Integer.toString(year);
                selectMonth = Integer.toString(month + 1);
                selectDay = Integer.toString(dayOfMonth);


                selectDate = selectYear + " " + selectMonth + " " + selectDay;
                selectTextView.setText("선택 날짜 : " + selectDate);

                FragmentManager fragmentManager = myContext.getSupportFragmentManager();
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.getInstance();
                bottomSheetDialog.show(fragmentManager, "bottomsheetdialog");

            }
        });

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
