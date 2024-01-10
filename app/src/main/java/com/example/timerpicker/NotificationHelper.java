package com.example.timerpicker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

/**
 * The NotificationHelper class
 * <p>
 * This class concentrates two methods to fire demo notification
 * </p>
 *
 * @author Levy Albert albert.school2015@gmail.com
 * @version 2.0
 * @since 21 /7/2023
 */
public class NotificationHelper {
    private static final String CHANNEL_ID = "Your_Channel_ID";
    private static final String CHANNEL_NAME = "Your_Channel_Name";
    private static final int NOTIFICATION_ID = 1;

    /**
     * showNotification method
     * <p> Demonstrate basic notification building & firing
     * </p>
     *
     * @param context the context object that triggered the method
     * @param text the string to show in the notification
     */
    public static void showNotification(Context context, String text) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notiBbuilder = new
                NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Notifications Demo App")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(NOTIFICATION_ID, notiBbuilder.build());
    }

    /**
     * showNotificationBtn method
     * <p> Demonstrate a notification with one button building & firing
     * </p>
     *
     * @param context the context object that triggered the method
     * @param text the string to show in the notification
     */
    public static void showNotificationBtn(Context context, String text) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("notification_clicked", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Notifications Demo App")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(android.R.drawable.ic_menu_close_clear_cancel,
                        "OK", pendingIntent)
                .setAutoCancel(true);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}