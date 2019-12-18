package com.demo.phy.phybasedemo.ui.fragment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.phy.phybasedemo.R

/**
 * Created by phy on 2019/11/21
 */

class TwoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_two, container, false)
    }
}