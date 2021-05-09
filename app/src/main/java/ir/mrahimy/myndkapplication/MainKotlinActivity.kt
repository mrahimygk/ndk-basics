package ir.mrahimy.myndkapplication

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class MainKotlinActivity : AppCompatActivity() {

    companion object {
        init {
            System.loadLibrary("hello-log")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)
        setSupportActionBar(findViewById(R.id.toolbar))

    }
}