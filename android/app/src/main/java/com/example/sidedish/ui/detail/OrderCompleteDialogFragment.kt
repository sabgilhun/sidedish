package com.example.sidedish.ui.detail

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.sidedish.R
import java.lang.IllegalStateException

class OrderCompleteDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = requireActivity()

        val builder = AlertDialog.Builder(activity)
            .setMessage("주문이 완료되었습니다")
            .setPositiveButton("확인") { _, _ ->
                findNavController().navigate(R.id.action_detailFragment_to_menuFragment)
            }

        return builder.create()
    }

    companion object {
        fun newInstance() = OrderCompleteDialogFragment()
    }
}