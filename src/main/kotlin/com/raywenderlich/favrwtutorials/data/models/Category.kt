package com.raywenderlich.favrwtutorials.data.models

enum class Category {
    IOS_AND_KOTLIN,
    ANDROID_AND_KOTLIN,
    SERVER_SIDE_SWIFT,
    UNITY,
    FLUTTER;

    companion object {
        fun toStorage(category: Category) : Int =
            when (category) {
                IOS_AND_KOTLIN -> 0
                ANDROID_AND_KOTLIN -> 1
                SERVER_SIDE_SWIFT -> 2
                UNITY -> 3
                FLUTTER -> 4
            }

        fun fromStorage(num: Int): Category =
            when(num) {
                0 -> IOS_AND_KOTLIN
                1 -> ANDROID_AND_KOTLIN
                2 -> SERVER_SIDE_SWIFT
                3 -> UNITY
                else -> FLUTTER
            }
    }
}