package ir.mrahimy.myndkapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainGlActivity : AppCompatActivity() {

    private external fun ndkGl(data: IntArray?, width: Int, height: Int)

    companion object {

        init {
            System.loadLibrary("native-gl")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_java)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val leafBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.woocommerce
        )
        (findViewById<View>(R.id.placeholder_before) as ImageView).setImageBitmap(leafBitmap)
        val pixels = IntArray(leafBitmap.width * leafBitmap.height)
        leafBitmap.getPixels(
            pixels, 0, leafBitmap.width, 0, 0, leafBitmap.width,
            leafBitmap.height
        )
        ndkGl(pixels, leafBitmap.width, leafBitmap.height)
        val leafGled = Bitmap.createBitmap(
            leafBitmap.width, leafBitmap.height,
            Bitmap.Config.ARGB_8888
        )
        leafGled.setPixels(
            pixels, 0, leafBitmap.width, 0, 0, leafBitmap.width,
            leafBitmap.height
        )
        (findViewById<View>(R.id.placeholder_after) as ImageView).setImageBitmap(leafGled)
    }
}