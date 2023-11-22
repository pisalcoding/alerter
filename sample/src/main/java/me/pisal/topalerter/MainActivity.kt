package me.pisal.topalerter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import me.pisal.alerter.Alerter
import me.pisal.topalerter.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        findViewById<View>(id.btn_success).setOnClickListener {
            Alerter.success()
                .withTitle("Success")
                .withMessage("Congratulations!")
                .setIconDrawable(ResourcesCompat
                    .getDrawable(resources, drawable.round_check_circle_24, theme)
                )
                .setTimeoutMillis(4000)
                .setOnDismissListener {
                    // Do something after dialog is dismissed
                }
                .show(supportFragmentManager, "Alerter.info")
        }

        findViewById<View>(id.btn_info).setOnClickListener {
            Alerter.info()
                .withTitle("Info")
                .withMessage("Angkor Wat is a temple complex in Cambodia, located on a site measuring 162.6 hectares (1,626,000 m2; 402 acres).")
                .setIconDrawable(ResourcesCompat
                    .getDrawable(resources, drawable.round_info_24, theme)
                )
                .show(supportFragmentManager, "Alerter.info")
        }

        findViewById<View>(id.btn_warning).setOnClickListener {
            Alerter.warning()
                .withTitle("Warning")
                .withMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in malesuada lorem. Nullam a odio non metus rhoncus faucibus bibendum quis sapien.")
                .setIconDrawable(ResourcesCompat
                    .getDrawable(resources, drawable.round_warning_24, theme)
                )
                .show(supportFragmentManager, "Alerter.info")
        }

        findViewById<View>(id.btn_error).setOnClickListener {
            Alerter.error()
                .withTitle("Error")
                .withMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in malesuada lorem. Nullam a odio non metus rhoncus faucibus bibendum quis sapien.")
                .show(supportFragmentManager, "Alerter.info")
        }
    }
}