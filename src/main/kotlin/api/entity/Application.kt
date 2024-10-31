package com.example.api.entity

data class Application(
    val content: String?, // 试试，因为有个mod没翻译我自己来试试。话说一不小心点到别的内容申请就没啦啊啊啊。
    val createdAt: String?, // 2024-10-16T15:22:36.789Z
    val detail: Detail?,
    val id: Int?, // 25488
    val `operator`: Operator?,
    val permission: Int?, // 1
    val project: Int?, // 967
    val reason: String?, // 欢迎加入，请加入汉化维护群。另外请特别仔细地阅读我们的翻译规范，总得来说英文水平大概够，但是中文表述水平有待改进
    val status: Int?, // 2
    val tests: Int?, // 5
    val uid: Int?, // 55978
    val user: User?
) {
    data class Detail(
        val english: English?,
        val experience: Experience?,
        val gameTime: Int? // 0
    ) {
        data class English(
            val detail: String?,
            val level: String? // highSchool
        )

        data class Experience(
            val detail: String?,
            val times: Int? // 0
        )
    }

    data class Operator(
        val avatar: String?, // https://static.paratranz.cn/media/7ee6156b449c1749dda458cae99beaf6.png!320
        val id: Int?, // 16263
        val lastVisit: String?, // 2024-10-31T00:26:50.087Z
        val nickname: String?, // 汞与银
        val username: String? // KrukaL
    )

    data class User(
        val avatar: String?, // https://avatars.githubusercontent.com/u/185212544?v=4
        val id: Int?, // 55978
        val lastVisit: String?, // 2024-10-25T10:26:39.221Z
        val nickname: String?, // CLKS
        val username: String? // SkollClassic
    )
}