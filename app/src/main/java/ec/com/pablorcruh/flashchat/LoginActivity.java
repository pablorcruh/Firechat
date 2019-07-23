package ec.com.pablorcruh.flashchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private AutoCompleteTextView mEmailView;

    private EditText mPasswordView;

    private FirebaseAuth mAuth;

    private static final String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(id == R.integer.register_form_finished || id == EditorInfo.IME_NULL){
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    public void signInExistingUser(View v){
        attemptLogin();
    }

    public void registerNewUser(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        finish();
        startActivity(intent);
    }



    private void attemptLogin() {
        String email = mEmailView.getText().toString();
        String password= mPasswordView.getText().toString();

        if(email.equals("") || password.equals("")){
            return;
        }else{
            Log.d(TAG, "attemptLogin: ");
            Toast.makeText(this,"Login in progress...", Toast.LENGTH_LONG).show();
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "onComplete: "+task.isSuccessful());
                if(!task.isSuccessful()){
                    Log.d(TAG, "Problem Signing in: "+task.getException());
                    showErrorMessageDialog("There was a problem signing in");
                }else{
                    Intent intent = new Intent(LoginActivity.this, MainChatActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    public void showErrorMessageDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Error Login")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
