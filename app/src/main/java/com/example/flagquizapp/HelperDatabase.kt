package com.example.flagquizapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperDatabase(contex:Context) : SQLiteOpenHelper(contex,"bayrakquiz.sqlite",null,1){
    override fun onCreate(p0: SQLiteDatabase?) {
    p0?.execSQL("CREATE TABLE \"bayraklar\" (\n" +
            "\t\"bayrak_id\"\tINTEGER,\n" +
            "\t\"bayrak_ad\"\tTEXT,\n" +
            "\t\"bayrak_resim\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"bayrak_id\" AUTOINCREMENT)\n" +
            ");")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}