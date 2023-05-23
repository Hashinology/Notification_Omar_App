/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hashinology.notification_omar_app.util

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.hashinology.notification_omar_app.MainActivity
import com.hashinology.notification_omar_app.R
import com.hashinology.notification_omar_app.receiver.SnoozeReceiver


// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

// TODO: Step 1.1 extension function to send messages (GIVEN)
/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
@SuppressLint("WrongConstant")
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // Create the content intent for the notification, which launches
    // this activity
    // TODO: Step 1.11 create intent
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // TODO: Step 1.12 create PendingIntent
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_MUTABLE
    )
    // TODO: Step 2.0 add style
    val eggImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.cooked_egg
    )
    val bigPictureStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        //large icon goes away when notification is expanded
//        .bigLargeIcon(null)

    // TODO: Step 2.2 add snooze action
    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        REQUEST_CODE,
        snoozeIntent,
        PendingIntent.FLAG_MUTABLE
    )



    // TODO: Step 1.2 get an instance of NotificationCompat.Builder
    // Build the notification
    // notification compat used instead of notification to support all devices versions
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.egg_notification_channel_id)
    )




    // TODO: Step 1.8 use the new 'breakfast' notification channel



    // TODO: Step 1.3 set title, text and icon to builder
        .setSmallIcon(R.drawable.egg_icon)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentTitle(messageBody)

    // TODO: Step 1.13 set content intent
        .setContentIntent(contentPendingIntent)
            //notification disappear itself when user click it
        .setAutoCancel(true)

        // TODO: Step 2.1 add style to builder
        .setStyle(bigPictureStyle)
        .setLargeIcon(eggImage)
        // TODO: Step 2.3 add snooze action
        .addAction(
            R.drawable.egg_icon,
            applicationContext.getString(R.string.snooze),
            snoozePendingIntent
        )
        // TODO: Step 2.5 set priority
            //support devices lower than 25
        .setPriority(NotificationCompat.PRIORITY_HIGH)


    // TODO: Step 1.4 call notify
    notify(NOTIFICATION_ID,builder.build())
}

// TODO: Step 1.14 Cancel all notifications
fun NotificationManager.cancelNotifications(){
    cancelAll()
}
