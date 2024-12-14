package io.jenkins.plugins.blueking.utils;

import com.cloudbees.plugins.credentials.CredentialsProvider;
import com.cloudbees.plugins.credentials.common.StandardCredentials;
import java.util.Collections;
import java.util.Objects;
import jenkins.model.Jenkins;

/**
 * @author Bruce.Wu
 * @date 2024-12-11
 */
public final class Utils {

    private Utils() {}

    public static boolean isNullOrEmpty(final String name) {
        return name == null || name.matches("\\s*");
    }

    public static boolean isNotEmpty(final String name) {
        return !isNullOrEmpty(name);
    }

    public static <T extends StandardCredentials> T findCredential(String credentialsId, Class<T> clazz) {
        StandardCredentials credentials = null;
        if (Utils.isNullOrEmpty(credentialsId)) {
            return null;
        }

        for (T c :
                CredentialsProvider.lookupCredentialsInItemGroup(clazz, Jenkins.get(), null, Collections.emptyList())) {
            if (Objects.equals(c.getId(), credentialsId)) {
                return c;
            }
        }

        return null;
    }
}