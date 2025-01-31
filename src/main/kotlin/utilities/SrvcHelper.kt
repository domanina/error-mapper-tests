package utilities

import models.Model
import kotlin.collections.ArrayList

class SrvcHelper
{
    fun fillObjectMap(entity: String, obj: Model, objectsMap: MutableMap<String, ArrayList<Model>>)
    {
        val objectsList: ArrayList<Model> = arrayListOf()
        if (objectsMap[entity].isNullOrEmpty())
        {
            objectsList.add(obj)
            objectsMap[entity] = objectsList
        } else objectsMap[entity]!!.add(obj)
    }


    fun makePaging(list: ArrayList<Any>, offset: Int? = 0, limit: Int?): ArrayList<Any>
    {
        val resList: ArrayList<Any>
        val startIndex: Int = offset!!
        var endIndex: Int =  startIndex + limit!!
        if (endIndex > list.size) endIndex = list.size
        resList = list.slice(startIndex until endIndex) as ArrayList<Any>
        return resList
    }

}