package simple.clever.notes.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte

import java.util.Date;

class CardData : Parcelable {

    var id: String
    var head: String
    var timeOpen: Date
    var favorite: Boolean

    constructor(head: String, timeOpen: Date, favorite: Boolean) {
        this.head = head
        this.timeOpen = timeOpen
        this.favorite = favorite
        this.id = ""
    }
    constructor(in2:Parcel)
    {
        head = in2.readString()?:"";
        favorite = in2.readByte()==(1 as Byte);
        timeOpen = Date ( in2 . readLong ());
        id=in2.readString()?:"";
    }



    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(dest: Parcel, flags: Int): Unit {
        dest.writeString(head);
        dest.writeByte((if (favorite) 1 else 0) as Byte);
        dest.writeLong(timeOpen.getTime());
        dest.writeString(id)
    }

 companion object Creator: Parcelable.Creator<CardData>
     {
         override fun createFromParcel(in2:Parcel ):CardData  {
             return  CardData ( in2);
         }

         override fun newArray(size: Int): Array<CardData?> {

             return arrayOfNulls(size)
         }
     };





}
