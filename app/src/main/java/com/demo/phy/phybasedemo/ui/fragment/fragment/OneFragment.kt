package com.demo.phy.phybasedemo.ui.fragment.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.demo.phy.phybasedemo.R


/**
 * Created by phy on 2019/11/21
 */

class OneFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }
}