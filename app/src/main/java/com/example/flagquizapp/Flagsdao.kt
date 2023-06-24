package com.example.flagquizapp


class Flagsdao {
    fun getRandomFiveFlags(vt: HelperDatabase): ArrayList<Flags> {
        val flagsList = ArrayList<Flags>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5", null)

        while (c.moveToNext()) {
            val flag = Flags(
                c.getInt(c.getColumnIndex("bayrak_id")),
                c.getString(c.getColumnIndex("bayrak_ad")),
                c.getString(c.getColumnIndex("bayrak_resim"))
            )
            flagsList.add(flag)
        }

        return flagsList
    }

    fun getRandomFalseThreeFlags(vt: HelperDatabase,flag_id:Int): ArrayList<Flags> {
        val flagsList = ArrayList<Flags>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != $flag_id ORDER BY RANDOM() LIMIT 3", null)

        while (c.moveToNext()) {
            val flag = Flags(
                c.getInt(c.getColumnIndex("bayrak_id")),
                c.getString(c.getColumnIndex("bayrak_ad")),
                c.getString(c.getColumnIndex("bayrak_resim"))
            )
            flagsList.add(flag)
        }

        return flagsList
    }
}