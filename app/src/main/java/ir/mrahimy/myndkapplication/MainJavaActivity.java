package ir.mrahimy.myndkapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainJavaActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-gl");
    }

    public static native void ndkGl(int[] data, int width, int height);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bitmap leafBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.woocommerce);

        ((ImageView)findViewById(R.id.placeholder_before)).setImageBitmap(leafBitmap);

        int[] pixels = new int[leafBitmap.getWidth() * leafBitmap.getHeight()];
        leafBitmap.getPixels(pixels, 0, leafBitmap.getWidth(), 0, 0, leafBitmap.getWidth(),
                leafBitmap.getHeight());

        ndkGl(pixels, leafBitmap.getWidth(), leafBitmap.getHeight());

        Bitmap leafGled =  Bitmap.createBitmap(leafBitmap.getWidth(), leafBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        leafGled.setPixels(pixels, 0, leafBitmap.getWidth(), 0, 0, leafBitmap.getWidth(),
                leafBitmap.getHeight());

        ((ImageView)findViewById(R.id.placeholder_after)).setImageBitmap(leafGled);
    }
}