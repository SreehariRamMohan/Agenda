package jrjava.eduhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;


    //defining firebaseauth object

    private FirebaseAuth firebaseAuth;
    //DatabaseReference userroot = FirebaseDatabase.getInstance().getReference().child("UserSide");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        System.out.println("hi1");
        //initializing firebase auth object
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseAuth = FirebaseAuth.getInstance();

        System.out.println("hi2");
        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editText);
        editTextPassword = (EditText) findViewById(R.id.editText2);

        buttonSignup = (Button) findViewById(R.id.button3);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        System.out.println("hi3");

    }

    private void registerUser(){
        System.out.println("hi5");
        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        System.out.println("hi6");
        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        System.out.println("wasnt empty");
        //if the email and password are not empty
        //displaying a progress dialog


        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("entered create");
                        //checking if success
                        if(task.isSuccessful()){
                            System.out.println("was successful");
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            //Map<String, Object> map4 = new HashMap<String, Object>();
                            //map4.put(FirebaseAuth.getInstance().getCurrentUser().getUid().toString(), "");
                            //userroot.updateChildren(map4);
                            startActivity(new Intent(getApplicationContext(), ClassesActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        //calling register method on click
        registerUser();
    }

}
