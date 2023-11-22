package me.pisal.alerter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.DialogFragment

class Alerter private constructor() : DialogFragment() {

    private var title: String = "TITLE"
    private var message: String = "MESSAGE"

    private var iconDrawable: Drawable? = null
    private var timeout = 3000L
    private var level: AlertLevel = AlertLevel.INFO
    private var onDismissListener: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullscreenDialogFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(requireView()) {
            findViewById<TextView>(R.id.txt_title).text = title
            findViewById<TextView>(R.id.txt_message).text = message
            findViewById<View>(R.id.card).run {
                setBackgroundColor(
                    resources.getColor(level.colorResId, context.theme)
                )
            }
            iconDrawable?.let {
                findViewById<ImageView>(R.id.icon).setImageDrawable(it)
            }
        }

        initAnimation()
    }

    /**
     * Mandatory
     */
    fun withTitle(title: String): Alerter {
        this.title = title
        return this
    }

    /**
     * Mandatory
     */
    fun withMessage(message: String): Alerter {
        this.message = message
        return this
    }

    /**
     * Optional: customize the left icon on the dialog
     */
    fun setIconDrawable(drawable: Drawable?): Alerter {
        this.iconDrawable = drawable
        return this
    }

    /**
     * Optional: determines how long should the dialog stay visible before automatically dismiss.
     * In milliseconds.
     */
    fun setTimeoutMillis(timeout: Long): Alerter {
        this.timeout = timeout
        return this
    }

    fun setOnDismissListener(block: (() -> Unit)): Alerter {
        this.onDismissListener = block
        return this
    }

    companion object {
        fun fromLevel(level: AlertLevel): Alerter {
            return Alerter().apply {
                this.level = level
            }
        }

        fun info(): Alerter {
            return Alerter().apply {
                this.level = AlertLevel.INFO
            }
        }

        fun success(): Alerter {
            return Alerter().apply {
                this.level = AlertLevel.SUCCESS
            }
        }

        fun warning(): Alerter {
            return Alerter().apply {
                this.level = AlertLevel.WARNING
            }
        }

        fun error(): Alerter {
            return Alerter().apply {
                this.level = AlertLevel.ERROR
            }
        }
    }

    enum class AlertLevel(@ColorRes val colorResId: Int) {
        SUCCESS(R.color.color_green_success),
        INFO(R.color.color_blue_info),
        WARNING(R.color.color_orange_warning),
        ERROR(R.color.color_red_error)
    }

    //# region private
    private fun initAnimation() {
        with(requireView()) {
            findViewById<View>(R.id.card).run {
                AnimationUtils.loadAnimation(this.context, R.anim.vertical_shake).run {
                    startAnimation(this)
                }
                setOnClickListener {
                    dismiss()
                }
            }

            findViewById<View>(R.id.content).run {
                AnimationUtils.loadAnimation(this.context, R.anim.alerter_slide_in_from_top).run {
                    startAnimation(this)
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    try {
                        AnimationUtils.loadAnimation(this.context, R.anim.alerter_slide_out_to_top)
                            .apply {
                                setAnimationListener(object : Animation.AnimationListener {
                                    override fun onAnimationRepeat(animation: Animation?) {}
                                    override fun onAnimationEnd(animation: Animation?) {
                                        dismiss()
                                    }

                                    override fun onAnimationStart(animation: Animation?) {}
                                })
                                startAnimation(this)
                            }
                    } catch (t: Throwable) {
                        t.printStackTrace()
                    }
                }, timeout)
            }

            findViewById<View>(R.id.icon).run {
                AnimationUtils.loadAnimation(this.context, R.anim.bounce).apply {
                    interpolator = BounceInterpolator() //0.2f, 20.0
                    startAnimation(this)
                }
            }
        }
    }
    //# endregion
}