package com.ziad.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CategoriesActivity extends AppCompatActivity {

    EditText Type;
    EditText CatName;
    EditText CatNum;
    EditText CatPrice;
    Button Save;
    DatabaseReference reference;
    Categories categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Type = findViewById(R.id.CatType);
        CatName = findViewById(R.id.CatName);
        CatNum = findViewById(R.id.CatNum);
        CatPrice = findViewById(R.id.CatPrice);
        Save = findViewById(R.id.Save);




        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Categories");
       Save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               categories = new Categories();
               setCategories(categories);
               reference.child("Categories").child(categories.getType()).child(categories.getName()).setValue(setCategories(categories));
           }
       });

    }



    protected Categories setCategories(Categories categories){
        categories.type = Type.getText().toString();
        categories.Name = CatName.getText().toString();
        categories.Number = CatNum.getText().toString();
        categories.Price = CatPrice.getText().toString();
        return categories;
    }
}
