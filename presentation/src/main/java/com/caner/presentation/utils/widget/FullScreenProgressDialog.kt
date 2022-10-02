package com.caner.presentation.utils.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.caner.presentation.R

class FullScreenProgressDialog(context: Context) : Dialog(context, R.style.ProgressDialogStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.view_progress)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
    }

    override fun show() {
        if (!isShowing) super.show()
    }
}
