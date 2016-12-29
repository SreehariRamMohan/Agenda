package jrjava.eduhub;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Notes extends AppCompatActivity {
    int fileName = 0;
    public String[] noteData = new String[10];
    Spinner spinneruse;
    ArrayAdapter<CharSequence> adapter;
    String currentFile = "note";
    File file;
    ArrayList<String> arraySpinner = new ArrayList<String>();
    String titleForNotes;
    String currentFileTitle = titleForNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSpinnerData();
        boolean shouldCreateNote = true;
        for(int i = 0; i < arraySpinner.size(); i++) {
            if(arraySpinner.get(i) == "note") {
                shouldCreateNote = false;
            }
        }
        if(shouldCreateNote) {
            arraySpinner.add("note");
        }
       if(spinneruse != null) spinneruse = (Spinner) findViewById(R.id.spinner);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinneruse.setAdapter(adapter);
        adapter.notifyDataSetChanged();

if(spinneruse != null) spinneruse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(getBaseContext(),  adapterView.getItemAtPosition(position) + " selected", Toast.LENGTH_SHORT ).show();
                load(adapterView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        load("note");
        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = (EditText) findViewById(R.id.titleView);
                titleForNotes = title.getText().toString();
                save(titleForNotes);
            }
        });

        Button newNote = (Button) findViewById(R.id.newNote);
        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = (EditText) findViewById(R.id.titleView);
                titleForNotes = title.getText().toString();
                Toast toast = Toast.makeText(getApplicationContext(), "New Note with title " + titleForNotes , Toast.LENGTH_SHORT);
                toast.show();
                EditText n = (EditText) findViewById(R.id.usernotes);
                n.setText("");
                EditText t = (EditText) findViewById(R.id.titleView);
                n.setText("");


            }
        });


        /*
        FileInputStream in = null;
        File file5 = getFileStreamPath("notes.txt");
        String FILENAME = "notes.txt";
        String string = "Diary: CHS hackathon..... EPIC. This project rocks!";
        try {
            in = openFileInput("notes.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            String text = "";
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    text += line;
                }
                Toast toast = Toast.makeText(getApplicationContext(), "Data Retrieved", Toast.LENGTH_SHORT );
                EditText notes = (EditText)findViewById(R.id.usernotes);
                notes.setText(text);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } */

    }

    public void save(String title) {
        File file = new File(getApplicationContext().getFilesDir(), title);
        EditText notes = (EditText)findViewById(R.id.usernotes);
        String data = notes.getText().toString().trim();
        try {
            FileOutputStream fou = openFileOutput(title + ".txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fou);
            try {
                osw.write(data);
                osw.flush();
                osw.close();
                Toast toast = Toast.makeText(getApplicationContext(), "Data Saved in file " + title, Toast.LENGTH_SHORT );
                toast.show();

                EditText titleOfNote = (EditText) findViewById(R.id.titleView);
                String isrepeat = titleOfNote.getText().toString();
                boolean shouldCreate = true;
                for(int i = 0; i < arraySpinner.size(); i++) {
                    if(arraySpinner.get(i) == isrepeat) {
                        shouldCreate = false;
                        break;
                    }
                }
                if(shouldCreate) {
                    arraySpinner.add(isrepeat);
                    if(adapter != null) adapter.notifyDataSetChanged();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void load(String title) {
        FileInputStream in;
        try {
            in = openFileInput(title + ".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            String text = "";
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    text += line;
                }
                Toast toast = Toast.makeText(getApplicationContext(), "Data Retrieved from " + title , Toast.LENGTH_SHORT );
                toast.show();
                EditText notes = (EditText)findViewById(R.id.usernotes);
                notes.setText(text);

                EditText titleOfNote = (EditText) findViewById(R.id.titleView);
                titleOfNote.setText(title);


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void add(View view) {


    }


    public void retrieve(View view) {


    }
    /*public void save(View view) {
        EditText notes = (EditText)findViewById(R.id.usernotes);
        String data = notes.getText().toString().trim();
        try {

            FileOutputStream fou = openFileOutput("notes.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fou);
            try {
                osw.write(data);
                osw.flush();
                osw.close();
                Toast toast = Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT );
                toast.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } */

    @Override
    protected void onStop() {
        super.onStop();
        Toast toast = Toast.makeText(getApplicationContext(), "Attempting to save files..... " , Toast.LENGTH_SHORT );
        toast.show();
        File file = new File(getApplicationContext().getFilesDir(), "SpinnerData");
        String toPut = "";
        for(int i = 0; i < arraySpinner.size(); i++) {
            toPut += arraySpinner.get(i) + " ";
        }

        try {
            FileOutputStream fou = openFileOutput("SpinnerData.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fou);
            try {
                osw.write(toPut);
                osw.flush();
                osw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Toast toast1 = Toast.makeText(getApplicationContext(), "Save Complete Happy Monkey " , Toast.LENGTH_SHORT );
        toast1.show();

    }


    public void loadSpinnerData() {
        FileInputStream in;
        String text = "";
        try {
            in = openFileInput("SpinnerData.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    text += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringTokenizer st = new StringTokenizer(text, " ");
        while(st.hasMoreTokens()) {
            arraySpinner.add(st.nextToken());
        }
        Toast toast1 = Toast.makeText(getApplicationContext(), "Files transfered from Cloud" , Toast.LENGTH_SHORT );
        toast1.show();

    }
}