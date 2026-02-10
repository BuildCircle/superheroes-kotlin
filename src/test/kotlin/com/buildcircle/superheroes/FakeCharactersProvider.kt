package com.buildcircle.superheroes

import com.buildcircle.superheroes.characters.CharactersProvider
import com.buildcircle.superheroes.characters.CharactersResponse

class FakeCharactersProvider(
    private val fakeResponse: CharactersResponse
) : CharactersProvider {

    override fun getCharacters(): CharactersResponse {
        return fakeResponse
    }
}
