package com.buildcircle.superheroes.battle

import com.buildcircle.superheroes.characters.CharacterResponse
import com.buildcircle.superheroes.characters.CharactersProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BattleController @Autowired constructor(
    private val charactersProvider: CharactersProvider,
    private val objectMapper: ObjectMapper
) {

    private lateinit var character1: CharacterResponse
    private lateinit var character2: CharacterResponse

    @GetMapping("/battle")
    fun battle(
        @RequestParam(value = "hero") hero: String,
        @RequestParam(value = "villain") villain: String
    ): ResponseEntity<String> {

        val characters = charactersProvider.getCharacters()

        for (character in characters.items) {
            if (character.name == hero) {
                character1 = character
            }

            if (character.name == villain) {
                character2 = character
            }
        }

        if (character1.score > character2.score) {
            return ResponseEntity.ok(objectMapper.writeValueAsString(character1))
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(character2))
    }
}
