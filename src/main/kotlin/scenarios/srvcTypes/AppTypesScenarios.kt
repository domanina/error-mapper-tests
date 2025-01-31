package scenarios.apptypes

import scenarios.ActionObject
import config.Settings
import models.Model
import utilities.Helper


open class AppTypesScenarios(var testCase: CaseTestIT)
{
    private val srvcApi = srvcAPI(Settings.standUrl)
    private val helper = Helper()

    fun getList(
        unionParams: MutableMap<String, Any>?,
        objectsMap: MutableMap<String, ArrayList<Model>>
    ): ActionObject
    {
        val response = srvcApi.getAllAppTypes()
        testCase.response = response!!.body.toString()
        return ActionObject(objectsMap, response.body.toString(), unionParams)
    }

    fun getOne(
        unionParams: MutableMap<String, Any>?,
        objectsMap: MutableMap<String, ArrayList<Model>>
    ): ActionObject
    {
        val appType = objectsMap[ObjectsMap.APP_TYPES.obj]?.last() as AppType
        val appType = if (unionParams?.containsKey("app_type")!!) unionParams["app_type"] as String else appTypeObj.app_type.toString()
        val response = srvcApi.getAppType(appType = appType)
        testCase.response = response!!.body.toString()
        return ActionObject(objectsMap, response.body.toString(), unionParams)
    }

}
