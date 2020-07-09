package com.example.lightweightalerter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInfo.setOnClickListener {
            Alerter.showTopDialog(
                this,
                "Info",
                "You are handsome",
                Alerter.AlertLevel.INFO
            )
        }

        btnSuccess.setOnClickListener {
            Alerter.showTopDialog(
                this,
                "Success",
                "You did it",
                Alerter.AlertLevel.SUCCESS
            )
        }

        btnWarning.setOnClickListener {
            Alerter.showTopDialog(
                this,
                "Warning",
                "Don't do it",
                Alerter.AlertLevel.WARNING
            )
        }

        btnDanger.setOnClickListener {
            Alerter.showTopDialog(
                this,
                "Error",
                "You f*cked it up",
                Alerter.AlertLevel.DANGER
            )
        }
    }

}
