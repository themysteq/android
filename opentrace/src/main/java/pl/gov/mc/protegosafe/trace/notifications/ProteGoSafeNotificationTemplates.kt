package pl.gov.mc.protegosafe.trace.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import io.bluetrace.opentrace.MainActivity
import io.bluetrace.opentrace.R
import io.bluetrace.opentrace.onboarding.OnboardingActivity
import io.bluetrace.opentrace.services.BluetoothMonitoringService.Companion.PENDING_ACTIVITY
import io.bluetrace.opentrace.services.BluetoothMonitoringService.Companion.PENDING_WIZARD_REQ_CODE

const val ACTIVITY_FOR_INTENT_CLASS = "pl.gov.mc.protegosafe.ui.MainActivity"

class ProteGoSafeNotificationTemplates {

    companion object {

        fun getStartupNotification(context: Context, channel: String): Notification {

            val builder = NotificationCompat.Builder(context, channel)
                .setContentText("Tracer is setting up its antennas")
                .setContentTitle("Setting things up")
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_LOW)
                .setSmallIcon(R.drawable.ic_notification_setting)
                .setWhen(System.currentTimeMillis())
                .setSound(null)
                .setVibrate(null)
                .setColor(ContextCompat.getColor(context, R.color.notification_tint))

            return builder.build()
        }

        fun getRunningNotification(context: Context, channel: String): Notification {

            val intent = Intent(
                context,
                Class.forName(ACTIVITY_FOR_INTENT_CLASS)
            )
            val activityPendingIntent = PendingIntent.getActivity(
                context, PENDING_ACTIVITY,
                intent, 0
            )

            val builder = NotificationCompat.Builder(context, channel)
                .setContentTitle(context.getText(R.string.ps_service_ok_title))
                .setContentText(context.getText(R.string.ps_service_ok_body))
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_LOW)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(activityPendingIntent)
                .setTicker(context.getText(R.string.ps_service_ok_body))
                .setStyle(NotificationCompat.BigTextStyle().bigText(context.getText(R.string.ps_service_ok_body)))
                .setWhen(System.currentTimeMillis())
                .setSound(null)
                .setVibrate(null)
                .setColor(ContextCompat.getColor(context, R.color.notification_tint))

            return builder.build()
        }

        fun lackingThingsNotification(context: Context, channel: String): Notification {
            val intent = Intent(
                context,
                Class.forName(ACTIVITY_FOR_INTENT_CLASS)
            )
            intent.putExtra("lacking_things", true)

            val activityPendingIntent = PendingIntent.getActivity(
                context, PENDING_WIZARD_REQ_CODE,
                intent, 0
            )

            val builder = NotificationCompat.Builder(context, channel)
                .setContentTitle(context.getText(R.string.ps_service_not_ok_title))
                .setContentText(context.getText(R.string.ps_service_not_ok_body))
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_LOW)
                .setSmallIcon(R.drawable.ic_notification_warning)
                .setTicker(context.getText(R.string.service_not_ok_body))
                .addAction(
                    R.drawable.ic_notification_setting,
                    context.getText(R.string.ps_service_not_ok_action),
                    activityPendingIntent
                )
                .setContentIntent(activityPendingIntent)
                .setStyle(NotificationCompat.BigTextStyle().bigText(context.getText(R.string.ps_service_not_ok_body)))
                .setWhen(System.currentTimeMillis())
                .setSound(null)
                .setVibrate(null)
                .setColor(ContextCompat.getColor(context, R.color.notification_tint))

            return builder.build()
        }
    }
}
