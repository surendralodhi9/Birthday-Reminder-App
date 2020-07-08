package com.example.birthdayreminders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayBirthdayFragment extends Fragment {


    public TextView Text_Today;
    public TodayBirthdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_today_birthday, container, false);
        Text_Today=view.findViewById(R.id.txt_display_today);





        //Notification





        displayBirthday();

        return view;
    }


    private void displayBirthday()
    {

        DbHelper dbHelper=new DbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();

        Cursor cursor=dbHelper.displayBirthday(sqLiteDatabase);


        String info="";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String cur=dtf.format(now);
        cur=cur.substring(0,5);
        //info=info+cur+"\n\n";

        while(cursor.moveToNext())
        {
            String mobile=cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.mobile));
            String name=cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.name));
            String email=cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.email));
            String dob=cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.dob));

            if(dob.equalsIgnoreCase(cur)) {

                if (info.length() > 0)
                    info = info + "\n\n";
                info = info + "Name: " + name + "\nMobile: " + mobile + "\nEmail: " + email + "\nDOB: " + dob;
            }

        }

        if(info.length()==0)
            info="Today is no birthday";
        Text_Today.setText(info);
        dbHelper.close();


    }

}
