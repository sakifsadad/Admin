package com.example.dil.admin;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddAvailableProduct extends AppCompatActivity implements View.OnClickListener {
    //    static String TAG = AppCompatActivity.class.getSimpleName();
    private String CategoryName, pname, pprice, pdisplay, pcolor, pram, pmemory, pcamera, pbattery, pprocessor, pnetwork, pfingerprint, pothers, pvideolink, saveCurrentDate, saveCurrentTime;
    private EditText ProductName, Price, Display, Color, RAM, Memory, Camera, Battery, Processor, Network, Fingerprint, Others, YoutubeVideoLink;
    private Button SubmitButton;
    private static Button openCustomGallery;
    private static GridView selectedImageGridView;
    private static final int CustomGallerySelectId = 1;//Set Intent Id
    public static final String CustomGalleryIntentKey = "ImageArray";//Set Intent Key Value
    private String productRandomKey;
    private DatabaseReference ProductRef;
    private ProgressDialog loadingBar;
    private Images images;
    private List<String> selectedImages;
    private List<Uri> seletedImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_available_product);
        initViews();
        setListeners();
        getSharedImages();

        CategoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(this, CategoryName, Toast.LENGTH_SHORT).show();
//        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductRef = FirebaseDatabase.getInstance().getReference().child("AvailableProducts");

        ProductName = (EditText) findViewById(R.id.product_name);
        Price = (EditText) findViewById(R.id.price);
        Display = (EditText) findViewById(R.id.display);
        Color = (EditText) findViewById(R.id.color);
        RAM = (EditText) findViewById(R.id.ram);
        Memory = (EditText) findViewById(R.id.memory);
        Camera = (EditText) findViewById(R.id.camera);
        Battery = (EditText) findViewById(R.id.battery);
        Processor = (EditText) findViewById(R.id.processor);
        Network = (EditText) findViewById(R.id.network);
        Fingerprint = (EditText) findViewById(R.id.fingerprint);
        Others = (EditText) findViewById(R.id.others);
        YoutubeVideoLink = findViewById(R.id.youtube_video_link);
        SubmitButton = (Button) findViewById(R.id.submit);
        loadingBar = new ProgressDialog(this);

        images = new Images();


    }


    //Init all views
    private void initViews() {
        openCustomGallery = (Button) findViewById(R.id.openCustomGallery);
        selectedImageGridView = (GridView) findViewById(R.id.selectedImagesGridView);
    }


    //set Listeners
    private void setListeners() {
        openCustomGallery.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openCustomGallery:
                //Start Custom Gallery Activity by passing intent id
                startActivityForResult(new Intent(AddAvailableProduct.this, CustomGallery_Activity.class), CustomGallerySelectId);
                break;
        }
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }

        });

    }


    protected void onActivityResult(int requestcode, int resultcode, Intent imagereturnintent) {
        super.onActivityResult(requestcode, resultcode, imagereturnintent);
        switch (requestcode) {
            case CustomGallerySelectId:
                if (resultcode == RESULT_OK) {
                    String imagesArray = imagereturnintent.getStringExtra(CustomGalleryIntentKey);//get Intent data
                    //Convert string array into List by splitting by ',' and substring after '[' and before ']'
                    List<String> selectedImages = Arrays.asList(imagesArray.substring(1, imagesArray.length() - 1).split(", "));
                    loadGridView(new ArrayList<String>(selectedImages));//call load gridview method by passing converted list into arrayList
//                    uploadImagesToFirebase(selectedImages);
                    this.selectedImages = selectedImages;
                }
                break;

        }
    }


    //Load GridView
    private void loadGridView(ArrayList<String> imagesArray) {
        GridView_Adapter adapter = new GridView_Adapter(AddAvailableProduct.this, imagesArray, false);
        selectedImageGridView.setAdapter(adapter);
    }

    //Read Shared Images
    private void getSharedImages() {

        //If Intent Action equals then proceed
        if (Intent.ACTION_SEND_MULTIPLE.equals(getIntent().getAction())
                && getIntent().hasExtra(Intent.EXTRA_STREAM)) {
            ArrayList<Parcelable> list =
                    getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);//get Parcelabe list
            ArrayList<String> selectedImages = new ArrayList<>();

            //Loop to all parcelable list
            for (Parcelable parcel : list) {
                Uri uri = (Uri) parcel;//get URI
                String sourcepath = getPath(uri);//Get Path of URI
                selectedImages.add(sourcepath);//add images to arraylist
            }
            loadGridView(selectedImages);//call load gridview
        }


    }


    //get actual path of uri
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }


    private void ValidateProductData() {

        pname = ProductName.getText().toString();
        pprice = Price.getText().toString();
        pdisplay = Display.getText().toString();
        pcolor = Color.getText().toString();
        pram = RAM.getText().toString();
        pmemory = Memory.getText().toString();
        pcamera = Camera.getText().toString();
        pbattery = Battery.getText().toString();
        pprocessor = Processor.getText().toString();
        pnetwork = Network.getText().toString();
        pfingerprint = Fingerprint.getText().toString();
        pothers = Others.getText().toString();
        pvideolink = YoutubeVideoLink.getText().toString();


        if (TextUtils.isEmpty(pname)) {
            Toast.makeText(this, "Product Name is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pprice)) {
//            Toast.makeText(this, "Product Price is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pdisplay)) {
//            Toast.makeText(this, "Display Information is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pcolor)) {
//            Toast.makeText(this, "Product Color is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pram)) {
//            Toast.makeText(this, "Ram Information is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pmemory)) {
//            Toast.makeText(this, "Product Memory Information is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pcamera)) {
//            Toast.makeText(this, "Camera Information is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pbattery)) {
//            Toast.makeText(this, "Battery Information is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pprocessor)) {
//            Toast.makeText(this, "Processor Information is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pnetwork)) {
//            Toast.makeText(this, "Network Information is Mandatory", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pfingerprint)) {
//            Toast.makeText(this, "Fingerprint Information is Mandatory", Toast.LENGTH_SHORT).show();
        } else {
            StoreProductInformation();
//            SaveProductInfoToDatabase();
//            Log.d(TAG, "ValidateProductData: " + selectedImages.size());
//            uploadImagesToFirebase(selectedImages);
        }

    }

    //image upload

    private void uploadImagesToFirebase(final List<String> selectedImages) {

        loadingBar.setTitle("Uploading Images");
        loadingBar.setMessage("Dear Admin please wait, while we are uploading the product images");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        Uri[] uri = new Uri[selectedImages.size()];
        FirebaseApp app = FirebaseApp.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance(app);
        final StorageReference storageRef;

        storageRef = storage.getReference("Available Product Images");

        for (int i = 0; i < selectedImages.size(); i++) {
//            uri[i] = Uri.parse("file:/"+selectedImages.get(i));
            uri[i] = Uri.fromFile(new File(selectedImages.get(i)));
            final StorageReference ref = storageRef.child(uri[i].getLastPathSegment());
            ref.putFile(uri[i])
                    .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                            SaveProductInfoToDatabase();
                            Task<Uri> downloadUrl = ref.getDownloadUrl();
                            downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // Got the download URL for 'users/me/profile.png'
                                    Log.d("HI", "onSuccess: " + uri.toString());
                                    images.getImages().add(uri.toString());
                                    SaveProductInfoToDatabase();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });
                            loadingBar.dismiss();
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println();
                        }
                    });
        }
    }

    private void SaveProductInfoToDatabase() {

        HashMap<String, Object> productMap = new HashMap<>();

        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("CategoryName", CategoryName);
        productMap.put("ProductName", pname);
        productMap.put("Price", pprice);
        productMap.put("Display", pdisplay);
        productMap.put("Color", pcolor);
        productMap.put("RAM", pram);
        productMap.put("Memory", pmemory);
        productMap.put("Camera", pcamera);
        productMap.put("Battery", pbattery);
        productMap.put("Processor", pprocessor);
        productMap.put("Network", pnetwork);
        productMap.put("Fingerprint", pfingerprint);
        productMap.put("Others", pothers);
        productMap.put("YoutubeVideoLink", pvideolink);
        productMap.put("ImageUris", images);

        ProductRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AddAvailableProduct.this, "Product is Added Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            String message = task.getException().toString();
                            Toast.makeText(AddAvailableProduct.this, "Error:" + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void StoreProductInformation() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        uploadImagesToFirebase(selectedImages);


    }


}