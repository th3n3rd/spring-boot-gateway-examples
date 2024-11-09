package com.example.connect.downstream

import com.fasterxml.jackson.databind.ObjectMapper
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import java.io.File

class JsonApprovalTesting : ApprovalsExtension(File("src/test/resources"), ".json")

fun String.toJsonPrettyString(): String {
    return ObjectMapper().readTree(this).toPrettyString()
}