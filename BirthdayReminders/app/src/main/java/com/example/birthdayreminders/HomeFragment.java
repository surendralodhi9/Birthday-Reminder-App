package com.example.birthdayreminders;


import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends  Fragment implements View.OnClickListener{

    private Button btnAdd,btnDisplay,btnToday,btnDelete,btnUpdate;
    private TextView dateDisplay;

    BirthdayDbOptionListener dbOptionListener;




    public HomeFragment() {
        // Required empty public constructor
    }

    public interface BirthdayDbOptionListener{


        public void bdOptionPerformed(int method);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String cur=dtf.format(now);
        cur=cur.substring(0,5);
        dateDisplay=view.findViewById(R.id.todayDateView);
        dateDisplay.setText(dateDisplay.getText()+cur);
        btnAdd=view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        btnDisplay=view.findViewById(R.id.btn_display);
        btnDisplay.setOnClickListener(this);

        btnUpdate=view.findViewById(R.id.btn_update_save);
        btnUpdate.setOnClickListener(this);

        btnDelete=view.findViewById(R.id.btn_delete_save);
        btnDelete.setOnClickListener(this);

        btnToday=view.findViewById(R.id.btn_today);
        btnToday.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View view) {



        switch (view.getId())
        {

            case R.id.btn_today:
                dbOptionListener.bdOptionPerformed(0);
                break;

            case R.id.btn_display:
                dbOptionListener.bdOptionPerformed(1);
                break;
            case R.id.btn_add:
                dbOptionListener.bdOptionPerformed(2);
                break;
            case R.id.btn_update_save:
                dbOptionListener.bdOptionPerformed(3);
                break;
            case R.id.btn_delete_save:
                dbOptionListener.bdOptionPerformed(4);
                break;



        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity=(Activity)context;

        try {


            dbOptionListener = (BirthdayDbOptionListener) activity;
        }
        catch(ClassCastException e)
        {

            throw new ClassCastException(activity.toString()+"Must implement the interface method");
        }
    }
}
