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

package com.google.example.javafriendlykotlin

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

object Repository {
    const val BACKUP_PATH = "/backup/user.repo"

    private val _users = mutableListOf<User>()
    private var _nextGuestId = 1000

    @JvmStatic
    val users: List<User>
        get() = _users

    @JvmStatic
    val nextGuestId
        get() = _nextGuestId++

    init {
        _users.add(User(100, "josh", "Joshua Calvert", listOf("admin", "staff", "sys")))
        _users.add(User(101, "dahybi", "Dahybi Yadev", listOf("staff", "nodes")))
        _users.add(User(102, "sarha", "Sarha Mitcham", listOf("admin", "staff", "sys")))
        _users.add(User(103, "warlow", groups = listOf("staff", "inactive")))
    }

    @JvmStatic
    @Throws(IOException::class)
    fun saveAs(path: String?): Boolean {
        val backupPath = path ?: return false

        val outputFile = File(backupPath)
        if (!outputFile.canWrite()) {
            throw FileNotFoundException("Could not write to file: $backupPath")
        }
        // Write data...
        return true
    }

    @JvmStatic
    fun addUser(user: User) {
        // Ensure the user isn't already in the collection.
        val existingUser = users.find { user.id == it.id }
        existingUser?.let { _users.remove(it) }
        // Add the user.
        _users.add(user)
    }
}
