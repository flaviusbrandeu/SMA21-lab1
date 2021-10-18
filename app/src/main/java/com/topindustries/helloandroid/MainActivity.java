package com.topindustries.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedHashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText eName;
    Button bClick;
    TextView tName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName = (EditText) findViewById(R.id.eName);
        bClick = (Button) findViewById(R.id.bClick);
        tName = (TextView) findViewById(R.id.tName);
        TypedArray ta = getResources().obtainTypedArray(R.array.buttonTextColors);
        Set<String> buttonColorNames = new LinkedHashSet<String>();
        for (int i = 0; i < ta.length(); i++) {
            buttonColorNames.add(getResources().getResourceEntryName(ta.getResourceId(i, R.color.black)));
        }
        Spinner spinner = (Spinner) findViewById(R.id.colorsSpinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, buttonColorNames.toArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void clicked(View view) {
        switch (view.getId()) {
            case R.id.bClick:
                String userName = eName.getText().toString();
                tName.setText("Hello, " + userName);
                GreetingsDialog greetingsDialog = new GreetingsDialog(userName);
                greetingsDialog.show(getSupportFragmentManager(), "missiles");
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String colorName = parent.getItemAtPosition(pos).toString();
        int colorResourceId = getResources().getIdentifier(colorName, "color", this.getPackageName());
        int color = getResources().getColor(colorResourceId);
        bClick.setTextColor(color);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}