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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayBirthdayFragment extends Fragment {


    public TextView Text_display;
    public DisplayBirthdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_display_birthday, container, false);

        Text_display=view.findViewById(R.id.txt_display);
        displayBirthday();
        return view;

    }
    private void displayBirthday()
    {

        DbHelper dbHelper=new DbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();

        Cursor cursor=dbHelper.displayBirthday(sqLiteDatabase);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String cur=dtf.format(now);
        cur=cur.substring(0,5);
        cur=reverseString(cur);

        String info="";
        List<Birthday> BeforeToday=new ArrayList<>();
        List<Birthday> AfterToday=new ArrayList<>();

        while(cursor.moveToNext()) {

            String mobile = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.mobile));
            String name = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.name));
            String email = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.email));
            String dob = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.dob));

            dob=reverseString(dob);
            Birthday birthday = new Birthday(name, mobile, email, dob);

            if(cur.compareToIgnoreCase(dob)<=0)
                AfterToday.add(birthday);
            else
                BeforeToday.add(birthday);

        }
        Collections.sort(AfterToday,new AscendingSort());
        Collections.sort(BeforeToday,new AscendingSort());
        for(int i=0;i<AfterToday.size();i++)
        {

            Birthday birthday=AfterToday.get(i);
            birthday.Dob=reverseString(birthday.Dob);
            if (info.length() > 0)
                info = info + "\n\n";
            info = info + "Name: " + birthday.Name + "\nMobile: " +birthday.Mobile + "\nEmail: " + birthday.Email + "\nDOB: " + birthday.Dob;

        }
        for(int i=0;i<BeforeToday.size();i++)
        {

            Birthday birthday=BeforeToday.get(i);
            birthday.Dob=reverseString(birthday.Dob);
            if (info.length() > 0)
                info = info + "\n\n";
            info = info + "Name: " + birthday.Name + "\nMobile: " +birthday.Mobile + "\nEmail: " + birthday.Email + "\nDOB: " + birthday.Dob;

        }




        if(info.length()==0)
            info="No birthday entry found... Please add ";
        Text_display.setText(info);
        dbHelper.close();


    }
    public String  reverseString(String str)
    {
        String res="";
        for(int i=str.length()-1;i>=0;i--)
            res=res+str.charAt(i);
        return res;
    }

}
class AscendingSort implements Comparator<Birthday>
{

    public int compare(Birthday a,Birthday b )
    {
        return a.Dob.compareToIgnoreCase(b.Dob);

    }

}
