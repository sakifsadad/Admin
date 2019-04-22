package com.example.dil.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateAvailableProduct extends AppCompatActivity {

    private String productID = "";
    private EditText productName, price, display, color, ram, rom, sim, battery, processor, network, camera,fingerprint, youtubevideolink, others;

    DatabaseReference availableProductsref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadte_available_product);

        productName = (EditText) findViewById(R.id.edit_name);
        price = (EditText) findViewById(R.id.edit_price);
        display = (EditText) findViewById(R.id.edit_display);
        color = (EditText) findViewById(R.id.edit_color);
        ram = (EditText) findViewById(R.id.edit_ram);
        rom = (EditText) findViewById(R.id.edit_memory);
        sim = (EditText) findViewById(R.id.edit_sim);
        battery = (EditText) findViewById(R.id.edit_battery);
        processor = (EditText) findViewById(R.id.edit_processor);
        network = (EditText) findViewById(R.id.edit_network);
        camera = (EditText) findViewById(R.id.edit_camera);
        fingerprint = (EditText) findViewById(R.id.edit_fingerprint);
        youtubevideolink = (EditText) findViewById(R.id.edit_youtube_video_link);
        others = (EditText) findViewById(R.id.edit_others);


        productID = getIntent().getStringExtra("pid").toString();
        availableProductsref = FirebaseDatabase.getInstance().getReference().child(productID);
        productName.setText(getIntent().getStringExtra("ProductName"));
        price.setText(getIntent().getStringExtra("Price"));
        display.setText(getIntent().getStringExtra("Display"));
        color.setText(getIntent().getStringExtra("Color"));
        ram.setText(getIntent().getStringExtra("RAM"));
        rom.setText(getIntent().getStringExtra("Memory"));
        sim.setText(getIntent().getStringExtra("Sim"));
        battery.setText(getIntent().getStringExtra("Battery"));
        processor.setText(getIntent().getStringExtra("Processor"));
        network.setText(getIntent().getStringExtra("Network"));
        camera.setText(getIntent().getStringExtra("Camera"));
        fingerprint.setText(getIntent().getStringExtra("Fingerprint"));
        youtubevideolink.setText(getIntent().getStringExtra("YoutubeVideoLink"));
        others.setText(getIntent().getStringExtra("Others"));


    }

    public void btndelete_click(View view) {

        availableProductsref.child("pid").setValue(productID);
    }

    public void btnupdate_click(View view) {

        availableProductsref.removeValue();
    }
}
