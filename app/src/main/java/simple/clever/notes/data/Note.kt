package simple.clever.notes.data

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcel
import android.os.Parcelable

import simple.clever.notes.R

class Note :Parcelable {

    var  noteIndex:Int
    var  heading:String
    val KEY_USER_NOTE= "save_note"
    val KEY_PREF= "note_pref"

    constructor(parcel: Parcel)  {
        this.noteIndex = 1
        this.heading = ""
    }

    constructor(contentIndex:Int ,  heading:String){
        this.noteIndex = contentIndex
        this.heading = heading
    }

    fun  getNoteBody(mContext:Context ):String? {
        val sp:SharedPreferences  = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE)
        return sp.getString(KEY_USER_NOTE+noteIndex, mContext.getResources().getString(R.string.init_note))
//        return mContext.getResources().getStringArray(R.array.notes)[noteIndex]
    }

    fun setNoteBody( mContext:Context,  userText:String){
        val sharedPreferences:SharedPreferences  = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_USER_NOTE+noteIndex, userText)
        editor.apply()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(noteIndex)
    }


    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }
}
