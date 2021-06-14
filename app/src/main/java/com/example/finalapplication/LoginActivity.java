package com.example.finalapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.finalapplication.Api.InterfaceAPI;
import com.example.finalapplication.Api.RetrofitClientInstance;
import com.example.finalapplication.Model.LoginRequest;
import com.example.finalapplication.Model.LoginResponse;
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
//    EditText npmEditText, passwordEditText;
//    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText npmEditText = (EditText) findViewById(R.id.editTextNPM);
        final EditText passwordEditText = findViewById(R.id.editTextPassword);
        Button signIn = findViewById(R.id.SignInButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginRequest(npmEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    public void onLoginRequest(String npm, String password){
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);

        LoginRequest login = new LoginRequest();
        login.setNpm(npm);
        login.setPassword(password);
        login.setDeviceName("Android");

        Call<LoginResponse> call = api.checkLogin(login);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().equalsIgnoreCase("success")){

                        // Add token to shared preferences
                        SharedPreferences prefs;
                        SharedPreferences.Editor edit;
                        prefs=LoginActivity.this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                        edit=prefs.edit();
                        String saveToken = response.body().getToken();
                        edit.putString("AuthToken",saveToken);
                        edit.commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("TAG", t.toString());
                t.printStackTrace();
            }
        });
    }
}