package n.b.m.auabnb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

import n.b.m.auabnb.Adapters.ImageAdapter;
import n.b.m.auabnb.Netwrok.Upload;


public class MainActivity extends AppCompatActivity implements ImageAdapter.isAnimationMoved {
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private SlidrInterface slidr;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private CardView cityCard;
    private CardView cityCard1;
    private CardView cityCard2;

    //private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        cityCard = (CardView)findViewById(R.id.city_card);
        cityCard1 = (CardView)findViewById(R.id.city_card1);
        cityCard2 = (CardView)findViewById(R.id.city_card2);
        slidr = Slidr.attach(this);

        TextView title = (TextView) findViewById(R.id.homeTitle1);
        title.setText(" What can we help you find?");

        TextView title1 = (TextView) findViewById(R.id.almaty_text);
        title1.setText("Almaty");


        ImageView image = (ImageView) findViewById(R.id.card_view_almaty);
        String url="https://firebasestorage.googleapis.com/v0/b/mabenu-3ac40.appspot.com/o/almaty-min.jpeg?alt=media&token=ef01b374-80b1-4912-9af7-3b18354a86f4";
        Glide.with(getApplicationContext()).load(url).into(image);

        TextView asText = (TextView) findViewById(R.id.astan_text);
        asText.setText("Astana");
        ImageView asImage = (ImageView) findViewById(R.id.card_view_astana);
        String url1="https://firebasestorage.googleapis.com/v0/b/mabenu-3ac40.appspot.com/o/astana-min.jpg?alt=media&token=68b25d40-57e0-4291-a263-c18cf3cb0032";
        Glide.with(getApplicationContext()).load(url1).into(asImage);

        TextView akText = (TextView) findViewById(R.id.aktau_text);
        akText.setText("Aktau");
        ImageView akImage = (ImageView) findViewById(R.id.card_view_aktau);
        String url2="https://firebasestorage.googleapis.com/v0/b/mabenu-3ac40.appspot.com/o/aktau-min.jpg?alt=media&token=7bb3185b-4290-4f5a-b397-22d2b22d6e88";
        Glide.with(getApplicationContext()).load(url2).into(akImage);

        TextView top = (TextView) findViewById(R.id.top_rated_apartments);
        top.setText(" Top rated apartments");

        mRecyclerView = findViewById(R.id.recView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("photos");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }
                mAdapter = new ImageAdapter(MainActivity.this,mUploads,MainActivity.this);
                mAdapter.setmUploads(mUploads);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });



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

        cityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AllList.class);
                v.getContext().startActivity(intent);
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        cityCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AllList.class);
                v.getContext().startActivity(intent);
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        cityCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AllList.class);
                v.getContext().startActivity(intent);
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void fadeIn() {
        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void lockSlide(View v){
        slidr.lock();
    }
    public void unlockSlide(View v){
        slidr.unlock();
    }

}
