package com.tcs.test.animemangatoon.utils

import com.tcs.test.animemangatoon.data.model.Article

object Articles {
    val list = listOf(
        Article(
            img = "solo_leveling.jpg", // Replace with the correct path or URL to the image
            imgSmall = "solo_leveling_small.jpg", // Replace with the correct path or URL to the small image
            name = "Solo Leveling",
            description = "In a world where hunters fight monsters, the weakest hunter, E-rank Sung Jin-Woo, finds himself in a deadly dungeon. After a fateful encounter, he gains a unique ability to level up and become the strongest hunter.",
            writer = "Chugong",
            art = "REDICE STUDIO",
            reads = "2.5B",
            isFav = false,
            isReviewed = false,
            rating = 5
        ),
        Article(
            img = "estate_large.jpg", // Replace with the correct path or URL to the image
            imgSmall = "estate_small.jpg", // Replace with the correct path or URL to the small image
            name="The Greatest Estate Developer",
            description = "After being betrayed, a man is reborn with the ability to develop real estate in a fantasy world. He uses his knowledge to become the greatest estate developer and change his fate.",
            writer = "Lee Hyunmin, Kim Hyunsoo",
            art = "Youngwoo",
            reads =  "87.3M",
            isFav = true,
            isReviewed = false,
            rating = 4
        ),
        Article(
            img = "returner_large.jpg", // Replace with the correct path or URL to the image
            imgSmall = "returner_small.jpg", // Replace with the correct path or URL to the small image
            name = "The Returner's Magic Should be Special",
            description = "Magic is a part of everyday life in this world. The protagonist, a returner, seeks to change the past and overcome obstacles using his unique knowledge of magic.",
            writer = "Hee-joon",
            art = "Lyu",
            reads = "45.2M",
            isFav = false,
            isReviewed = true,
            rating = 4
        ),
        Article(
            img = "tower_large.jpg", // Replace with the correct path or URL to the image
            imgSmall = "tower_small.jpg", // Replace with the correct path or URL to the small image
            name = "Tower of God",
            description = "A young boy named Bam enters the Tower of God, seeking to find his friend Rachel. He must face numerous challenges and enemies as he climbs the tower.",
            writer = "SIU",
            art = "SIU",
            reads = "20M",
            isFav = true,
            isReviewed = true,
            rating = 5
        ),
        Article(
            img = "god_large.jpg", // Replace with the correct path or URL to the image
            imgSmall = "god_small.jpg", // Replace with the correct path or URL to the small image
            name = "God of HighSchool",
            description = "A martial arts tournament brings together the best fighters from high schools across Korea. Jin Mori, the main protagonist, fights to prove himself and uncover the secrets of his past.",
            writer = "Yongje Park",
            art = "Yongje Park",
            reads = "100M",
            isFav = false,
            isReviewed = true,
            rating = 4
        )
    )
}
