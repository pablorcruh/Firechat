package ec.com.pablorcruh.flashchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainChatActivity extends AppCompatActivity {

    private String mDisplayName;

    private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDisplayName();

        setContentView(R.layout.activity_main_chat);
        mInputText = findViewById(R.id.messageInput);
        mSendButton = findViewById(R.id.sendButton);
        mChatListView = findViewById(R.id.chat_list_view);
    }

    public void setupDisplayName(){
        SharedPreferences prefs= getSharedPreferences(RegisterActivity.CHAT_PREFS, MODE_PRIVATE);
        mDisplayName = prefs.getString(RegisterActivity.DISPLAY_NAME_KEY, null);
        if (mDisplayName==null) mDisplayName = "Anónimo";

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
