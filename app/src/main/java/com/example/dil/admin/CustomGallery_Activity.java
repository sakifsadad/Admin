package com.example.dil.admin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SONU on 31/10/15.
 */
public class CustomGallery_Activity extends AppCompatActivity implements View.OnClickListener {
    private static Button selectImages;
    private static GridView galleryImagesGridView;
    private static ArrayList<String> galleryImageUrls;
    private static GridView_Adapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gallery_);
        setTitle("You Can Select Only 5 Images");

        initViews();
        setListeners();
        fetchGalleryImages();
        setUpGridView();
    }

    //Init all views
    private void initViews() {
        selectImages = (Button) findViewById(R.id.selectImagesBtn);
        galleryImagesGridView = (GridView) findViewById(R.id.galleryImagesGridView);

    }

    //fetch all images from gallery
    private void fetchGalleryImages() {
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};//get all columns of type images
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;//order data by date
        Cursor imagecursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy + " DESC");//get all data in Cursor by sorting in DESC order

        galleryImageUrls = new ArrayList<String>();//Init array


        //Loop to cursor count
        for (int i = 0; i < imagecursor.getCount(); i++) {
            imagecursor.moveToPosition(i);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);//get column index
            galleryImageUrls.add(imagecursor.getString(dataColumnIndex));//get Image from column index
            System.out.println("Array path" + galleryImageUrls.get(i));
        }


    }

    //Set Up GridView method
    private void setUpGridView() {
        imagesAdapter = new GridView_Adapter(CustomGallery_Activity.this, galleryImageUrls, true);
        galleryImagesGridView.setAdapter(imagesAdapter);
    }

    //Set Listeners method
    private void setListeners() {
        selectImages.setOnClickListener(this);
    }


    //Show hide select button if images are selected or deselected
    public void showSelectButton() {
        ArrayList<String> selectedItems = imagesAdapter.getCheckedItems();
        if (selectedItems.size() > 0) {
            selectImages.setText(selectedItems.size() + " - Images Selected");
            selectImages.setVisibility(View.VISIBLE);

//            imageLimit();
        } else
            selectImages.setVisibility(View.GONE);

    }

//    private void imageLimit() {
//        ArrayList<String> selectedItems = imagesAdapter.getCheckedItems();
//        if (selectedItems.size()>5){
//            Toast.makeText(this,"WTF", Toast.LENGTH_SHORT).show();
//
//        }
//
//    }

    @Override
    public void onClick(View view) {
        ArrayList<String> selectedItems = imagesAdapter.getCheckedItems();

        if (selectedItems.size() <= 5) {
            switch (view.getId()) {
                case R.id.selectImagesBtn:

                    //When button is clicked then fill array with selected images
//                ArrayList<String> selectedItems = imagesAdapter.getCheckedItems();

                    //Send back result to Addproduct with selected images
                    Intent intent = new Intent();
                    intent.putExtra(AddAvailableProduct.CustomGalleryIntentKey, selectedItems.toString());//Convert Array into string to pass data
                    setResult(RESULT_OK, intent);//Set result OK
                    finish();//finish activity
                    break;

            }

        }

        else {
            Toast.makeText(this,"You can select only 5 images", Toast.LENGTH_SHORT).show();
//            Snackbar snackbar = Snackbar.make(view, "A short message is !", Snackbar.LENGTH_LONG);
        }
        }
    }

