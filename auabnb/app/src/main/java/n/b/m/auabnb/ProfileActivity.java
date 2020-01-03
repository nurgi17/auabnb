package n.b.m.auabnb;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class ProfileActivity extends AppCompatActivity {
    private Button signOut;
    private SlidrInterface slidr;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.profile);
        slidr = Slidr.attach(this);
        mAuth = FirebaseAuth.getInstance();
        signOut = findViewById(R.id.sign_out_button);
//        TextView title = (TextView) findViewById(R.id.activityTitle2);
//        title.setText("Your profile page");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        signOut.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                        mAuth.signOut();
                Intent intent = new Intent(v.getContext(), SignInActivity.class);
                v.getContext().startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
        });



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent a = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(a);
                        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case R.id.ic_plus:
                        Intent b = new Intent(ProfileActivity.this, PartnerActivity.class);
                        startActivity(b);
                        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case R.id.ic_person:
                        break;
                }
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    public void lockSlide(View v){
        slidr.lock();
    }
    public void unlockSlide(View v){
        slidr.unlock();
    }

}
