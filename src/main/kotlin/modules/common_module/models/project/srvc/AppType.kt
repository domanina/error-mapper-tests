package modules.common_module.models.project.srvc

import com.google.gson.annotations.Expose
import models.Model

/**
 * 
 * @param app_type 
 * @param name 
 */
data class AppType (
    @Expose
    var app_type: String? = null,

    @Expose
    var name: String? = null
) : Model()

