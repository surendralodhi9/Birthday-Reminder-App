package com.example.birthdayreminders;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteBirthdayFragment extends Fragment {


    public TextView mobile;
    Button btnDelete;
    public DeleteBirthdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete_birthday, container, false);

        btnDelete=view.findViewById(R.id.btn_delete_save);
        mobile=view.findViewById(R.id.mobile_delete_text);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                deleteBirthday();
            }
        });

        return view;
    }
    private void deleteBirthday()
    {

        DbHelper dbHelper=new DbHelper(getActivity());

        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        String Mobile=mobile.getText().toString();

        if(Mobile.length()==0)
        {

            Toast.makeText(getActivity(),"Please enter the mobile number",Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.deleteBithday(Mobile,sqLiteDatabase);
        dbHelper.close();
        Toast.makeText(getActivity(),"Birthday deleted successfully",Toast.LENGTH_SHORT).show();

    }

}
