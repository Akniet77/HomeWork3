package com.example.homework3.core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>( private val inflate: (inflate: LayoutInflater) -> VB): AppCompatActivity() {
    protected lateinit var binding: VB

    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)
        initUI()
    }

    open fun initUI(){}


}
