package models.testing

class Test(
    var api: String,

    var initialContent: String,

    var description: String,

    var preconditions: String?,

    var targetAction: String,

    var expectedResult: String
)