package com.example.digitalsociety.chairman.fragmentall.logout

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.digitalsociety.OptionActivity2
import com.example.digitalsociety.R


class LogOutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_log_out, container, false)

        var pragDialog=ProgressDialog(view.context)
        pragDialog.setTitle("Log Out...")
        pragDialog.setMessage("Please Wait Until fewseconds...")
        pragDialog.show()

        Handler(Looper.getMainLooper()).postDelayed({

            var i=Intent(view.context, OptionActivity2::class.java)
            activity?.finish()
            startActivity(i)
        },3000)


        return view
    }
}