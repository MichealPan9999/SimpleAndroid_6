package com.example.panzq.simpleandroid_6;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.panzq.simpleandroid_6.widget.MyAppWidgetProvider;

public class MainActivity extends AppCompatActivity {

    Button sendNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotification = findViewById(R.id.btn_sendnotification);
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendNotification();
                //sendRemoteNotification();
                Intent intent = new Intent("com.example.panzq.simpleandroid_6.action.CLICK");
                sendBroadcast(intent);
            }
        });
    }

    private void sendNotification() {
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        PendingIntent intent = PendingIntent.getActivity(this, 0,
                new Intent(this, SecondActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentTitle("Notification test");
        builder.setContentText("通知的内容");
        builder.setTicker("状态栏上显示");
        builder.setSmallIcon(R.drawable.ic_launcher_round);
        builder.setContentIntent(intent);
        builder.setWhen(System.currentTimeMillis() + 3000);

        Notification notification = builder.getNotification();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(1, notification);
    }
    private void sendRemoteNotification() {
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher_round);
        Notification notification = builder.getNotification();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_notification);
        remoteViews.setTextViewText(R.id.tv_title,"chapter_6");
        remoteViews.setTextViewText(R.id.tv_content,"通知内容");
        remoteViews.setImageViewResource(R.id.iv_img,R.drawable.ic_launcher_round);
        PendingIntent intent = PendingIntent.getActivity(this, 0,
                new Intent(this, SecondActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.ll_open,intent);
        notification.contentView = remoteViews;
        notification.contentIntent = intent;
        manager.notify(1, notification);
    }
}
