package n.b.m.auabnb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import n.b.m.auabnb.Entities.User;
import n.b.m.auabnb.Netwrok.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button btnIn = (Button) findViewById(R.id.sign_in_btn);
        Button btnUp = (Button) findViewById(R.id.sign_up_btn);
        final TextView login = (TextView) findViewById(R.id.email);
        final TextView pass = (TextView) findViewById(R.id.password);
        final TextView passRep = (TextView) findViewById(R.id.rep_password);
        final TextView fName = (TextView) findViewById(R.id.full_name);
        final TextView pNumber = (TextView) findViewById(R.id.phone_number);
        final TextView error = (TextView) findViewById(R.id.error);

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignInActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                long number = Long.parseLong(pNumber.getText().toString());
                User newUser = new User(login.getText().toString(), pass.getText().toString(), number, fName.getText().toString());

                NetworkService.getInstance()
                        .getJSONApi()
                        .createUser(newUser)
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                                Intent intent = new Intent(v.getContext(), MainActivity.class);
                                v.getContext().startActivity(intent);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }
                            @Override
                            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

                                error.append("Error occurred while getting request!");
                                t.printStackTrace();
                            }
                        });
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
