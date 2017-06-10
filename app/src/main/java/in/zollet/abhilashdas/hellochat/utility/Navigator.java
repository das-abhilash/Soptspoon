package in.zollet.abhilashdas.hellochat.utility;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import in.zollet.abhilashdas.hellochat.view.activity.ChatActivity;
import in.zollet.abhilashdas.hellochat.view.activity.LoginActivity;


/**
 * Created by abhilashdas on 25/05/17.
 */

public class Navigator {

    public static void navigateTOUsersScreen(AppCompatActivity context) {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }

    public static void navigateTOLoginScreen(AppCompatActivity context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
