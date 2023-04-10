/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("StringUtils")

package com.google.example.javafriendlykotlin

fun String.toTitleCase(): String {
    if (isNullOrBlank()) {
        return this
    }

    return split(" ").map { word ->
        word.foldIndexed("") { index, working, char ->
            val nextChar = if (index == 0) char.toUpperCase() else char.toLowerCase()
            "$working$nextChar"
        }
    }.reduceIndexed { index, working, word ->
        if (index > 0) "$working $word" else word
    }
}

fun String.nameToLogin(): String {
    if (isNullOrBlank()) {
        return this
    }
    var working = ""
    toCharArray().forEach { char ->
        if (char.isLetterOrDigit()) {
            working += char.toLowerCase()
        } else if (char.isWhitespace() and !working.endsWith(".")) {
            working += "."
        }
    }
    return working
}