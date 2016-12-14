package pe.comercio.materialsearchview.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import pe.comercio.materialsearchview.R;


/**
 * Created by Carlos Vargas on 13/05/16.
 * Alias: CarlitosDroid
 */

public class DeleteLastSearchDialgoFragment extends DialogFragment implements View.OnClickListener{

    private AppCompatButton btnAccept;
    private AppCompatButton btnCancel;

    private OnLastSearchDeletedListener onLastSearchDeletedListener;

    private int labelPosition = 0;
    private String labelName = "";

    public DeleteLastSearchDialgoFragment(){
    }

    @SuppressWarnings("SameParameterValue")
    public static DeleteLastSearchDialgoFragment newInstance(String labelName, int labelPosition){
        DeleteLastSearchDialgoFragment frag = new DeleteLastSearchDialgoFragment();
        Bundle args = new Bundle();

        args.putInt("labelPosition", labelPosition);
        args.putString("labelName", labelName);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        labelPosition = getArguments().getInt("labelPosition");
        labelName = getArguments().getString("labelName");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fdialog_delete_tag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        onLastSearchDeletedListener = (OnLastSearchDeletedListener)getActivity();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        btnAccept = (AppCompatButton) view.findViewById(R.id.btnAccept);
        btnCancel = (AppCompatButton) view.findViewById(R.id.btnCancel);

        btnAccept.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAccept:
                onLastSearchDeletedListener.onLastSearchDeleted(labelName,labelPosition);
                getDialog().dismiss();
                break;
            case R.id.btnCancel:
                getDialog().dismiss();
                break;
            default:
                break;
        }
    }
}
