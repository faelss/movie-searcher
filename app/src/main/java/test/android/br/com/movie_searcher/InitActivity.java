package test.android.br.com.movie_searcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InitActivity extends AppCompatActivity {

    private Intent initActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final EditText userName = findViewById(R.id.userName);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initActivity = new Intent(InitActivity.this,SearchActivity.class);
                initActivity.putExtra("userName",String.valueOf(userName));
                if(initActivity != null){
                    finish();
                    startActivity(initActivity);
                }
            }
        });
    }
}
