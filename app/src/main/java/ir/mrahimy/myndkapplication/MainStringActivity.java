package ir.mrahimy.myndkapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainStringActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-concat");
    }

    public static native String ndkGetHello();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_string);

        ((TextView) findViewById(R.id.textview)).setText(ndkGetHello());
    }
}