package simple.clever.notes.data


import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*

class CardSourceFireBaseImpl : CardSource {

    val CARDS_COLLECTION = "cards"
    val TAG = "[CardsSourceFirebaseImpl]"
    val store = FirebaseFirestore.getInstance()
    val collection = store.collection(CARDS_COLLECTION)
    var cardsData: MutableList<CardData> = ArrayList()


    override fun init(cardSourceResponse: CardSourceResponse): CardSource {
        collection.orderBy(CardDataMapping.Fields.DATE, Query.Direction.DESCENDING).get()
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (task.isSuccessful()) {
                        cardsData = ArrayList()
                        for (document in task.result!!) {
                            val doc: MutableMap<String, Any> = document.data
                            val id: String = document.id
                            val cardData: CardData = CardDataMapping.toCardData(id, doc)
                            cardsData.add(cardData)
                        }
                        cardSourceResponse.initialized(this)
                    }
                })

        return this
    }

    override fun getCardData(position: Int): CardData {
        return cardsData[position]
    }

    override fun size(): Int {
        if (cardsData == null) {
            return 0
        }
        return cardsData.size
    }

    override fun addCardData(cardData: CardData) {
        collection.add(CardDataMapping.Companion.toDocument(cardData)).addOnSuccessListener(OnSuccessListener<DocumentReference>() {
            fun onSuccess(documentReference: DocumentReference) {
                cardData.id = (documentReference.id)
            }
        })
    }

    override fun deleteCardData(position: Int) {
        collection.document(cardsData.get(position).id).delete()
        cardsData.removeAt(position)
    }

    override fun updateCardData(cardData: CardData, position: Int) {
        val id: String = cardData.id

        collection.document(id).set(CardDataMapping.toDocument(cardData))
    }
}
