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

package com.google.example.javafriendlykotlin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UseCase {
    public static User registerGuest(String name) {
        User guest = new User(Repository.getNextGuestId(), StringUtils.nameToLogin(name), name);
        Repository.addUser(guest);
        return guest;
    }

    public static List<User> getSystemUsers() {
        ArrayList<User> systemUsers = new ArrayList<>();
        for (User user : Repository.getUsers()) {
            if (user.hasSystemAccess()) {
                systemUsers.add(user);
            }
        }
        return systemUsers;
    }

    public static String formatUser(User user) {
        return String.format("%s (%s:%d)", user.displayName, user.username, user.id);
    }

    public static void backupUsers() {
        try {
            if (!Repository.saveAs(Repository.BACKUP_PATH)) {
                // TODO: Report error backing up user database.
            }
        } catch (IOException e) {
            // Log exception.
        }
    }
}
