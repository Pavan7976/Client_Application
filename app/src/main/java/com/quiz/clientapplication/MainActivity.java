package com.quiz.clientapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference Database;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rrecyclerview);

        Database = FirebaseDatabase.getInstance().getReference().child("Data").child("project");

        Database.keepSynced(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));



    }
    @Override
    public void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<model, ViewHolder> firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<model, ViewHolder>(
                model.class, R.layout.item, ViewHolder.class,Database.orderByChild("name")) {

            @Override
            protected void populateViewHolder(ViewHolder Holder, model model1, final int position) {
                Holder.setName(model1.getName());

                Holder.setUrl( model1.getUrl());

            }


        };

        recyclerView.setAdapter(firebaseRecyclerAdapter1);
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name) {
            TextView title1 = (TextView) mView.findViewById(R.id.text_view);
            title1.setText(name);
        }


        public void setUrl(String url) {
            ImageView imageView = (ImageView) mView.findViewById(R.id.image_view);
            Picasso.get().load(url).into(imageView);


            initRecyclerView();


        }
        private void initRecyclerView(){

        }
    }


}


