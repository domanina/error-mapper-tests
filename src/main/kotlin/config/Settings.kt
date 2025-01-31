package config

object Settings {
    val dbHost: String = if (System.getenv("DB_HOST") != null) System.getenv("DB_HOST")
    val dbPort: String = if (System.getenv("DB_PORT") != null) System.getenv("DB_PORT")
    val standUrl: String = if (System.getenv("HOST") != null) System.getenv("HOST")
    val srvcDbName: String = if (System.getenv("DB_NAME") != null) System.getenv("DB_NAME")
    val srvcHost: String = if (System.getenv("SRVC_HOST") != null) System.getenv("SRVC_HOST")
    val srvcDbUrl: String = if (System.getenv("SRVC_DB_URL") != null) System.getenv("SRVC_DB_URL")
    val dbUser: String = if (System.getenv("DB_USER") != null) System.getenv("DB_USER")
    val dbPass: String = if (System.getenv("DB_PASS") != null) System.getenv("DB_PASS")
}