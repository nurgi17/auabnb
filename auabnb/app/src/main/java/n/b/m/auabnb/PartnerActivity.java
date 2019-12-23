package n.b.m.auabnb;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import n.b.m.auabnb.Entities.Apartment;
import n.b.m.auabnb.Entities.City;
import n.b.m.auabnb.Entities.Photos;
import n.b.m.auabnb.Entities.User;
import n.b.m.auabnb.Netwrok.NetworkService;
import n.b.m.auabnb.Netwrok.Upload;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnerActivity extends AppCompatActivity {
    private static byte[] someBytePhoto = null;
    private Context context;
    private TextView title ;
    private TextView naTitle;
    private  TextView naPrice;
    private  TextView naDescription;
    private  TextView naImgTitle;
    private Button naImgAdd;
    private  TextView naCountry;
    private  TextView naCity;
    private  TextView naStreet;
    private  TextView error;
    private Button confirm;
    private BottomNavigationView navigation;
    private ImageView imgV;
    private Uri imageUri;
    private static final int PICK_IMAGE = 1;

    private SlidrInterface slidr;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);
        initUI();
        final SharedPreferences pref = getSharedPreferences("user_details", MODE_PRIVATE);
        slidr = Slidr.attach(this);



        //Adding new Apartment
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                System.out.println("User ID = " + Long.parseLong(pref.getString("id", "")));

                final Apartment newApartment = new Apartment(naTitle.getText().toString(), Double.parseDouble(naPrice.getText().toString()),
                        naDescription.getText().toString(), naCountry.getText().toString(), naStreet.getText().toString(),
                        10L, Long.parseLong(pref.getString("id", "")));


                //Adding new apartment by found city
                NetworkService.getInstance()
                        .getJSONApi()
                        .getAllCities()
                        .enqueue(new Callback<List<City>>() {
                            public void onResponse(@NonNull Call<List<City>> call, @NonNull Response<List<City>> response) {
                                List<City> cities = response.body();
                                boolean yes = false;
                                for (City c : cities) {
                                    if (c.getName().equals(naCity.getText().toString())) {
                                        yes = true;
                                        newApartment.setCity_id(c.getId());
                                    }
                                }
                                if (!yes) {
                                    error.setText("City doesn't exist!");
                                    error.setTextColor(Color.RED);
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<List<City>> call, @NonNull Throwable t) {
                                error.append("Error occurred while getting request!");
                                t.printStackTrace();
                            }
                        });
                //Adding new apartment by found city END

                //Adding photo by new apartment
                NetworkService.getInstance()
                        .getJSONApi()
                        .createApartment(newApartment)
                        .enqueue(new Callback<Apartment>() {
                            @Override
                            public void onResponse(@NonNull Call<Apartment> call, @NonNull Response<Apartment> response) {


                                NetworkService.getInstance()
                                        .getJSONApi()
                                        .getAllApartments()
                                        .enqueue(new Callback<List<Apartment>>() {
                                            @Override
                                            public void onResponse(@NonNull Call<List<Apartment>> call, @NonNull Response<List<Apartment>> response) {
                                                List<Apartment> apartments = response.body();
                                                boolean yes = false;

                                                if(apartments!=null){
                                                    yes = true;
                                                    int l = apartments.size();
                                                    Apartment aa = apartments.get(l-1);
                                                    uploadFile(aa.getId(), aa.getPrice(), aa.getTitle());
                                                    Intent intent = new Intent(v.getContext(), PartnerAccessActivity.class);
                                                    v.getContext().startActivity(intent);
                                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                                    //Photos ppp = new Photos(someBytePhoto, aa.getId());

//                                                    NetworkService.getInstance()
//                                                            .getJSONApi()
//                                                            .createPhoto(ppp)
//                                                            .enqueue(new Callback<Photos>() {
//                                                                @Override
//                                                                public void onResponse(@NonNull Call<Photos> call, @NonNull Response<Photos> response) {
//
//                                                                    System.out.println("Photo was send sucessfully KUKA");
//                                                                    Intent intent = new Intent(v.getContext(), PartnerAccessActivity.class);
//                                                                    v.getContext().startActivity(intent);
//                                                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                                                                }
//                                                                @Override
//                                                                public void onFailure(@NonNull Call<Photos> call, @NonNull Throwable t) {
//                                                                    System.out.println("Photo was not send sucessfully KUKA");
//
//                                                                    error.append("Error occurred while getting request!");
//                                                                    t.printStackTrace();
//                                                                }
//                                                            });
                                                }

                                                if (!yes){
                                                    error.setText("Incorrect user or password!");
                                                    error.setTextColor(Color.RED);
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<List<Apartment>> call, @NonNull Throwable t) {
                                                System.out.println("First try fail KUKA");

                                                error.append("Error occurred while getting request!");
                                                t.printStackTrace();
                                            }
                                        });


                            }

                            @Override
                            public void onFailure(@NonNull Call<Apartment> call, @NonNull Throwable t) {
                                System.out.println("Second try fail KUKA");

                                error.append("Error occurred while getting request!");
                                t.printStackTrace();
                            }
                        });
                //Adding photo by new apartment END
            }
        });
        //Adding new Apartment END

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent a = new Intent(PartnerActivity.this, MainActivity.class);
                        startActivity(a);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case R.id.ic_plus:
                        break;
                    case R.id.ic_person:
                        Intent b = new Intent(PartnerActivity.this, ProfileActivity.class);
                        startActivity(b);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                }
                return false;
            }
        });

        naImgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
    }

    private void initUI() {
         title = (TextView) findViewById(R.id.helper_title);
         title.setText("Please fill the information");
         naTitle = (TextView) findViewById(R.id.new_apart_title);
         setTitle(R.string.partner);
         naPrice = (TextView) findViewById(R.id.new_apart_price);
         naDescription = (TextView) findViewById(R.id.new_apart_description);
         naImgTitle = (TextView) findViewById(R.id.new_apart_images_title);
         naImgAdd = findViewById(R.id.new_apart_images_add);
         naCountry = (TextView) findViewById(R.id.new_apart_country);
         naCity = (TextView) findViewById(R.id.new_apart_city);
         naStreet = (TextView) findViewById(R.id.new_apart_street);
         error = (TextView) findViewById(R.id.error_message);
         confirm = (Button) findViewById(R.id.confirm_action);
         navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
         imgV = (ImageView) findViewById(R.id.imgView);
         mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
         mDatabaseRef = FirebaseDatabase.getInstance().getReference("photos");
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

@RequiresApi(api = Build.VERSION_CODES.O)
protected void onActivityResult(int reqCode, int resultCode, Intent data) {
    super.onActivityResult(reqCode, resultCode, data);
    if (resultCode == RESULT_OK) {
        try {
            imageUri = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            someBytePhoto = byteArray;
            imgV.setImageBitmap(selectedImage);
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            error.setText("Something went wrong");
        }
    }else {
        error.setText("You haven't picked Image");
    }
}

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile(final long apartment_id, final double price, final String title){
        if(imageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
            +"."+getFileExtension(imageUri));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(PartnerActivity.this, "Upload succesfull", Toast.LENGTH_SHORT).show();
                            if (taskSnapshot.getMetadata() != null) {
                                if (taskSnapshot.getMetadata().getReference() != null) {
                                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageUrl = uri.toString();
                                            Upload upload = new Upload("New photo",
                                                    imageUrl, apartment_id, price, title);
                                            String uploadId = mDatabaseRef.push().getKey();
                                            mDatabaseRef.child(uploadId).setValue(upload);
                                        }
                                    });
                                }
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PartnerActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
    public void lockSlide(View v){
        slidr.lock();
    }
    public void unlockSlide(View v){
        slidr.unlock();
    }
}
