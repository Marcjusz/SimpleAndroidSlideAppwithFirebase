package com.example.mn.simpleandroidslideappwithfirebase;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    DatabaseReference database;
    DataBaseGift dataBaseGift;
    ArrayList<DataBaseGift> dataBaseGiftArrayList = new ArrayList<>();
    ArrayList<String> dataBaseGiftsKeysArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //make reference to base
        database = FirebaseDatabase.getInstance().getReference();

        //read from base and start page adapter
        readFromFirebase(database);

        /* didint work
        StorageReference storageReference2 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://praca-inzynierska-omm-omm-site.appspot.com/zdjecie1.jpg");
        ImageView imageView = findViewById(R.id.a1);
        GlideApp.with(this).load(storageReference2).into(imageView);
        */

        /* leater use for upgrade app
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Poszedł lajk", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void readFromFirebase(DatabaseReference myRef){

        myRef.child("Base").child("Slider").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                    dataBaseGift=childSnapshot.getValue(DataBaseGift.class);
                    dataBaseGiftArrayList.add(dataBaseGift);
                    dataBaseGiftsKeysArrayList.add(childSnapshot.getKey());
                    Log.d("odczyt", "Value is: " + dataBaseGift.getTitle());
                    viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), dataBaseGiftsKeysArrayList, dataBaseGiftArrayList, database));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("odczyt", "Failed to read value.", error.toException());
            }
        });
    }
}
