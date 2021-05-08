package ir.mrahimy.myndkapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainNativeCallActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-concat");
        System.loadLibrary("native-callme");
        System.loadLibrary("native-exception");
        System.loadLibrary("native-throw");
        System.loadLibrary("native-mutable");
        System.loadLibrary("native-lock");
        System.loadLibrary("native-thread");
    }

    public static native String ndkGetHello(Activity mainStringActivity);

    public static native String ndkCallMe(PhoneNumber phoneNumber);

    public static native String ndkException(ExceptionistClass exceptionistClass);

    public static native String ndkThrow(ExceptionistClass exceptionistClass);

    public static native DataClass ndkMutable(DataClass dataClass);

    public static native String ndkLock(Activity mainStringActivity);

    public static native String ndkThread(IntWrapper intWrapper);

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
                    .setText("Exception catch in java: \n" + e.getMessage());

        }

        final DataClass dataClass = new DataClass("John", "Doe");

        ((TextView) findViewById(R.id.globalBeforeTextview))
                .setText(dataClass.toString());

        ndkMutable(dataClass);

        ((TextView) findViewById(R.id.globalAfterTextview))
                .setText(dataClass.toString());

        ((TextView) findViewById(R.id.lockTextview))
                .setText(ndkLock(this));

        final IntWrapper intWrapper = new IntWrapper(0);
        ndkThread(intWrapper);
        ((TextView) findViewById(R.id.threadTextview))
                .setText(String.valueOf(intWrapper.getInt()));
    }
}