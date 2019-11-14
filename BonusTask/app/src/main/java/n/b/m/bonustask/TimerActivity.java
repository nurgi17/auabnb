package n.b.m.bonustask;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {

    public int counter = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        final LinearLayout lay = (LinearLayout) findViewById(R.id.rlview);
        final TextView timer = (TextView) findViewById(R.id.count_time);
        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(counter));
                counter--;
            }

            @Override
            public void onFinish() {
                timer.setText("Finished");
                lay.setBackgroundColor(Color.RED);
            }
        }.start();


        Button button = (Button) findViewById(R.id.button_back);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
    }

}
