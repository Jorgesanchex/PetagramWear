package com.miapp.petagramwear;

import android.app.PendingIntent;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message){
        String idUsuario = message.getData().get("id_usuario_instagram");

        // Acción 1: Ver mi perfil
        Intent perfilIntent = new Intent(this, PerfilActivity.class);
        perfilIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent perfilPending = PendingIntent.getActivity(this, 0, perfilIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Acción 2: Dar Follow/Unfollow
        Intent followIntent = new Intent(this, FollowService.class);
        followIntent.putExtra("id_usuario_instagram", idUsuario);
        PendingIntent followPending = PendingIntent.getService(this, 0, followIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Acción 3: Ver Usuario
        Intent usuarioIntent = new Intent(this, UsuarioActivity.class);
        usuarioIntent.putExtra("id_usuario_instagram", idUsuario);
        usuarioIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent usuarioPending = PendingIntent.getActivity(this, 0, usuarioIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Crear la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_01")
                .setSmallIcon(R.drawable.ic_notif)
                .setContentTitle("Alguien interactuó con tu foto")
                .setContentText("¡Tienes acciones disponibles!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.ic_notif, "Ver mi Perfil", perfilPending)
                .addAction(R.drawable.ic_notif, "Follow/Unfollow", followPending)
                .addAction(R.drawable.ic_notif, "Ver Usuario", usuarioPending)
                .setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(1, builder.build());
    }
}
