package simple.clever.notes.data

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte

import java.util.Date

class CardData : Parcelable {

    var id: String=""
    var head: String
    var timeOpen: Date
    var favorite: Boolean

    constructor(head: String, timeOpen: Date, favorite: Boolean) {
        this.head = head
        this.timeOpen = timeOpen
        this.favorite = favorite

    }
    constructor(in2:Parcel)
    {
        head = in2.readString()?:""
        favorite = in2.readByte()==(1 as Byte)
        timeOpen = Date ( in2 . readLong ())
    }



    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int): Unit {
        dest.writeString(head)
        dest.writeByte((if (favorite) 1 else 0) as Byte)
        dest.writeLong(timeOpen.getTime())
    }


    companion object CREATOR : Parcelable.Creator<CardData> {
        override fun createFromParcel(parcel: Parcel): CardData {
            return CardData(parcel)
        }

        override fun newArray(size: Int): Array<CardData?> {
            return arrayOfNulls(size)
        }
    }


}
