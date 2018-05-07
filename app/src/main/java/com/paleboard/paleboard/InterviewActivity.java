package com.paleboard.paleboard;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;

public class InterviewActivity extends AppCompatActivity {
    // TODO (0) : Make UI for Interview layout, with list of interviews

    private static final int INTERVIEW_LIST_REQUEST = 1;

    /*
        Starts the initial screen user sees and ensures that they are logged in
        Displays all interviews that user has, otherwise shows a starter instruction
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            //Starts start activity, user can register or sign in
            startActivityForResult(
                    // Creates the sign in/up activity for us
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    INTERVIEW_LIST_REQUEST
            );
        }
        else {
            // Continue to display the list of interviews in the interview room
            displayInterviews();
        }
    }

    /*
        displayInterviews: shows all interviews in a ListView
    */
    private void displayInterviews() {
        // TODO: change xml to showcase the chats
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Checks if request is for interviews list
        if (requestCode == INTERVIEW_LIST_REQUEST) {
            // Checks if user signed in properly
            if (resultCode == RESULT_OK) {
                displayInterviews();
            }
            else {
                Toast.makeText(this,
                        "Please try logging in again. An error has occured.",
                        Toast.LENGTH_LONG)
                        .show();
                // otherwise close app
                finish();
            }
        }
    }

    /*
        Ensures that there is a signout button in the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(InterviewActivity.this,
                                    "You successfully signed out.",
                                    Toast.LENGTH_LONG)
                                    .show();
                            finish();
                        }
                    });
        }
        return true;
    }
}
