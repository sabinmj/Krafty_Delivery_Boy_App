package com.example.sabinmj.krafty_delivery_boy_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MoreDetails extends AppCompatActivity {
    String id;
    TextView from;
    TextView to;
    Button Reached;
    Button Cancel;
    DatabaseReference databaseReference;
    public static final int SIGNATURE_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);


        from = (TextView) findViewById(R.id.tvfromprev);
        to = (TextView) findViewById(R.id.tvToPrev);

        Reached = (Button) findViewById(R.id.btnactivate);
        Cancel = (Button) findViewById(R.id.btncancel);
        Bundle extras = getIntent().getExtras();


        //  Log.d("sabin",from1);
        if (extras != null) {
            String from1 = extras.getString("from");
            String to1 = extras.getString("to");
            this.id = extras.getString("id");
            from.setText(from1);
            to.setText(to1);
        }

    }

    public void Status_update(View view) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbRef = databaseReference.child("datas").child(id);

        Intent intent = new Intent(this, Taskassigned.class);
        startActivity(intent);

        Map<String, Object> taskMap = new HashMap<String, Object>();
        taskMap.put("status", "COMPLETED");
        dbRef.updateChildren(taskMap);

    }

    public void Cancel_from_Database(View view) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbRef = databaseReference.child("datas");
        dbRef.child(id).removeValue();
        Toast.makeText(this, "Your task deleted...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void upload_image(View view) {
        Intent intent = new Intent(MoreDetails.this, CaptureSignature.class);
        startActivityForResult(intent, SIGNATURE_ACTIVITY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SIGNATURE_ACTIVITY:
                if (resultCode == RESULT_OK) {

                    Bundle bundle = data.getExtras();
                    String status = bundle.getString("status");
                    if (status.equalsIgnoreCase("done")) {
                        Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 105, 50);
                        toast.show();
                    }
                }
                break;
        }

    }
}
