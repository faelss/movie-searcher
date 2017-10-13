package test.android.br.com.movie_searcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InitActivity extends AppCompatActivity {

    private Intent searchActivity = null;
    private EditText nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nameUser = findViewById(R.id.nameUser);
        nameUser.setText("");
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchActivity = new Intent(InitActivity.this,SearchActivity.class);
                searchActivity.putExtra("username",nameUser.getText().toString());
                if(searchActivity != null){
                    finish();
                    startActivity(searchActivity);
                }
            }
        });
    }
}
