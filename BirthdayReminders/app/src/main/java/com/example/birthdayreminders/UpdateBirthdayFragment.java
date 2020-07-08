package com.example.birthdayreminders;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateBirthdayFragment extends Fragment {


    Button btnUpdate;
    EditText mobile,name,email,dob;
    public UpdateBirthdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_update_birthday, container, false);

        btnUpdate=view.findViewById(R.id.btn_update_save);
        mobile=view.findViewById(R.id.mobile_update_text);
        email=view.findViewById(R.id.email_update_text);
        name=view.findViewById(R.id.name_update_text);
        dob=view.findViewById(R.id.dob_update_text);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateBirthday();
            }
        });
        return view;
    }
    private void updateBirthday()
    {

        String Name=name.getText().toString();
        String Email=email.getText().toString();
        String  Mobile=mobile.getText().toString();
        String Dob=dob.getText().toString();

        if(Mobile.length()==0)
        {

            Toast.makeText(getActivity(),"Please enter the mobile number",Toast.LENGTH_SHORT).show();
            return;
        }

        DbHelper dbHelper=new DbHelper(getActivity());

        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        // new AsyncTask(dbHelper.addBirthday(Mobile,Name,Email,Dob,sqLiteDatabase)).execute(new Object());
        dbHelper.updateBirthday(Mobile,Name,Email,Dob,sqLiteDatabase);
        dbHelper.close();
        mobile.setText("");
        email.setText("");
        dob.setText("");
        name.setText("");
        Toast.makeText(getActivity(),"Birthday updated successfully",Toast.LENGTH_SHORT).show();
    }
}