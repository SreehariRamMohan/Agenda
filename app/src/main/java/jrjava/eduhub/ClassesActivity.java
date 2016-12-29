package jrjava.eduhub;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class ClassesActivity extends AppCompatActivity {
    Button classes1;
    Button settings;
    Button signout;
    Button notes;
    Button study;
    Button homework1;
    Button calendar;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("UserSide");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        classes1 = (Button)findViewById(R.id.classes);
        settings = (Button)findViewById(R.id.buttonsettings);
        homework1 = (Button)findViewById(R.id.homework);
        signout = (Button)findViewById(R.id.signout);
        study = (Button)findViewById(R.id.study);
        notes = (Button)findViewById(R.id.notes);
        calendar = (Button)findViewById(R.id.calendar);
        calendar.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
            }
        }));
        classes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyClassesActivity.class));
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsClasses.class));
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        homework1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeworkActivity.class));
            }
        });
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Stopwatch.class));
            }
        });
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notes.class));
            }
        });




    }

}
