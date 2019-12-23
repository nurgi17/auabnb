package n.b.m.auabnb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.util.List;

import n.b.m.auabnb.Entities.User;
import n.b.m.auabnb.Netwrok.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Button inBtn = (Button) findViewById(R.id.btn_sign_in);
        Button upBtn = (Button) findViewById(R.id.btn_sign_up);
        final TextView error = (TextView) findViewById(R.id.error_message);
        final TextView login = (TextView) findViewById(R.id.email);
        final TextView pass = (TextView) findViewById(R.id.password);

        //Session
        final SharedPreferences pref = getSharedPreferences("user_details", MODE_PRIVATE);
        //Session END

        inBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
                progressBar.setVisibility(View.VISIBLE);
                error.setText("");

                NetworkService.getInstance()
                .getJSONApi()
                .getAllUsers()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        List<User> users = response.body();
                        boolean yes = false;
                        for (User u: users) {
                            if(u.getLogin().equals(login.getText().toString())&&u.getPassword().equals(pass.getText().toString())){
                                yes = true;

                                //Session
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("id",Long.toString(u.getId()));
                                editor.putString("login",u.getLogin());
                                editor.putString("number",Long.toString(u.getPhone_number()));
                                editor.putString("fullName",u.getFull_name());
                                editor.commit();
                                //Session END

                                Intent intent = new Intent(v.getContext(), MainActivity.class);
                                v.getContext().startActivity(intent);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }
                        }
                        if (!yes){
                            progressBar.setVisibility(View.GONE);
                            error.setText("Incorrect user or password!");
                            error.setTextColor(Color.RED);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        error.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });

            }
        });
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignUpActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
