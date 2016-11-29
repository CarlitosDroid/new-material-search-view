package pe.comercio.materialsearchview;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FirstDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgSearch;

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
                loadToolBarSearch();
                break;
        }
    }

    public void loadToolBarSearch() {


        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_first_demo, null);
        LinearLayout linGeneral = (LinearLayout) view.findViewById(R.id.linGeneral);
//        ImageView imgToolBack = (ImageView) view.findViewById(R.id.img_tool_back);
//        final EditText edtToolSearch = (EditText) view.findViewById(R.id.edt_tool_search);
//        ImageView imgToolMic = (ImageView) view.findViewById(R.id.img_tool_mic);
//        final ListView listSearch = (ListView) view.findViewById(R.id.list_search);
//        final TextView txtEmpty = (TextView) view.findViewById(R.id.txt_empty);
//
//        Utils.setListViewHeightBasedOnChildren(listSearch);

//        edtToolSearch.setHint("Search your country");

        final Dialog toolbarSearchDialog = new Dialog(this, R.style.MaterialSearch);
        toolbarSearchDialog.setContentView(view);
        toolbarSearchDialog.setCancelable(true);
        toolbarSearchDialog.setCanceledOnTouchOutside(true);
        toolbarSearchDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        toolbarSearchDialog.getWindow().setGravity(Gravity.BOTTOM);
        toolbarSearchDialog.show();

        toolbarSearchDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        linGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toolbarSearchDialog.isShowing()){
                    toolbarSearchDialog.hide();
                }
            }
        });

    }
}
