package com.example.dojo.dao

import com.example.dojo.R
import com.example.dojo.domain.search.SearchItem

class SearchItemsProvider {

    companion object {
        private val items: MutableList<SearchItem> = fixtureItems()
    }

    fun add(item: SearchItem) {
        items.add(item)
    }

    fun load() = items.toList()
}

fun fixtureCoverImage() =
    listOf(
        R.drawable.asian,
        R.drawable.cyberpunk,
        R.drawable.marvel_1,
        R.drawable.tech_1,
        R.drawable.tech_2,
        R.drawable.tech_3,
        R.drawable.trip,
        R.drawable.wave,
    ).random()

private fun fixtureItems() = mutableListOf(
    SearchItem(
        "",
        "Mr. Robot",
        "mrrobot",
        "Elliot is a young programmer who works as a cyber security engineer by day and a vigilante hacker by night."
    ),
    SearchItem(
        "",
        "Paulo Guilherme",
        "pgsilva",
        "computer science student at USJT full stack developer at @itau, genin since 98’s"
    ),
    SearchItem(
        "",
        "Miles Morales",
        "milesmorales",
        "Miles Morales is a character present in Marvel Comics, who first appeared in Ultimate Fallout #4."
    ),
    SearchItem(
        "",
        "Bojack Horseman",
        "bojackhorseman",
        "Aren't you the horse from \"Horsin Around\"?"
    ),
    SearchItem(
        "",
        "Rick Sanchez",
        "ricksanchez",
        "Rick Sanchez is one of the protagonists of the animated series Rick and Morty. Rick is a mad and amoral scientist who, after disappearing for 20 years, returns to his daughter."
    ),
    SearchItem(
        "",
        "Morty Smith",
        "mortysmith",
        "Morty is Rick Sanchez's grandson and was often forced to tag along on his various misadventures as a sidekick whilst attending Harry Herpson High School along with his sister."
    )
)