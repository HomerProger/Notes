package simple.clever.notes.data

import com.google.firebase.Timestamp

import java.util.HashMap
import java.util.Map

class CardDataMapping {
    object Fields{
        const val  DATE: String  = "date"
        const val HEAD:String  = "title"
        const val FAVORITE:String  = "favorite"
    }
    companion object{


        fun  toCardData(id2:String , doc:MutableMap<String, Any> ):CardData {
            val timeStamp:Timestamp  = doc.get(Fields.DATE)as Timestamp
             val answer:CardData =  CardData( doc.get(Fields.HEAD)as String, timeStamp.toDate(),  doc.get(Fields.FAVORITE) as Boolean)
            answer.id=id2
            return answer
        }



        fun  toDocument(cardData:CardData ):MutableMap<String, Any>{
            val answer:MutableMap<String, Any>  =  HashMap()
            answer.put(Fields.HEAD, cardData.head)
            answer.put(Fields.DATE, cardData.timeOpen)
            answer.put(Fields.FAVORITE, cardData.favorite)
            return answer
        }

    }



}
