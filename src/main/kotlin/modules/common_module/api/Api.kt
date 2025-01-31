package api

import utilities.Helper
import utilities.Requests

abstract class Api(_baseUrl: String) {
    var baseUrl: String
    val requests: Requests = Requests()
    var helper: Helper = Helper()

    init {
        baseUrl = _baseUrl
    }
}