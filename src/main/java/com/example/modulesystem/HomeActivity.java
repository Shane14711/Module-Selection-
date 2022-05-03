package com.example.modulesystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    private Button f_Year, s_Year, t_Year, fth_Year, fif_Year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        f_Year=findViewById(R.id.f_Year);
       // s_Year=findViewById(R.id.s_Year);
      //  t_Year=findViewById(R.id.t_Year);
      //  fth_Year=findViewById(R.id.fth_Year);
      //  fif_Year=findViewById(R.id.fif_Year);

       /* f_Year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openF_Year();
            }
        }); */
    }
}