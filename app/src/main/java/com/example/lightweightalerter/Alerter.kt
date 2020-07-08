import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatTextView
import com.example.lightweightalerter.R

typealias Completion = (success: Boolean, result: Any?, message: String?) -> Unit

object Alerter {
    fun showTopErrorDialog(
            context: Context,
            title: String,
            shortMessage: String
    ): Dialog {
        val dialog = Dialog(context)
        try {

            dialog.inflateOnTop(R.layout.layout_top_error_message)
            dialog.findViewById<AppCompatTextView>(R.id.txtTitle).text = title
            dialog.findViewById<AppCompatTextView>(R.id.txtShortMessage).text = shortMessage
            dialog.show()

            //Animation
            dialog.findViewById<View>(R.id.dialogRootView).slideInFromTop()
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    dialog.findViewById<View>(R.id.dialogRootView).slideOutToTop { _, _, _ ->
                        dialog.dismiss()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, 3000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dialog
    }

    private fun Dialog.inflateOnTop(@LayoutRes layoutResId: Int): Dialog{
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(layoutResId)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(this.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.TOP

        this.window!!.attributes = lp
        return this
    }

    private fun View.slideInFromTop() {
        val shake = AnimationUtils.loadAnimation(this.context, R.anim.slide_in_from_top)
        this.startAnimation(shake)
    }

    private fun View.slideOutToTop(completion: Completion? = null) {
        val shake = AnimationUtils.loadAnimation(this.context, R.anim.slide_out_to_top)
        shake.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }
            override fun onAnimationEnd(animation: Animation?) {
                completion?.invoke(true, null, null)
            }
            override fun onAnimationStart(animation: Animation?) {
            }
        })
        this.startAnimation(shake)
    }
}