package utilities.databases

import config.Settings
import models.Model


class SrvcPostgresClient
{
    private val postgresClient = PostgresDatabase(Settings.DbUrl, Settings.DbName, Settings.dbUser, Settings.dbPass)
    private val dbName = Settings.DbName

    fun cleanupDb()
    {
        postgresClient.deleteRequest(dbName, "param_temp_history")
        postgresClient.doAnySQL("INSERT INTO srvc.srvc_objects_types (ob_id,vp_code,ob_name) VALUES ('0','0','0')")

    }

    fun close()
    {
        postgresClient.close()
    }

}