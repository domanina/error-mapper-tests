package scenarios.apptypes

import models.Model
import utilities.Helper
import utilities.ObjectsMap

class ExpectedResultForAppTypes
{

    private val helper = Helper()

    fun get(
        targetActionExpectedResult: String,
        unionParams: MutableMap<String, Any>?,
        objectsMap: MutableMap<String, ArrayList<Model>>
    ): String
    {
        val expected = targetActionExpectedResult.ifEmpty { helper.gson.toJson(objectsMap[ObjectsMap.APP_TYPES.obj]?.last() as AppType).toString() }
        return expected
    }

    fun getList(
        targetActionExpectedResult: String,
        unionParams: MutableMap<String, Any>?,
        objectsMap: MutableMap<String, ArrayList<Model>>
    ): String
    {
        val expected = targetActionExpectedResult.ifEmpty { helper.gson.toJson(objectsMap[ObjectsMap.APP_TYPES.obj]).toString() }
        return expected
    }

}