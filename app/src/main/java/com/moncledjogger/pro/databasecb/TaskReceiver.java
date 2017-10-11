package com.moncledjogger.pro.databasecb;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by user 3 on 16-07-2017.
 */

public class TaskReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        PendingIntent pendingIntent=PendingIntent.getActivity(context,123,new Intent())
        NotificationManager nm= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification= new Notification.Builder(context)
                .setContentText("This task is pending")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();
    }
}
