package com.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dotaustere.design.R;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class UserProfileActivity extends AppCompatActivity {

    ImageView arrowBack;
    EditText userEmail;
    EditText userName;
    AutoCompleteTextView userGender;
    EditText dob;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //select gender
        findIds();


        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this,UserNameActivity.class);
                startActivity(intent);
            }
        });

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, getResources()
                .getStringArray(R.array.Gender_Names));

        userGender.setAdapter(arrayAdapter);
        userGender.setCursorVisible(false);
        userGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userGender.showDropDown();
                String selection;
                selection = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selection,
                        Toast.LENGTH_SHORT);

            }
        });

        userGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                userGender.showDropDown();
            }
        });

        dob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 2 || charSequence.length() == 5 ) {
                    if (!Pattern.compile("([0-9]{2})/").matcher(charSequence).matches()) {
                        dob.setText(new StringBuilder(dob.getText().toString()).insert(charSequence.length(), "/").toString());
                        dob.setSelection(dob.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            //select DOB
            dob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            UserProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            String date = day+"/"+month+"/"+year;
                            dob.setText(date);
                        }
                    },year,month,day);
                    datePickerDialog.show();
//                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                            setListener,year,month,day);
//                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    datePickerDialog.show();
                }
            });
            setListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month+1;
                    String date = day+"/"+month+"/"+year;
                    dob.setText(date);
                }
            };



            





    }

    private void findIds() {

        userGender = findViewById(R.id.userGender);
        userEmail = findViewById(R.id.userEmail);
        userName = findViewById(R.id.userName);
        dob = findViewById(R.id.dateOfBirth);
        arrowBack = findViewById(R.id.backArrow);



    }
}