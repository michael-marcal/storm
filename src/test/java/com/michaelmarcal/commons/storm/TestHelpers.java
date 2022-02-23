package com.michaelmarcal.commons.storm;

import java.io.File;

public class TestHelpers {

    public String getResourceFilePath(String resourceName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        return file.getAbsolutePath();
    }
}
