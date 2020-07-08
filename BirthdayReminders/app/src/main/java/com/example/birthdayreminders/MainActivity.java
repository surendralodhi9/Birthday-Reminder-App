package com.example.birthdayreminders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements HomeFragment.BirthdayDbOptionListener {

    public static final String CHANNEL_ID="BirthdayReminder notifications";
    public static final int NOTIFICATION_ID=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To cancel notification automatically
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);

       /* Intent intent=new Intent(MainActivity.this,NotificationReciever.class);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        long curTime=System.currentTimeMillis();
        long nextTime=1000*10;

        alarmManager.set(AlarmManager.RTC_WAKEUP,curTime+nextTime,pendingIntent);
        */


        Calendar calendar=Calendar.getInstance();


        /*
           Change time accordingly
         */

        calendar.set(Calendar.HOUR_OF_DAY,1);
        calendar.set(Calendar.MINUTE,19);
        calendar.set(Calendar.SECOND,0);
        System.out.println(calendar.getTime());

        Intent intent=new Intent(MainActivity.this,NotificationReciever.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

        if(findViewById(R.id.fragment_container)!=null)
        {

            if(savedInstanceState!=null)
            {
                return;
            }
            HomeFragment homeFragment=new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,homeFragment).commit();
        }
    }
    @Override
    public void bdOptionPerformed(int method) {


        switch (method)
        {

            case 0:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TodayBirthdayFragment()).addToBackStack(null).commit();
                break;

            case 1:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DisplayBirthdayFragment()).addToBackStack(null).commit();
                break;
            case 2:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddBirthdayFragment()).addToBackStack(null).commit();
                break;
            case 3:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UpdateBirthdayFragment()).addToBackStack(null).commit();
                break;
            case 4:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DeleteBirthdayFragment()).addToBackStack(null).commit();
                break;
        }

    }

}
