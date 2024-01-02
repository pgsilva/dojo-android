package com.example.dojo.dao

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

private fun fixtureItems() = mutableListOf(
    SearchItem(
        "https://avatars.githubusercontent.com/u/74172618?v=4",
        "Mr. Robot",
        "mrrobot",
        "Elliot is a young programmer who works as a cyber security engineer by day and a vigilante hacker by night."
    ),
    SearchItem(
        "https://avatars.githubusercontent.com/u/31107814?v=4",
        "Paulo Guilherme",
        "pgsilva",
        "computer science student at USJT full stack developer at @itau, genin since 98’s"
    ),
    SearchItem(
        "https://avatars.githubusercontent.com/u/89589526?v=4",
        "Miles Morales",
        "milesmorales",
        "Miles Morales is a character present in Marvel Comics, who first appeared in Ultimate Fallout #4."
    ),
    SearchItem(
        "https://avatars.githubusercontent.com/u/34991755?v=4",
        "Bojack Horseman",
        "bojackhorseman",
        "Aren't you the horse from \"Horsin Around\"?"
    ),
    SearchItem(
        "https://avatars.githubusercontent.com/u/5826579?v=4",
        "Rick Sanchez",
        "ricksanchez",
        "Rick Sanchez is one of the protagonists of the animated series Rick and Morty. Rick is a mad and amoral scientist who, after disappearing for 20 years, returns to his daughter."
    ),
    SearchItem(
        "https://avatars.githubusercontent.com/u/30023467?v=4",
        "Morty Smith",
        "mortysmith",
        "Morty is Rick Sanchez's grandson and was often forced to tag along on his various misadventures as a sidekick whilst attending Harry Herpson High School along with his sister."
    )
)