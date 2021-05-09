package ir.mrahimy.myndkapplication

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainNativeCallActivity : AppCompatActivity() {

    private external fun ndkGetHello(mainStringActivity: Activity?): String?
    private external fun ndkCallMe(phoneNumber: PhoneNumber?): String?
    private external fun ndkException(exceptionistClass: ExceptionistClass?): String?
    private external fun ndkThrow(exceptionistClass: ExceptionistClass?): String
    private external fun ndkMutable(dataClass: DataClass?): DataClass?
    private external fun ndkLock(mainStringActivity: Activity?): String?
    private external fun ndkThread(intWrapper: IntWrapper?): String?
    private external fun ndkAbi(): String?

    companion object {

        init {
            System.loadLibrary("native-concat")
            System.loadLibrary("native-callme")
            System.loadLibrary("native-exception")
            System.loadLibrary("native-throw")
            System.loadLibrary("native-mutable")
            System.loadLibrary("native-lock")
            System.loadLibrary("native-thread")
            System.loadLibrary("native-abi")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_string)
        (findViewById<View>(R.id.textview) as TextView).text = ndkGetHello(this)
        (findViewById<View>(R.id.phonetextview) as TextView).text =
            ndkCallMe(PhoneNumber("555-666-777"))
        (findViewById<View>(R.id.exceptiontextview) as TextView).text =
            ndkException(ExceptionistClass())
        try {
            val result = ndkThrow(ExceptionistClass())
            (findViewById<View>(R.id.catchertextview) as TextView).text = result
        } catch (e: RuntimeException) {
            (findViewById<View>(R.id.catchertextview) as TextView).text = """
                Exception caught in java: 
                ${e.message}
                """.trimIndent()
        }
        val dataClass = DataClass("John", "Doe")
        (findViewById<View>(R.id.globalBeforeTextview) as TextView).text = dataClass.toString()
        ndkMutable(dataClass)
        (findViewById<View>(R.id.globalAfterTextview) as TextView).text = dataClass.toString()
        (findViewById<View>(R.id.lockTextview) as TextView).text = ndkLock(this)
        val intWrapper = IntWrapper(0)
        ndkThread(intWrapper)
        (findViewById<View>(R.id.threadTextview) as TextView).text = intWrapper.int.toString()

        (findViewById<View>(R.id.abiTextview) as TextView).text = ndkAbi()
    }
}