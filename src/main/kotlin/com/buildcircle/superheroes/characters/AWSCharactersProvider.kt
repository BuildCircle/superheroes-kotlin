package com.buildcircle.superheroes.characters

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
class AWSCharactersProvider @Autowired constructor(
    private val objectMapper: ObjectMapper
) : CharactersProvider {

    companion object {
        private const val CharactersUri = "https://s3.eu-west-2.amazonaws.com/build-circle/characters.json"
    }

    override fun getCharacters(): CharactersResponse {
        val client = HttpClient.newHttpClient()

        val request = HttpRequest.newBuilder()
            .uri(URI.create(CharactersUri))
            .GET()
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val charactersResponse = objectMapper.readValue(response.body(), CharactersResponse::class.java)

        return charactersResponse
    }
}
