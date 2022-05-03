package com.example.modulesystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT= 4000;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent=new Intent(MainActivity.this, Login.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

         */
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLoginDialog();
            }
        });

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignupDialog();
            }
        });
    }

    private void handleSignupDialog() {
        View view= getLayoutInflater().inflate(R.layout.activity_login,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setView(view).show();
        Button loginBtn= view.findViewById(R.id.login);
        EditText idNum= view.findViewById(R.id.idNum);
        EditText secret= view.findViewById(R.id.secret);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String>map=new HashMap<>();
                map.put("ID#",idNum.getText().toString());
                map.put("Password",secret.getText().toString());
                Call<Login> call=retrofitInterface.executeLogin(map);
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {

                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        });
    }

    private void handleLoginDialog() {
        View view= getLayoutInflater().inflate(R.layout.activity_register,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button signupBtn =view.findViewById(R.id.signup);
        EditText fname=view.findViewById(R.id.fname);
        EditText lname=view.findViewById(R.id.lname);
        EditText idNum=view.findViewById(R.id.idNum);
        EditText email=view.findViewById(R.id.email);
        EditText secret=view.findViewById(R.id.secret);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String>map=new HashMap<>();
                map.put("First Name", fname.getText().toString());
                map.put("Last Name", lname.getText().toString());
                map.put("ID#",idNum.getText().toString());
                map.put("Email",email.getText().toString());
                map.put("Password",secret.getText().toString());

                Call<Void>call=retrofitInterface.executeSignup(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        });
    }
}