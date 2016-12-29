package jrjava.eduhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ClassFilesActivity extends AppCompatActivity {
    //String[] pins1 = new String[1];
    //String[] passes1 = new String[1];
    TextView pinandpassword;
    String infoofclass;
    ListView listviewhw;
    String pin;
    String passclass;
    TextView nameofclass;
    String thename;
    String user;
    Button pinpass;
    LinearLayout linearLayout;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> classhomework = new ArrayList<>();
    DatabaseReference root;
    DatabaseReference rootpin;
    DatabaseReference rootpass;
    DatabaseReference serverroot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_files);
        pinandpassword = (TextView)findViewById(R.id.pinandpass);
        linearLayout = (LinearLayout)findViewById(R.id.layoutl);
        nameofclass = (TextView)findViewById(R.id.title);
        thename = (String) (getIntent().getExtras().get("ClassName"));
        user = (String) (getIntent().getExtras().get("Username"));
        pinpass = (Button)findViewById(R.id.pinpass);
        nameofclass.setText(thename);
        rootpin =  FirebaseDatabase.getInstance().getReference().child("UserSide").child(user).child(thename).child("PIN");
        rootpass = FirebaseDatabase.getInstance().getReference().child("UserSide").child(user).child(thename).child("Password");

        rootpin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ArrayList<String> pins = new ArrayList<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                System.out.println("went to datachange");
                /*
                while(i.hasNext()){
                    pins.add(((DataSnapshot)i.next()).getKey());

                }
                */
                pin = dataSnapshot.getValue().toString();
                System.out.println(pin);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        rootpass.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ArrayList<String> passes = new ArrayList<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                /*
                while(i.hasNext()){
                    passes.add(((DataSnapshot)i.next()).getKey());

                }
                */
                passclass = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //pin = joinpin.getText().toString().trim();
        //password = joinpass.getText().toString().trim();



        pinpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoofclass = "(Pin: " + pin + ", Password: " + passclass + ")";
                pinandpassword.setText(infoofclass);
            }
        });
        //root = FirebaseDatabase.getInstance().getReference().child("UserSide").child(user).child(thename).child("Homework");
        serverroot = FirebaseDatabase.getInstance().getReference().child("ServerSide").child("ClientClasses").child(thename).child("Homework");

        listviewhw = (ListView)findViewById(R.id.listofhw);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classhomework);
        listviewhw.setAdapter(arrayAdapter);
        serverroot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Set<String> set = new HashSet<String>();
                ArrayList<String> addingarray = (ArrayList<String>) (dataSnapshot.getValue());
                /*
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey().replaceAll("-", "/"));

                }
                */
                classhomework.clear();
                classhomework.addAll(addingarray);


                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
