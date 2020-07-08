package com.example.birthdayreminders;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService;

public class NotificationReciever extends BroadcastReceiver {


    public static final String CHANNEL_ID="BirthdayReminder notifications";
    public static final int NOTIFICATION_ID=100;

    @Override
    public void onReceive(Context context, Intent intent) {


        ComponentName comp = new ComponentName(context.getPackageName(),
                Intent.class.getName());
        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);

        createNotificationChannel(context);
        displayNotification(context);

        /*Intent repeating_intent=new Intent(context,MainActivity.class);

        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID);

        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("Birthday Reminder");
        builder.setContentText("Check today's birthday");
        builder.setAutoCancel(true);

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,builder.build());*/


    }
    public void displayNotification(Context context) {




        Intent landingIntent=new Intent(context,MainActivity.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPandingIntent=PendingIntent.getActivity(context,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID);

        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("Birthday Reminder");
        builder.setContentText("Check today's birthday");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        builder.setContentIntent(landingPandingIntent);

        NotificationManagerCompat notificationManagerCompat;
        notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());



    }

    private void createNotificationChannel(Context context)
    {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {

            CharSequence name="Personal Notification";
            String description="Include all the personal notification";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }
}
