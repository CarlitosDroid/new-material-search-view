package pe.comercio.materialsearchview.activity;

import android.app.Dialog;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import pe.comercio.materialsearchview.R;
import pe.comercio.materialsearchview.model.UserEntity;
import pe.comercio.materialsearchview.storage.database.LastSearchDB;
import pe.comercio.materialsearchview.util.Util;
import pe.comercio.materialsearchview.view.SecondFilter;
import pe.comercio.materialsearchview.view.UserFilter;
import pe.comercio.materialsearchview.view.adapter.SecondAdapter;
import pe.comercio.materialsearchview.view.fragment.DeleteLastSearchDialgoFragment;
import pe.comercio.materialsearchview.view.fragment.OnLastSearchDeletedListener;

public class SecondDemoActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher,
        OnLastSearchDeletedListener{

    private ImageView imgSearch;

    Dialog dialog;
    View view;
    private ImageView imgVoice;
    private ImageView imgClose;
    private ImageView imgBack;
    LinearLayout linGeneral;
    private EditText txtSearch;
    private RecyclerView recyclerView;

    private SecondAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<UserEntity> userEntityList = new ArrayList<>();

    private LastSearchDB lastSearchDB;


    public static final int SPEECH_REQUEST_CODE = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_demo);
        imgSearch = (ImageView) findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(this);

        view = getLayoutInflater().inflate(R.layout.fragment_dialog_second_demo, null);
        linGeneral = (LinearLayout) view.findViewById(R.id.linGeneral);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcvContact);
        txtSearch = (EditText) view.findViewById(R.id.txtSearch);
        imgVoice = (ImageView) view.findViewById(R.id.imgVoice);
        imgClose = (ImageView) view.findViewById(R.id.imgClose);
        imgBack = (ImageView) view.findViewById(R.id.imgBack);

        lastSearchDB = new LastSearchDB(this);

        initDialog();
    }

    private void initDialog(){

        userEntityList.addAll(lastSearchDB.getOrderedListLastSearch());

//        Collections.sort(userEntityList);
//        Collections.reverse(userEntityList);

        userAdapter = new SecondAdapter(this, userEntityList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);

        dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        linGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialog.isShowing()){
                    dialog.hide();
                }
            }
        });

        txtSearch.addTextChangedListener(this);
        imgSearch.setOnClickListener(this);
        imgVoice.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgSearch:
                showDialogSearchView();
                //loadToolBarSearch();
                break;
        }
    }

    public void showDialogSearchView() {
        dialog.show();
        txtSearch.setText("");

        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String textSelected = txtSearch.getText().toString();

                    if(!textSelected.isEmpty()){
                        if(shouldAddLastSearch(textSelected)){
                            lastSearchDB.addLastSearch(textSelected, Util.getFormatDate());
                            Toast.makeText(SecondDemoActivity.this, "ENTER", Toast.LENGTH_SHORT).show();
                            userEntityList.clear();
                            userEntityList.addAll(lastSearchDB.getOrderedListLastSearch());
                            ((SecondFilter) userAdapter.getFilter()).addItemToOfOriginalList(new UserEntity(textSelected, Util.getFormatDate()));
                        }
                    }
                    dialog.dismiss();

                    return true;
                }
                return false;
            }
        });

    }

    public boolean shouldAddLastSearch(String textSelected){
        for (int i = 0; i < userEntityList.size(); i++) {
            if(userEntityList.get(i).getName().trim().toLowerCase().equals(textSelected.trim().toLowerCase())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(txtSearch.getText().toString().trim().isEmpty()){

            imgVoice.setVisibility(View.VISIBLE);
            imgClose.setVisibility(View.GONE);

        }else{
            imgVoice.setVisibility(View.GONE);
            imgClose.setVisibility(View.VISIBLE);
        }

        Log.e("TAMAÑO ","TAM AÑO  " + userEntityList.size());
        userAdapter.getFilter().filter(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public void showDeleteLabelDialogFragment(String name, int position) {
        FragmentManager fm = this.getSupportFragmentManager();
        DeleteLastSearchDialgoFragment deleteTagDialogFragment =
                DeleteLastSearchDialgoFragment.newInstance(name, position);
        deleteTagDialogFragment.show(fm, "layout_filter_checkbox_dialog");

    }

    @Override
    public void onLastSearchDeleted(String name, int itemPosition) {
        lastSearchDB.deleteLastSearchByName(name);
        ((SecondFilter) userAdapter.getFilter()).removeItemFromOriginalAndFilteredLisy(itemPosition);
        Toast.makeText(this, "size " + lastSearchDB.getOrderedListLastSearch().size(), Toast.LENGTH_SHORT).show();
    }

    private void onVoiceClicked() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "SPEAK NOW!");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && results.size() > 0) {
                String searchWrd = results.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    if (dialog != null) {
                        if(dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }


                    if(shouldAddLastSearch(searchWrd)){
                        userEntityList.add(new UserEntity(searchWrd, Util.getFormatDate()));
                        ((UserFilter) userAdapter.getFilter()).addItemToOfOriginalList(new UserEntity(searchWrd, Util.getFormatDate()));
                    }

                    txtSearch.setText(searchWrd);
                }
            }
        }
    }

}
