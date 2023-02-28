package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import com.example.myapplication.databinding.AccountSettingsBinding

class AccountSettings: BaseActivity() {
    private lateinit var binding: AccountSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)











    }


}