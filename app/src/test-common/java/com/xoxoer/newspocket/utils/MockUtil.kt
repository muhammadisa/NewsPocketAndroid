package com.xoxoer.newspocket.utils

import com.xoxoer.newspocket.model.example.Example

object MockUtil {
    fun mockExample() = Example(
        id = 1,
        postId = 2,
        body = "example_body",
        email = "example@example.com",
        name = "example_name"
    )
}