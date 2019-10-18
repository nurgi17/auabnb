package n.b.m.auabnb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        TextView title = (TextView) findViewById(R.id.homeTitle1);
        title.setText(" What can we help you find?");

        TextView title1 = (TextView) findViewById(R.id.almaty_text);
        title1.setText("Almaty");
        ImageView image = (ImageView) findViewById(R.id.card_view_almaty);
        image.setImageResource(R.drawable.almaty);

        TextView asText = (TextView) findViewById(R.id.astan_text);
        asText.setText("Astana");
        ImageView asImage = (ImageView) findViewById(R.id.card_view_astana);
        asImage.setImageResource(R.drawable.astana);

        TextView akText = (TextView) findViewById(R.id.aktau_text);
        akText.setText("Aktau");
        ImageView akImage = (ImageView) findViewById(R.id.card_view_aktau);
        akImage.setImageResource(R.drawable.aktau);

        TextView top = (TextView) findViewById(R.id.top_rated_apartments);
        top.setText(" Top rated apartments");


        ImageView topImage = (ImageView) findViewById(R.id.card_view_top);
        topImage.setImageResource(R.drawable.top);
        TextView topPartner = (TextView) findViewById(R.id.top_text);
        topPartner.setText(" Micheal Room");
        TextView topPrice = (TextView) findViewById(R.id.top_price);
        topPrice.setText(" $456/day");

        ImageView topImage1 = (ImageView) findViewById(R.id.card_view_top1);
        topImage1.setImageResource(R.drawable.top);
        TextView topPartner1 = (TextView) findViewById(R.id.top_text1);
        topPartner1.setText(" Micheal Room");
        TextView topPrice1 = (TextView) findViewById(R.id.top_price1);
        topPrice1.setText(" $456/day");

        TextView offers = (TextView) findViewById(R.id.offers);
        offers.setText(" Offers");

        ImageView ofImage = (ImageView) findViewById(R.id.off_image);
        ofImage.setImageResource(R.drawable.offer);
        TextView ofText1 = (TextView) findViewById(R.id.off_text1);
        ofText1.setText(" 11 000 тг");
        TextView ofText2 = (TextView) findViewById(R.id.off_text2);
        ofText2.setText(" 1-комнатная квартира");
        TextView ofText3 = (TextView) findViewById(R.id.off_text3);
        ofText3.setText(" г. Алматы, Каблукова - Фруне");


        CardView cardView1 = (CardView) findViewById(R.id.top_card_apart1);
        CardView cardView2 = (CardView) findViewById(R.id.top_card_apart2);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ApartmentActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ApartmentActivity.class);
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
                        break;
                    case R.id.ic_plus:
                        Intent a = new Intent(MainActivity.this,PartnerActivity.class);
                        startActivity(a);
                        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case R.id.ic_person:
                        Intent b = new Intent(MainActivity.this,ProfileActivity.class);
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
