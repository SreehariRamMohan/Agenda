package jrjava.eduhub;

import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SettingsClasses extends AppCompatActivity {
    Set<String> set = new HashSet<String>();
    ArrayList<String> hw = new ArrayList<>();
    Map<String, Object> distractor = new HashMap<String, Object>();
    Button joinClass;
    EditText joinpin;
    EditText joinpass;
    EditText classesname;
    String pin;
    String password;
    String c;
    int count = 0;
    String username;
    DatabaseReference findnameroot = FirebaseDatabase.getInstance().getReference().child("ServerSide").child("ClientClasses");
    DatabaseReference findpinroot = FirebaseDatabase.getInstance().getReference().child("ServerSide").child("ClientClasses").child("PIN");
    DatabaseReference findpassroot;
    DatabaseReference usersideroot = FirebaseDatabase.getInstance().getReference().child("UserSide");
    DatabaseReference nameofclass;
    private static final String TAG = "SettingsClasses";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_classes);
        findpassroot = findnameroot.child("Password");
        username = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        joinClass = (Button)findViewById(R.id.joinClass);
        joinpin = (EditText)findViewById(R.id.joinpin);
        joinpass = (EditText)findViewById(R.id.joinpassword);
        classesname = (EditText)findViewById(R.id.editText3);
        //use the following model for other parts
        joinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin = joinpin.getText().toString().trim();
                password = joinpass.getText().toString().trim();
                c = classesname.getText().toString().trim();
                DatabaseReference className = findnameroot.child(c);
                findnameroot.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    //try .getvalue next
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println(dataSnapshot.child(c).child("PIN").getValue());
                        System.out.println(dataSnapshot.child(c).child("Password").getValue());
                        if(dataSnapshot.child(c).exists() && dataSnapshot.child(c).child("PIN").getValue().equals(pin) && dataSnapshot.child(c).child("Password").getValue().equals(password)){
                            ArrayList<String> adduserarray = (ArrayList<String>) (dataSnapshot.child(c).child("Users").getValue());
                            //ArrayList<String> hwarray = (ArrayList<String>) (dataSnapshot.child(c).child("Homework").getValue());

                            addthem(adduserarray);
                        }
                        else{
                            Toast.makeText(SettingsClasses.this,"The information you inputted was incorrect. Please try again.",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                /*
                if(className.equals(null) || className.child("Password").child(password).getRoot().equals(null) || className.child("PIN").child(pin).getRoot().equals(null)){
                    Toast.makeText(SettingsClasses.this,"The information you inputted was incorrect. Please try again.",Toast.LENGTH_LONG).show();

                }else{
                    addthem();
                }
                */
                /*
                if (className.equals(null)) {
                    Toast.makeText(SettingsClasses.this,"The information you inputted was incorrect. Please try again.",Toast.LENGTH_LONG).show();

                } else {
                    // class exists
                    if (className.child("Password").child(password).equals(null)) {
                        if(className.child(pin).equals(null)) {
                            addthem();
                        }
                        else{
                            Toast.makeText(SettingsClasses.this,"The information you inputted was incorrect. Please try again.",Toast.LENGTH_LONG).show();
                            //nope
                        }

                    }
                    else{
                        Toast.makeText(SettingsClasses.this,"The information you inputted was incorrect. Please try again.",Toast.LENGTH_LONG).show();

                    }
                }
                */


            }
        });

    }
    public void checkifpasswordworks(int i){

    }
    public void addthem(ArrayList<String> currentusers){
        currentusers.add(username);
        //Map<String, Object> map4 = new HashMap<String, Object>();
        //Map<String, Object> map = new HashMap<String, Object>();
        //Map<String, Object> mappin = new HashMap<String, Object>();
        //Map<String, Object> mappass = new HashMap<String, Object>();
        //Map<String, Object> maphwfake = new HashMap<String, Object>();
        //map4.put(username, "");
        findnameroot.child(c).child("Users").setValue(currentusers);
        usersideroot.child(username).child(c).child("PIN").setValue(pin);
        usersideroot.child(username).child(c).child("Password").setValue(password);
        //usersideroot.child(username).child("AllMyHomework").setValue(currenthw);
        //map.put(c, "");
        //mappin.put(pin, "");
        //mappass.put(password, "");
        //usersideroot.child(username).updateChildren(map);
        //usersideroot.child(username).child(c).child("PIN").updateChildren(mappin);
        //usersideroot.child(username).child(c).child("Password").updateChildren(mappass);
        /*
        maphwfake.put("Remove", "");
        usersideroot.child(c).child("Creator").updateChildren(maphwfake);
        */
        /*
        usersideroot.child(c).child("Creator").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator i = dataSnapshot.getChildren().iterator();
                hw.clear();
                while(i.hasNext()){
                    hw.add(((DataSnapshot)i.next()).getKey().replaceAll("/", "-"));

                }
                System.out.print("went to settings datachange");
                dataSnapshot.getRef().child("Remove").removeValue();

                //classhomework.clear();
                //classhomework.addAll(set);

                //arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        usersideroot.child(username).child(c).child("Homework").setValue(hw);
        System.out.print("Added the user successfully. they now have all the data.");
        */
    }




}
