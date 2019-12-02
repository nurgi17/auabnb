package n.b.m.auabnb;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PartnerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);
        setTitle(R.string.partner);

        TextView title = (TextView) findViewById(R.id.helper_title);
        title.setText("Please fill the information");

        Button confirm = (Button) findViewById(R.id.confirm_action);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PartnerAccessActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent a = new Intent(PartnerActivity.this,MainActivity.class);
                        startActivity(a);
                        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case R.id.ic_plus:
                        break;
                    case R.id.ic_person:
                        Intent b = new Intent(PartnerActivity.this,ProfileActivity.class);
                        startActivity(b);
                        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out);
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
