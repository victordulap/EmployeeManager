package com.step.utilities;

import java.io.File;

public class FileUtilities {
    public File newFile(String path, String fileName) {
        File file = new File(path + fileName);

        if (!file.exists()) {
            try {
                File pathToFile = new File(path);
                if(!pathToFile.exists()) {
                    pathToFile.mkdirs();
                }
                file.createNewFile();
                System.out.println("Created " + path + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
