package simple.clever.notes.data

import android.content.res.Resources
import simple.clever.notes.R
import java.util.*

class CardSourceImpl: CardSource{

   private  var dataSource: MutableList<CardData> = ArrayList()
    private val  resources:Resources

    constructor(resources: Resources) {
        this.resources = resources
    }

    override fun init(cardSourceResponse: CardSourceResponse): CardSource {
        val  heads   = resources.getStringArray(R.array.heading)
        for (i in 0..heads.size) {
            dataSource.add(CardData(heads[i], Calendar.getInstance().getTime(), false))
        }
        if (cardSourceResponse != null){
            cardSourceResponse.initialized(this)
        }
        return this
    }
    override fun getCardData(position: Int): CardData {
        return dataSource[position]
    }

    override fun size(): Int {
        return dataSource.size
    }

    override fun addCardData(cardData: CardData) {
        dataSource.add(cardData)
    }

    override fun deleteCardData(position: Int) {
        dataSource.removeAt(position)
    }

    override fun updateCardData(cardData: CardData, position: Int) {
        dataSource[position] = cardData
    }


}








