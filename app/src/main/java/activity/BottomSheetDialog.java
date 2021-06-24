package activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.studyplanner.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener{

    public static BottomSheetDialog getInstance() { return new BottomSheetDialog(); }

    private LinearLayout LL1, LL2, LL3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bottomsheet, container,false);
        LL1 = (LinearLayout) view.findViewById(R.id.LL1);
        LL2 = (LinearLayout) view.findViewById(R.id.LL2);
        LL3 = (LinearLayout) view.findViewById(R.id.LL3);

        LL1.setOnClickListener(this);
        LL2.setOnClickListener(this);
        LL3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.LL1:
                Toast.makeText(getContext(),"LL1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.LL2:
                Toast.makeText(getContext(),"LL2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.LL3:
                Toast.makeText(getContext(),"LL3",Toast.LENGTH_SHORT).show();
                break;
        }
        dismiss();
    }
}