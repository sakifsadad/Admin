package com.example.dil.admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateUpcomingProduct extends AppCompatActivity {


    private String productID = "";
    private EditText productName, price, display, color, ram, rom, sim, battery, processor, network, camera, fingerprint, youtubevideolink, others;

    DatabaseReference upcomingProductsref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_upcoming_product);
        setTitle("Upcoming Products");


        productName = (EditText) findViewById(R.id.edit_name);
        price = (EditText) findViewById(R.id.edit_price);
        display = (EditText) findViewById(R.id.edit_display);
        color = (EditText) findViewById(R.id.edit_color);
        ram = (EditText) findViewById(R.id.edit_ram);
        rom = (EditText) findViewById(R.id.edit_rom);
        sim = (EditText) findViewById(R.id.edit_sim);
        battery = (EditText) findViewById(R.id.edit_battery);
        processor = (EditText) findViewById(R.id.edit_processor);
        network = (EditText) findViewById(R.id.edit_network);
        camera = (EditText) findViewById(R.id.edit_camera);
        fingerprint = (EditText) findViewById(R.id.edit_fingerprint);
        youtubevideolink = (EditText) findViewById(R.id.edit_youtube_video_link);
        others = (EditText) findViewById(R.id.edit_others);


        productID = getIntent().getStringExtra("pid");
        upcomingProductsref = FirebaseDatabase.getInstance().getReference().child("Upcoming Products").child(productID);


        productName.setText(getIntent().getStringExtra("ProductName"));
        price.setText(getIntent().getStringExtra("Price"));
        display.setText(getIntent().getStringExtra("Display"));
        color.setText(getIntent().getStringExtra("Color"));
        ram.setText(getIntent().getStringExtra("RAM"));
        rom.setText(getIntent().getStringExtra("ROM"));
        sim.setText(getIntent().getStringExtra("Sim"));
        battery.setText(getIntent().getStringExtra("Battery"));
        processor.setText(getIntent().getStringExtra("Processor"));
        network.setText(getIntent().getStringExtra("Network"));
        camera.setText(getIntent().getStringExtra("Camera"));
        fingerprint.setText(getIntent().getStringExtra("Fingerprint"));
        youtubevideolink.setText(getIntent().getStringExtra("YoutubeVideoLink"));
        others.setText(getIntent().getStringExtra("Others"));


    }

    public void btnupdate_click(View view) {

        if (AppStatus.getInstance(UpdateUpcomingProduct.this).isOnline()) {


            upcomingProductsref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dataSnapshot.getRef().child("ProductName").setValue(productName.getText().toString());
                    dataSnapshot.getRef().child("Price").setValue(price.getText().toString());
                    dataSnapshot.getRef().child("Battery").setValue(battery.getText().toString());
                    dataSnapshot.getRef().child("Camera").setValue(camera.getText().toString());
                    dataSnapshot.getRef().child("RAM").setValue(ram.getText().toString());
                    dataSnapshot.getRef().child("Color").setValue(color.getText().toString());
                    dataSnapshot.getRef().child("Display").setValue(display.getText().toString());
                    dataSnapshot.getRef().child("ROM").setValue(rom.getText().toString());
                    dataSnapshot.getRef().child("Network").setValue(network.getText().toString());
                    dataSnapshot.getRef().child("Processor").setValue(processor.getText().toString());
                    dataSnapshot.getRef().child("Others").setValue(others.getText().toString());
                    dataSnapshot.getRef().child("YoutubeVideoLink").setValue(youtubevideolink.getText().toString());
                    dataSnapshot.getRef().child("Fingerprint").setValue(fingerprint.getText().toString());
                    dataSnapshot.getRef().child("Sim").setValue(sim.getText().toString());

                    Toast.makeText(UpdateUpcomingProduct.this, "Successfully Updated", Toast.LENGTH_SHORT).show();

                    UpdateUpcomingProduct.this.finish();

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(UpdateUpcomingProduct.this, "Update Failed", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else {
            Toast.makeText(UpdateUpcomingProduct.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public void btndelete_click(View view) {

        if (AppStatus.getInstance(UpdateUpcomingProduct.this).isOnline()) {


            upcomingProductsref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {

                        Toast.makeText(UpdateUpcomingProduct.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                        UpdateUpcomingProduct.this.finish();
                    } else {
                        Toast.makeText(UpdateUpcomingProduct.this, "Delete Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else {
            Toast.makeText(UpdateUpcomingProduct.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }
}