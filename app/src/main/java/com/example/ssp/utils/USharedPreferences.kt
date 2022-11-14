package com.example.ssp.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.ssp.model.Person
import com.google.gson.Gson

class USharedPreferences {
    companion object {
        private const val keyAccount = "account"
        private const val keyPreferencesAccount = "accountPreferences"

        fun writeAccount(account: Person, fragmentActivity: FragmentActivity?) {
            val accountGSON = Gson().toJson(account)
            val sharedPref = fragmentActivity?.getSharedPreferences(
                keyPreferencesAccount, Context.MODE_PRIVATE
            ) ?: return
            with(sharedPref.edit()) {
                putString(keyAccount, accountGSON)
                apply()
            }
        }

        fun writeAccount(account: Person, activity: AppCompatActivity) {
            val accountGSON = Gson().toJson(account)
            val sharedPref =
                activity.getSharedPreferences(keyPreferencesAccount, Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                putString(keyAccount, accountGSON)
                apply()
            }
        }

        fun readAccount(fragmentActivity: FragmentActivity?): Person {
            val sharedPref = fragmentActivity?.getSharedPreferences(keyPreferencesAccount, Context.MODE_PRIVATE)
            val accountString = sharedPref?.getString(keyAccount, "")
            return Gson().fromJson(accountString, Person::class.java)
        }

        fun readAccount(activity: AppCompatActivity): Person {
            val sharedPref = activity.getSharedPreferences(keyPreferencesAccount, Context.MODE_PRIVATE)
            val accountString = sharedPref?.getString(keyAccount, "")
            return Gson().fromJson(accountString, Person::class.java)
        }
    }
}