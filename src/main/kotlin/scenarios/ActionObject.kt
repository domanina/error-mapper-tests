package scenarios

import models.Model

class ActionObject(
    var objectsMap: MutableMap<String, ArrayList<Model>>?,
    var response: String?,
    var unionParams: MutableMap<String, Any>?
)