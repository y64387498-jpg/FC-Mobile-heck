package com.axiom.launcher

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    companion object {
        private const val GAME_PACKAGE = "com.axlebolt.standoff2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.rgb(8, 13, 22)
        window.navigationBarColor = Color.rgb(8, 13, 22)
        window.decorView.systemUiVisibility = 0
        setContentView(createLauncherView())
    }

    private fun createLauncherView(): View {
        val background = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(Color.rgb(15, 27, 44), Color.rgb(7, 11, 19))
        )

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(dp(28), dp(32), dp(28), dp(32))
            this.background = background
        }

        val title = TextView(this).apply {
            text = "A X I O M"
            setTextColor(Color.WHITE)
            textSize = 38f
            typeface = Typeface.create("sans-serif", Typeface.BOLD)
            gravity = Gravity.CENTER
            letterSpacing = 0.08f
        }

        val subtitle = TextView(this).apply {
            text = "STANDOFF 2 LAUNCHER"
            setTextColor(Color.rgb(151, 171, 199))
            textSize = 14f
            letterSpacing = 0.22f
            gravity = Gravity.CENTER
        }

        val spacer = View(this)
        val button = Button(this).apply {
            text = "BAŞLAT"
            textSize = 20f
            letterSpacing = 0.08f
            setTextColor(Color.WHITE)
            isAllCaps = false
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.rgb(22, 132, 207))
            }
            setOnClickListener { launchGame() }
        }

        val status = TextView(this).apply {
            text = "Oyunu açmak için BAŞLAT'a dokun."
            setTextColor(Color.rgb(170, 184, 204))
            textSize = 13f
            gravity = Gravity.CENTER
        }

        root.addView(title, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ))
        root.addView(subtitle, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { topMargin = dp(18) })
        root.addView(spacer, LinearLayout.LayoutParams(1, 0, 1f))
        root.addView(button, LinearLayout.LayoutParams(dp(220), dp(220)).apply {
            gravity = Gravity.CENTER
        })
        root.addView(status, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { topMargin = dp(24) })
        root.addView(View(this), LinearLayout.LayoutParams(1, 0, 1f))
        return root
    }

    private fun launchGame() {
        val launchIntent: Intent? = packageManager.getLaunchIntentForPackage(GAME_PACKAGE)

        if (launchIntent == null) {
            Toast.makeText(
                this,
                "Standoff 2 bulunamadı. Oyunun kurulu ve etkin olduğundan emin ol.",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        try {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(launchIntent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(this, "Standoff 2 başlatılamadı.", Toast.LENGTH_LONG).show()
        } catch (_: SecurityException) {
            Toast.makeText(this, "Android oyunun açılmasına izin vermedi.", Toast.LENGTH_LONG).show()
        }
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()
}
