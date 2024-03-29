package com.tech.demo.db

object Constants {

    object DataBase {
        const val DATABASE_VERSION = 1
    }

    object Stories {
        const val TABLE_NAME = "stories"
        const val QUERY_STORIES = "SELECT * FROM ${Stories.TABLE_NAME}"
    }

    object Videos {
        const val TABLE_NAME = "videos"
        const val QUERY_VIDEOS = "SELECT * FROM ${Videos.TABLE_NAME}"
    }

}