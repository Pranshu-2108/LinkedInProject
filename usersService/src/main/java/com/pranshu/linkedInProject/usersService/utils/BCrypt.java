package com.pranshu.linkedInProject.usersService.utils;

import static org.mindrot.jbcrypt.BCrypt.*;

public class BCrypt {

    public static String hash(String password) {
        return hashpw(password, gensalt());
    }

    public static boolean match(String password, String hash) {
        return checkpw(password, hash);
    }
}
