package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.studyplanner.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import adapter.Home_VPAdapter;

public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private Home_VPAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (ViewPager)view.findViewById(R.id.Viewpager);
        setupViewPager(viewPager);

        TabLayout tab = (TabLayout)view.findViewById(R.id.Tabs);
        tab.setupWithViewPager(viewPager);


        return view;
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

    private void setupViewPager(ViewPager viewPager) {
        Home_VPAdapter adapter = new Home_VPAdapter(getChildFragmentManager());
        adapter.addFragment(new TimeTableFragment(), "시간표");
        adapter.addFragment(new CalendarFragment(), "달력");
        viewPager.setAdapter(adapter);
    }

    private void setIconOfTabs(TabLayout tab) {
        ArrayList<Integer> images = new ArrayList<>();

        images.add(R.drawable.ic_launcher_foreground);

        for(int i=0; i<2; i++)
            tab.getTabAt(i).setIcon(images.get(0));

    }
}
