package ir.mrahimy.myndkapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainStringActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-concat");
        System.loadLibrary("native-callme");
        System.loadLibrary("native-exception");
        System.loadLibrary("native-throw");
    }

    public static native String ndkGetHello(Activity mainStringActivity);

    public static native String ndkCallMe(PhoneNumber phoneNumber);

    public static native String ndkException(ExceptionistClass exceptionistClass);

    public static native String ndkThrow(ExceptionistClass exceptionistClass);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_string);

        ((TextView) findViewById(R.id.textview)).setText(ndkGetHello(this));
        ((TextView) findViewById(R.id.phonetextview)).setText(ndkCallMe(new PhoneNumber("555-666-777")));

        ((TextView) findViewById(R.id.exceptiontextview)).setText(
                ndkException(new ExceptionistClass())
        );

        try {
            String result = ndkThrow(new ExceptionistClass());
            ((TextView) findViewById(R.id.catchertextview))
                    .setText(result);
        } catch (RuntimeException e) {
            ((TextView) findViewById(R.id.catchertextview))
                    .setText("Exception catch in java: \n"+e.getMessage());

        }


    }
}