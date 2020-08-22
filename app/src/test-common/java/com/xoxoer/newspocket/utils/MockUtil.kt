package com.xoxoer.newspocket.utils

import com.xoxoer.newspocket.model.example.Example
import com.xoxoer.newspocket.model.headline.Article
import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Source
import com.xoxoer.newspocket.model.source.Sources

object MockUtil {
    fun mockExample() = Example(
        id = 1,
        postId = 2,
        body = "example_body",
        email = "example@example.com",
        name = "example_name"
    )

    fun mockSource() = Sources(
        listOf(
            Source(
                "general",
                "us",
                "source_description",
                "source_id",
                "en",
                "source_name",
                "https://abcnews.go.com"
            )
        ),
        "ok"
    )

    fun mockHeadline() = Headlines(
        listOf(
            Article(
                "https://www.facebook.com/bbcnews",
                "article_content",
                "article_description",
                "2020-08-21T16:18:45Z",
                com.xoxoer.newspocket.model.headline.Source(
                    "article_source_id",
                    "article_source_name"
                ),
                "article_title",
                "https://www.bbc.com/news/world-europe-53865811",
                "https://ichef.bbci.co.uk/news/1024/branded_news/5B9D/production/_114035432_mediaitem114035431.jpg"
            )
        ),
        "ok",
        38
    )
}