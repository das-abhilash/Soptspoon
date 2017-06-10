package in.zollet.abhilashdas.hellochat.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import in.zollet.abhilashdas.hellochat.R;

import static in.zollet.abhilashdas.hellochat.utility.Navigator.navigateTOLoginScreen;
import static in.zollet.abhilashdas.hellochat.utility.Navigator.navigateTOUsersScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser(); // TODO: 25/05/17 add dagger here

        if(currentUser == null) {
            // redirecct to login screen
            navigateTOLoginScreen(this);
            finish();
        } else {
            // redirect to chat activty
            navigateTOUsersScreen(this);
            finish();
        }

    }
}
