package com.ziad.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class ImageActivity extends AppCompatActivity {
    Uri selectedImageUri;
    ImageView imageView;
    Button Choose;
    Button Upload;
    Button Download;
    StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView =findViewById(R.id.Choose_img);
        Choose = findViewById(R.id.btn_Choose);
        Upload =findViewById(R.id.btn_Upload);
        Download =findViewById(R.id.btn_download);
        selectedImageUri =Uri.fromFile(new File(String.valueOf(imageView.getTag())));
        reference = FirebaseStorage.getInstance().getReference("image/");

        Choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ImageChoose();
            }
        });

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(selectedImageUri.getPath()).putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(ImageActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ImageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

    protected void ImageChoose(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 200);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {


            if (requestCode == 200) {

                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }
}