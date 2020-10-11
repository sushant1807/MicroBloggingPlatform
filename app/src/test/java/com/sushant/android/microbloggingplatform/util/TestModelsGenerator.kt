package com.sushant.android.microbloggingplatform.util

import com.google.gson.Gson
import com.sushant.android.microbloggingplatform.model.service.response.Author
import java.io.File

class TestModelsGenerator {
    private var authorResponse: Author

    init {
        val gson = Gson()
        val jsonString = getJson("AuthorResponse.json")
        authorResponse = gson.fromJson(jsonString, Author::class.java)
    }

    fun generateAuthorResponse(): Author {
        return authorResponse
    }

    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}