package jrjava.eduhub;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

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


public class HomeworkActivity extends AppCompatActivity {
    String whatclass;
    ArrayAdapter<String> arrayAdapter;
    //ArrayAdapter<String> arrayAdaptermonth;
    //ArrayAdapter<String> arrayAdapterday;
    //ArrayAdapter<String> arrayAdapteryear;
    //Spinner month;
    //Spinner day;
    //Spinner year;
    ArrayList<String> usersclasses = new ArrayList<>();
    //ArrayList<String> months = new ArrayList<>();
    //ArrayList<String> days = new ArrayList<>();
    //ArrayList<String> years = new ArrayList<>();
    //ArrayList<Map> classhomework = new ArrayList<>();
    ArrayList<String> classhomework = new ArrayList<>();
    Button addhw1;
    EditText homeworktoadd1;
    EditText date1;
    Spinner whatclass1;
    //EditText whatclass1;
    String hw;
    String date;
    //ArrayList<String> homework = new ArrayList<>();
    DatabaseReference hwroot;
    DatabaseReference hwroot2;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("UserSide").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
       /*
       for(int i = 1; i<13; i++){
           months.add(String.valueOf(i));
       }
       for(int i = 1971; i<2050; i++){
           years.add(String.valueOf(i));
       }
       for(int i = 1; i<32; i++){
           days.add(String.valueOf(i));
       }

       month = (Spinner)findViewById(R.id.spinner2);
       day = (Spinner)findViewById(R.id.spinner3);
       year = (Spinner)findViewById(R.id.spinner4);
       month.setEnabled(false);
       day.setEnabled(false);
       year.setEnabled(false);
       */
        addhw1 = (Button) findViewById(R.id.addhomework);
        homeworktoadd1 = (EditText) findViewById(R.id.homeworktoadd);
        date1 = (EditText) findViewById(R.id.date);
        whatclass1 = (Spinner)findViewById(R.id.classspinner);
        //whatclass1 = (EditText) findViewById(R.id.whatclass);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, usersclasses);
       /*
       arrayAdaptermonth = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, months);
       arrayAdapterday = new ArrayAdapter<String>(HomeworkActivity.this, android.R.layout.simple_spinner_dropdown_item, days);
       //arrayAdapterday = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
       //arrayAdapteryear = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);
       month.setAdapter(arrayAdaptermonth);
       day.setAdapter(arrayAdapterday);

       //day.setAdapter(arrayAdapterday);
       //year.setAdapter(arrayAdapteryear);

       month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               date = (String) (adapterView.getItemAtPosition(i).toString());
               day.setEnabled(true);
               Toast.makeText(HomeworkActivity.this, "Please continue to the 'date' option", Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
               month.performClick();
           }
       });
       day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(Integer.valueOf(adapterView.getItemAtPosition(i).toString()) > 29 && date == String.valueOf(2)
                       || Integer.valueOf(adapterView.getItemAtPosition(i).toString()) > 30 && date == String.valueOf(4)
                       || Integer.valueOf(adapterView.getItemAtPosition(i).toString()) > 30 && date == String.valueOf(9)
                       || Integer.valueOf(adapterView.getItemAtPosition(i).toString()) > 30 && date == String.valueOf(6)
                       || Integer.valueOf(adapterView.getItemAtPosition(i).toString()) > 30 && date == String.valueOf(11)){
                   Toast.makeText(HomeworkActivity.this, "Not a valid date. Please enter a valid date, and continue to the 'year' option.",
                           Toast.LENGTH_SHORT).show();
               }
               date += "/" + (String) (adapterView.getItemAtPosition(i).toString());
               year.setEnabled(true);
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
               month.performClick();
           }
       });
       year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               date += "/" + (String) (adapterView.getItemAtPosition(i).toString());
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
               year.performClick();
           }
       });
       */
        whatclass1.setAdapter(arrayAdapter);
        whatclass1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                whatclass = (String) (adapterView.getItemAtPosition(i).toString());
                //month.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Went into hwactivity eventlistener");
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while(i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());

                }
                usersclasses.clear();
                usersclasses.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        addhw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hwroot2 = FirebaseDatabase.getInstance().getReference().child("ServerSide").child("ClientClasses").child(whatclass.toString().trim()).child("Homework");
                //hwroot = FirebaseDatabase.getInstance().getReference().child("UserSide").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("AllMyHomework");
                hw = homeworktoadd1.getText().toString().trim();
                date = date1.getText().toString();




                Map<String, Object> map = new HashMap<String, Object>();
                map.put(hw, date.replaceAll("/","-"));
                //classhomework.add(map);
                //classhomework.add(hw + " due " + date);
                //hwroot.updateChildren(map);
                hwroot2.updateChildren(map);
                //hwroot2.setValue(classhomework);
                Toast.makeText(HomeworkActivity.this, "Homework added to Class: " + whatclass.toString().trim(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}