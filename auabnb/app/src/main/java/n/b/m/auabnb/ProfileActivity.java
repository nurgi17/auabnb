package n.b.m.auabnb;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.profile);


//        TextView title = (TextView) findViewById(R.id.activityTitle2);
//        title.setText("Your profile page");

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

}
