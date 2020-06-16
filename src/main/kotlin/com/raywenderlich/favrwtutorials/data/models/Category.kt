package com.raywenderlich.favrwtutorials.data.models

enum class Category {
    IOS_AND_KOTLIN,
    ANDROID_AND_KOTLIN,
    SERVER_SIDE_SWIFT,
    UNITY,
    FLUTTER;

    companion object {
        private const val IOS_AND_KOTLIN_STR = "IOS_AND_KOTLIN"
        private const val ANDROID_AND_KOTLIN_STR = "ANDROID_AND_KOTLIN"
        private const val SERVER_SIDE_SWIFT_STR = "SERVER_SIDE_SWIFT"
        private const val UNITY_STR = "UNITY"
        private const val FLUTTER_STR = "FLUTTER"


        fun toStorage(category: Category) : String =
            when (category) {
                IOS_AND_KOTLIN -> IOS_AND_KOTLIN_STR
                ANDROID_AND_KOTLIN -> ANDROID_AND_KOTLIN_STR
                SERVER_SIDE_SWIFT -> SERVER_SIDE_SWIFT_STR
                UNITY -> UNITY_STR
                FLUTTER -> FLUTTER_STR
            }

        fun fromStorage(cat: String): Category =
            when(cat) {
                IOS_AND_KOTLIN_STR -> IOS_AND_KOTLIN
                ANDROID_AND_KOTLIN_STR -> ANDROID_AND_KOTLIN
                SERVER_SIDE_SWIFT_STR -> SERVER_SIDE_SWIFT
                UNITY_STR -> UNITY
                else -> FLUTTER
            }
    }
}