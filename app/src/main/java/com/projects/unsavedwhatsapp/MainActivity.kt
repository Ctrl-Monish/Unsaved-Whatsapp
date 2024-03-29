package com.projects.unsavedwhatsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number = "0"
        if(intent.action == Intent.ACTION_PROCESS_TEXT){
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if(number.isDigitsOnly()){
            startWhatsapp(number)
        }else{
            Toast.makeText(this,"Please check the number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWhatsapp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        //remove '+' from the number
        val data: String = if (number[0] == '+'){
            number.substring(1)
        }else if(number.length == 10){
            //add country code
            "91$number"
        }else{
            number
        }
        intent.data = Uri.parse("https://wa.me/$data")
        startActivity(intent)
        finish()
        //check if the users device has whatsapp installed or not
//        if (packageManager.resolveActivity(intent,0) != null){
//            startActivity(intent)
//        }else{
//            Toast.makeText(this,"Please install whatsapp", Toast.LENGTH_SHORT).show()
//        }
    }
}