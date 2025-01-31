package runners

import scenarios.ActionObject
import scenarios.apptypes.AppTypesScenarios
import models.Model
import scenarios.configParams.ConfigParamsScenarios
import scenarios.configurations.ConfigurationsScenarios
import scenarios.deviceClasses.DeviceClassesScenarios
import scenarios.deviceTypes.DeviceTypesScenarios
import scenarios.licenses.LicenceScenarios
import scenarios.operatorTitles.OperatorTitlesScenarios
import scenarios.operators.OperatorsScenarios
import scenarios.paramTemplates.ParamTemplatesScenarios
import scenarios.sources.SourcesScenarios
import scenarios.tags.TagsScenarios
import testit_client.module.models.CaseTestIT
import testit_client.module.models.Step
import utilities.Helper
import utilities.Logger
import kotlin.collections.ArrayList

class TestITCases
{
    private val logger = Logger().logger
    private val helper = Helper()

    fun makeTest(testCase: CaseTestIT)
    {
        logger.info("-------- TestCaseName: ${testCase.name}")
        logger.info("-------- TestCaseDescription: ${testCase.description}")
        logger.info("-------- TestCaseID: ${testCase.globalId}")
        logger.info("-------- TestPointID: ${testCase.testPointId}")
        logger.info("-------- TestConfiguration: ${testCase.configuration?.name}, ${testCase.configuration?.capabilities}")

        // Init steps
        val steps = testCase.steps

        // Init initial content
        val defaultParams: MutableMap<String, Any> = mutableMapOf()

        // Make steps
        var objectsMap: MutableMap<String, ArrayList<Model>> = mutableMapOf()

        val makePreconditions = makeSteps(defaultParams, testCase.preconditionSteps, objectsMap, testCase)
        objectsMap = makePreconditions.objectsMap!!

        val makeTargetAction = makeSteps(defaultParams, steps, objectsMap, testCase)
        objectsMap = makeTargetAction.objectsMap!!
        val response = makeTargetAction.response
        val unionParams = makeTargetAction.unionParams

        // Check result
        val targetAction = steps!!.last()
        val targetActionExpectedResult = targetAction.expected
            ?.replace("<p>", "")
            ?.replace("</p>", "")
        val expectedResult =
            if (targetActionExpectedResult!!.contains("error")
                || targetAction.action!!.contains("testDeleteAppType")
                || targetAction.action!!.contains("testDeleteOperator")
            ) targetActionExpectedResult
            else makeExpectedResult(targetAction.action!!, targetActionExpectedResult, objectsMap, unionParams)

        logger.info("Assert equals for target action - ${targetAction.action}")

        helper.equalResponseWithExpectedResult(expectedResult, response)
        logger.info("TEST PASSED")
    }

    private fun makeSteps(
        defaultParams: MutableMap<String, Any>,
        steps: ArrayList<Step>?,
        objectsMap: MutableMap<String, ArrayList<Model>>,
        testCase: CaseTestIT
    ): ActionObject
    {
        var actionObject = ActionObject(mutableMapOf(), "", mutableMapOf())
        if (steps == null) return actionObject

        var actionParams: MutableMap<String, Any>
        for (step in steps)
        {
            val stepParamsMap = mutableMapOf<String, Any>()
            stepParamsMap.putAll(defaultParams)
            if (step.action == "") continue
            if (step.action!!.contains('('))
            {
                val parseAction = step.action!!.split("(")
                val currentAction = parseAction[0]
                val tmp = helper.getWordBetweenBrackets("(" + parseAction[1], '(')
                val stringParams = java.lang.StringBuilder(tmp).deleteCharAt(0).deleteCharAt(tmp.length - 2).toString()
                actionParams = helper.parseActionParams(stringParams)
                stepParamsMap.putAll(actionParams)
                actionObject = makeAction(stepParamsMap, currentAction, objectsMap, testCase)
                if (actionParams.containsKey("count"))
                    for (i in 1 until   actionParams["count"].toString().toInt()) {
                        makeAction(stepParamsMap, currentAction, objectsMap, testCase)
                    }
                continue
            }
            actionObject = makeAction(stepParamsMap, step.action!!, objectsMap, testCase)
        }
        return actionObject
    }

    private fun makeAction(
        unionParams: MutableMap<String, Any>?,
        currentAction: String,
        objectsMap: MutableMap<String, ArrayList<Model>>,
        testCase: CaseTestIT
    ): ActionObject
    {

        when (currentAction)
        {
            "sleep"       ->
            {
                Thread.sleep(3000)
                val response: String = toString()
                return ActionObject(objectsMap, response, unionParams)
            }
            "testGetAppType" -> return AppTypesScenarios(testCase).getOne(unionParams, objectsMap)
            else          ->
            {
                Thread.sleep(3000)
                val response: String = toString()
                return ActionObject(objectsMap, response, unionParams)
            }

        }
    }

    private fun makeExpectedResult(
        action: String,
        targetActionExpectedResult: String,
        objectsMap: MutableMap<String, ArrayList<Model>>,
        unionParams: MutableMap<String, Any>?
    ): String?
    {
        when
        {
            action.contains("testPostAppType")-> return scenarios.apptypes.ExpectedResultForAppTypes().create(targetActionExpectedResult, unionParams, objectsMap)
            action.contains("testGetAppType")-> return scenarios.apptypes.ExpectedResultForAppTypes().get(targetActionExpectedResult, unionParams, objectsMap)
            else -> return null
        }
    }

}