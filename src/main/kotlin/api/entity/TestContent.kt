package com.example.api.entity

data class TestContent(
    val id: Int, // 51440868
    val key: String, // acot_pmc.1.insult.tooltip:
    val origin: Origin,
    val original: String, // Such a sickening lifeform has no place setting up a base in this galaxy. They are nothing but just a glorified pirate, who probably just paint everything red to look edgy. Phanon Corps? Is this some kind of edgy wanna-be boogeymen? We are the Legend; we are the one who will remove this worthless fool from the galaxy that rightfully belong to us. And then we will take all of their toys since they clearly suck at using them.
    val translation: String // 这种令人作呕的生命形态，根本没资格在这个银河系立足。他们不过是一群自我陶醉的海盗，只是把一切都漆得鲜红，装出一副狂野的样子。泛恩公司？这是什么自作多情的可笑组织？我们才是真正的传奇；我们将从星系中抹除这个不值一提的蠢货。到时，我们会没收他们所有的东西，因为他们连使用它们的能力都没有。
) {
    data class Origin(
        val context: Any?, // null
        val createdAt: String, // 2020-10-22T10:07:03.000Z
        val extra: Any?, // null
        val `file`: Int, // 181710
        val fileId: Int, // 181710
        val id: Int, // 51440868
        val key: String, // acot_pmc.1.insult.tooltip:
        val original: String, // Such a sickening lifeform has no place setting up a base in this galaxy. They are nothing but just a glorified pirate, who probably just paint everything red to look edgy. Phanon Corps? Is this some kind of edgy wanna-be boogeymen? We are the Legend; we are the one who will remove this worthless fool from the galaxy that rightfully belong to us. And then we will take all of their toys since they clearly suck at using them.
        val project: Int, // 967
        val stage: Int, // 5
        val translation: String, // 如此令人作呕的生物，这个星系内没有你们的容身之所。你们不过是一帮自吹自擂的海盗，大概只是把所有东西都涂红来显得激动人心罢了。卓越集团？那是某种急着去吓唬的小孩的黑鬼吗？我们正是要将这小毛孩从属于我们的星系中抹除的天选者人。然后我们会夺得它所有的玩具，因为这小屁孩显然不知道怎么好好使用这些东西。
        val uid: Int?, // 16242
        val updatedAt: String, // 2024-11-19T12:11:04.000Z
        val user: User?,
        val version: Int?, // 3721528
        val words: Int // 79
    ) {
        data class User(
            val id: Int, // 16242
            val avatar: String?, // https://static.paratranz.cn/media/b20d258c5ba22e00558c300c741a1c33!320
            val lastVisit: String, // 2024-12-05T06:00:43.026Z
            val nickname: String?, // Ankol
            val username: String? // VioletAyatan
        )
    }
}
