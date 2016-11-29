package pe.comercio.materialsearchview.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.comercio.materialsearchview.R;
import pe.comercio.materialsearchview.model.UserEntity;
import pe.comercio.materialsearchview.view.adapter.UserAdapter;

public class FirstDemoActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private ImageView imgSearch;
    private TextView txtSearch;

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<UserEntity> userEntityList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_demo);
        imgSearch = (ImageView) findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgSearch:
                showDialogSearchView();
                break;
        }
    }

    public void showDialogSearchView() {
        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_first_demo, null);
        LinearLayout linGeneral = (LinearLayout) view.findViewById(R.id.linGeneral);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcvContact);
        txtSearch = (TextView) view.findViewById(R.id.txtSearch);

        userEntityList.add(new UserEntity("carlos"));
        userEntityList.add(new UserEntity("ricardo"));
        userEntityList.add(new UserEntity("jose"));
        userEntityList.add(new UserEntity("hernesto"));
        userEntityList.add(new UserEntity("eduardo"));
        userEntityList.add(new UserEntity("fabri"));
        userEntityList.add(new UserEntity("monkey"));
        userEntityList.add(new UserEntity("monkey"));
        userEntityList.add(new UserEntity("rabbit"));
        userEntityList.add(new UserEntity("lion"));
        userEntityList.add(new UserEntity("been"));
        userEntityList.add(new UserEntity("monkey"));
        userEntityList.add(new UserEntity("monkey"));
        userEntityList.add(new UserEntity("monkey"));
        userEntityList.add(new UserEntity("monkey"));


        userAdapter = new UserAdapter(this, userEntityList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);


        final Dialog dialog = new Dialog(this, R.style.MaterialSearch);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        txtSearch.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        userAdapter.getFilter().filter(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
