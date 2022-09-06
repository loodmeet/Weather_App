package com.example.weatherapp.ui.alert_dialogs

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.weatherapp.R
import javax.inject.Inject

internal class ExitAlertDialogProvider @Inject constructor() {

    fun provide(context: Context, baseOnBackPressed: () -> Unit): AlertDialog {
        return AlertDialog.Builder(context).apply {
            with(context.resources) {
                setTitle(getString(R.string.exit_alert_dialog_title))
                setMessage(getString(R.string.exit_alert_dialog_message))
                setPositiveButton(
                    getString(R.string.exit_alert_dialog_positive_button_text)
                ) { _, _ ->
                    baseOnBackPressed()
                }
                setNegativeButton(
                    getString(R.string.exit_alert_dialog_negative_button_text)
                ) { dialog, _ ->
                    dialog.cancel()
                }
            }
            setCancelable(true)
        }.create()
    }
}