package api.project

import api.Api
import kong.unirest.HttpResponse
import kong.unirest.JsonNode

class srvcAPI(_baseUrl: String) : Api(_baseUrl) {


    fun getAllAppTypes(): HttpResponse<JsonNode>? {
        return requests.doGetRequest("http://srvc.$baseUrl/apptypes)
    }

    fun getAppType(appType: String?) : HttpResponse<JsonNode>? {
        return requests.doGetRequest("http://srvc.$baseUrl/apptypes/${appType}")
    }
}