package pe.comercio.materialsearchview.activity;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.comercio.materialsearchview.R;
import pe.comercio.materialsearchview.model.UserEntity;
import pe.comercio.materialsearchview.view.UserFilter;
import pe.comercio.materialsearchview.view.adapter.UserAdapter;
import pe.comercio.materialsearchview.view.fragment.DeleteLastSearchDialgoFragment;
import pe.comercio.materialsearchview.view.fragment.OnLastSearchDeletedListener;

public class FirstDemoActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher,
        OnLastSearchDeletedListener {

    private TextView lblSearchh;

    private ImageView imgSearch;
    private EditText txtSearch;

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<UserEntity> userEntityList = new ArrayList<>();

    private ImageView dialogImageSearch;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_demo);
        imgSearch = (ImageView) findViewById(R.id.imgSearch);
        lblSearchh = (TextView) findViewById(R.id.lblSearchh);
        imgSearch.setOnClickListener(this);

        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_first_demo, null);
        LinearLayout linGeneral = (LinearLayout) view.findViewById(R.id.linGeneral);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcvContact);
        txtSearch = (EditText) view.findViewById(R.id.txtSearch);

        dialogImageSearch = (ImageView) view.findViewById(R.id.imgSearch);

        userEntityList.add(new UserEntity("carlos"));
        userEntityList.add(new UserEntity("henry"));
        userEntityList.add(new UserEntity("david"));
        userEntityList.add(new UserEntity("bill"));

        userAdapter = new UserAdapter(this, userEntityList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);

        dialog = new Dialog(this, R.style.MaterialSearch);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        txtSearch.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgSearch:
                showDialogSearchView();
                break;
        }
    }

    public void showDialogSearchView() {
        dialog.show();
        txtSearch.setText("");

        dialogImageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

//    public void showDialogSearchView2() {
//
//        userAdapter = new UserAdapter(FirstDemoActivity.this, userEntityList);
//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(userAdapter);
//
//        dialog.show();
//        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//
//        txtSearch.setText("");
//
//        txtSearch.addTextChangedListener(this);
//
//        dialogImageSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                ((UserFilter) userAdapter.getFilter()).addItemToOfOriginalList(new UserEntity("nuevo"));
//                userEntityList.clear();
//                userEntityList.addAll(((UserFilter) userAdapter.getFilter()).getOriginalList());
//
//            }
//        });
//    }

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

    public void showDeleteLabelDialogFragment(int position) {
        FragmentManager fm = this.getSupportFragmentManager();
        DeleteLastSearchDialgoFragment deleteTagDialogFragment =
                DeleteLastSearchDialgoFragment.newInstance(position);
        deleteTagDialogFragment.show(fm, "layout_filter_checkbox_dialog");
    }


    @Override
    public void onLastSearchDeleted(String name, int itemPosition) {
        Toast.makeText(this, name + " - " + itemPosition, Toast.LENGTH_SHORT).show();
        ((UserFilter) userAdapter.getFilter()).removeItemFromOriginalAndFilteredLisy(itemPosition);
    }
}
