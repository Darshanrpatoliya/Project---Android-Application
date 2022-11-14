package com.example.a5note

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class MyDatabase(var context: Context) : SQLiteOpenHelper(context,"mydb",null,VERSION_NO)
{
    companion object
    {
        var ID="id"
        var VERSION_NO=1
        var TABLE_NAME="Notes"
        var TITLE="title"
        var NOTES="note"
    }

    override fun onCreate(db: SQLiteDatabase?)
    {
        var create_quary=" CREATE TABLE "+TABLE_NAME+" ( "+ID+" integer primary key AUTOINCREMENT, "+TITLE+" VARCHAR(20), "+NOTES+" VARCHAR(60) ) "
        db!!.execSQL(create_quary)
    }

    fun insertData(myModel: MyModel):Long
    {
        var mydb=this.writableDatabase
        var contentValues=ContentValues()

        contentValues.put(TITLE,myModel.mm_title)
        contentValues.put(NOTES,myModel.mm_notes)

        var iD=mydb.insert(TABLE_NAME,null,contentValues)
        return iD
    }

    @SuppressLint("Range")

    fun getall_data():MutableList<MyModel>
    {
        var notelist:MutableList<MyModel> = ArrayList()
        var sel_query="SELECT * FROM $TABLE_NAME"
        var cursor:Cursor?
        var mydb=this.readableDatabase

        try {
            cursor=mydb.rawQuery(sel_query,null)
        }catch (e:SQLiteException){
            mydb.execSQL(sel_query)
            return ArrayList()
        }

        var u_id:Int
        var u_title:String
        var u_notes:String

        if (cursor.count>0)
        {
            if (cursor.moveToFirst())
            {
                do {
                    u_id=cursor.getInt(cursor.getColumnIndex(ID))
                    u_title=cursor.getString(cursor.getColumnIndex(TITLE))
                    u_notes=cursor.getString(cursor.getColumnIndex(NOTES))

                    var u_data=MyModel(mm_id = u_id, mm_title = u_title, mm_notes = u_notes)

                    notelist.add(u_data)
                }while (cursor.moveToNext())
            }
        }
        return notelist
    }

    fun updateData(myModel: MyModel):Int
    {
        var db=this.writableDatabase
        var contentValues=ContentValues()

        contentValues.put(TITLE,myModel.mm_title)
        contentValues.put(NOTES,myModel.mm_notes)

        var id=db.update(TABLE_NAME,contentValues, ID+" = "+myModel.mm_id,null)
        db.close()
        return id
    }

    fun deleteData(myModel: MyModel):Int
    {
        var db=this.writableDatabase
        var delete_quary=db.delete(TABLE_NAME, ID+" = "+myModel.mm_id,null)
        db.close()
        return delete_quary
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}