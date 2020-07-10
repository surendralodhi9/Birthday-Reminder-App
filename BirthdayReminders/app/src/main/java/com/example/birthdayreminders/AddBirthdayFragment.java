package com.example.birthdayreminders;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBirthdayFragment extends Fragment {



    Button btnSave;
    EditText mobile,name,email,dob;
    public AddBirthdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_birthday, container, false);

        btnSave=view.findViewById(R.id.btn_save);
        mobile=view.findViewById(R.id.Mobile_text);
        email=view.findViewById(R.id.Email_text);
        name=view.findViewById(R.id.Name_text);
        dob=view.findViewById(R.id.Dob_text);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Name=name.getText().toString().trim();
                String Email=email.getText().toString().trim();
                String  Mobile=mobile.getText().toString().trim();
                String Dob=dob.getText().toString().trim();

                if(Mobile.length()==0)
                {

                    Toast.makeText(getActivity(),"Please enter the mobile number",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!isValidDate(Dob))
                {
                    Toast.makeText(getActivity(),"Please enter the DOB in correct format",Toast.LENGTH_SHORT).show();
                    return;
                }

                DbHelper dbHelper=new DbHelper(getActivity());

                SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

               // new AsyncTask(dbHelper.addBirthday(Mobile,Name,Email,Dob,sqLiteDatabase)).execute(new Object());
                dbHelper.addBirthday(Mobile,Name,Email,Dob,sqLiteDatabase);
                dbHelper.close();
                mobile.setText("");
                email.setText("");
                dob.setText("");
                name.setText("");

                Toast.makeText(getActivity(),"Birthday added successfully",Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
    public boolean isValidDate(String str)
    {
        if(str.length()>5||str.length()<5||str.charAt(2)!='/')
            return false;

        for(int i=0;i<str.length();i++)
        {
            if(i==2)
                continue;
            if(str.charAt(i)<'0'|| str.charAt(i)>'9')
                return false;
        }
        int dd=Integer.parseInt(str.substring(0,2));
        int mm=Integer.parseInt(str.substring(3));
        if(mm<1||mm>12)
            return false;
        if(mm==4||mm==6||mm==9||mm==11)
        {
            if(dd>30)
                return false;
        }
        if(dd<1||dd>31)
            return false;
        return true;
    }

}
