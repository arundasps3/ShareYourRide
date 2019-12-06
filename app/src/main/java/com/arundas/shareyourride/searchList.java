package com.arundas.shareyourride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.arundas.RideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class searchList extends AppCompatActivity {

    ListView listviewRide;

    //data objects
    List<RideModel> rides;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        db = FirebaseDatabase.getInstance().getReference("Posted_Rides");

        listviewRide = findViewById(R.id.listViewRide);
        rides = new ArrayList<>();
    }

    @Override
    protected void onStart(){
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rides.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    RideModel rideModel = postSnapshot.getValue(RideModel.class);
                    rides.add(rideModel);
                }

                //create adapter & attach it to our listview
                RideAdapter myArtistAdapter =
                        new RideAdapter(searchList.this,rides);
                listviewRide.setAdapter(myArtistAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
