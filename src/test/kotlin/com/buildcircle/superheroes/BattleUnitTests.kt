package com.buildcircle.superheroes

import com.buildcircle.superheroes.battle.BattleController
import com.buildcircle.superheroes.characters.CharacterResponse
import com.buildcircle.superheroes.characters.CharactersResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BattleUnitTests {

    private lateinit var fakeProvider: FakeCharactersProvider
    private lateinit var battleController: BattleController

    @Test
    fun canGetHeroes() {
        val responses = arrayOf(
            CharacterResponse("Batman", 8.3, "hero"),
            CharacterResponse("Joker", 8.2, "villain")
        )

        val fakeResponse = CharactersResponse(responses)

        fakeProvider = FakeCharactersProvider(fakeResponse)
        battleController = BattleController(fakeProvider, ObjectMapper())

        // Given
        val hero = "Batman"
        val villain = "Joker"

        // When
        val responseBody = battleController.battle(hero, villain)

        // Then
        Assert.assertTrue(responseBody.body == "{\"name\":\"Batman\",\"score\":8.3,\"type\":\"hero\"}")
    }
}
