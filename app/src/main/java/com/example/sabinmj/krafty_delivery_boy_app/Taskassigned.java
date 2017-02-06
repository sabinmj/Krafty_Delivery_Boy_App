package com.example.sabinmj.krafty_delivery_boy_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabinmj.krafty_delivery_boy_app.model.BlogPost;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Taskassigned extends AppCompatActivity {

    private RecyclerView mBlogRecycler;
    private DatabaseReference databaseReference;
    StorageReference storageReference= FirebaseStorage.getInstance().getReference("image");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskassigned);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        mBlogRecycler=(RecyclerView)findViewById(R.id.rv);
        mBlogRecycler.setHasFixedSize(true);
        mBlogRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
@Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference dbRef = databaseReference.child("datas");

        FirebaseRecyclerAdapter<BlogPost,BlogViewHolder> adapter=new FirebaseRecyclerAdapter<BlogPost, BlogViewHolder>(
                BlogPost.class,
                R.layout.model,
                BlogViewHolder.class,
                dbRef
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, BlogPost model, int position) {

                viewHolder.setId(model.getId());
                viewHolder.setStatus(model.getStatus());
                viewHolder.setTitle(model.getBlogtitle());
                viewHolder.setDesc(model.getDescription());
                viewHolder.setImage(model.getImageurl());


            }
        };

        mBlogRecycler.setAdapter(adapter);

    }

    public static  class BlogViewHolder extends RecyclerView.ViewHolder {

        private View mview;
        Context co;

        public BlogViewHolder(final View itemView) {
            super(itemView);
            mview = itemView;

            mview.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {

                    TextView tv= (TextView) mview.findViewById(R.id.tvFromLocation);
                    TextView tv2= (TextView) mview.findViewById(R.id.tvToLocation);
                    TextView id= (TextView) mview.findViewById(R.id.tvid);
                    TextView Status= (TextView) mview.findViewById(R.id.tvStatus);


                    Log.d("sabin", String.valueOf(tv.getText()));
                    Intent intent = new Intent(mview.getContext(), MoreDetails.class);
                    intent.putExtra("id", id.getText().toString());
                    intent.putExtra("from", tv.getText().toString());
                    intent.putExtra("to", tv2.getText().toString());
                    

                    mview.getContext().startActivity(intent);

                }
            });

        }
        public void setId(String id){
            String abc=id;
            TextView textView=(TextView)mview.findViewById(R.id.tvid);
            textView.setText(id);
            textView.setVisibility(View.INVISIBLE);
        }
        public void setTitle(String title){
            TextView textView=(TextView)mview.findViewById(R.id.tvFromLocation);
            textView.setText(title);
        }
        public void setDesc(String desc){
            TextView textView=(TextView)mview.findViewById(R.id.tvToLocation);
            textView.setText(desc);

        }
        public void setStatus(String Status){
            TextView textView=(TextView)mview.findViewById(R.id.tvStatus);
            textView.setText(Status);

        }

        public void setImage(String urlv){
            Log.i("sabin", "setImage: " +urlv );
            ImageView imageView=(ImageView)mview.findViewById(R.id.ivsignature);
          /*  Picasso.with(mview.getContext())
                    .load(urlv)
                    .into(imageView);*/
        }

}}






