package pe.comercio.materialsearchview;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AppCompatButton btnFirstDemoActivity;
    private AppCompatButton btnSecondDemoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFirstDemoActivity = (AppCompatButton) findViewById(R.id.btnFirstDemo);
        btnSecondDemoActivity = (AppCompatButton) findViewById(R.id.btnSecondDemo);

        btnFirstDemoActivity.setOnClickListener(this);
        btnSecondDemoActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFirstDemo:
                startActivity(new Intent(this, FirstDemoActivity.class));
                break;
            case R.id.btnSecondDemo:
                startActivity(new Intent(this, SecondDemoActivity.class));
                break;
        }

    }

}
