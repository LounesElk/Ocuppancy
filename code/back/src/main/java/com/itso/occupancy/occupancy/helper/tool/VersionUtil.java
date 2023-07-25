package com.itso.occupancy.occupancy.helper.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class VersionUtil {

    @Value("${pom.version}")
    private String pomVersion;


    public String getPomVersion() {
        return this.pomVersion;
    }

    public static int checkMajorVersion(String version) {
        return Integer.parseInt(version.substring(0, version.indexOf('.')));
    }

}
