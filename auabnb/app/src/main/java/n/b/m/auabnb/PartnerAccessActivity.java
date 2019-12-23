package n.b.m.auabnb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;


public class PartnerAccessActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_access);
        setTitle(R.string.partner);

        slidr = Slidr.attach(this);
        Button home = (Button) findViewById(R.id.home_button);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
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
    public void lockSlide(View v){
        slidr.lock();
    }
    public void unlockSlide(View v){
        slidr.unlock();
    }

}
