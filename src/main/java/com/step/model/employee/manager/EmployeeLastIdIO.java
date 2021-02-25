package com.step.model.employee.manager;

import com.step.utilities.FileUtilities;

import java.io.*;

/**
 * Interacts with last id, saving/loading it thru a serialized file
 */
public class EmployeeLastIdIO {
    private String path = ".\\data\\";
    private String fileName;
    private FileUtilities fileUtilities = new FileUtilities();

    public EmployeeLastIdIO( String fileName) {
        this.fileName = fileName;
    }

    public void save(Integer lastId) {
        try {
            File file = fileUtilities.newFile(path, fileName);

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeInt(lastId);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Integer load() {
        Integer lastId = 0;

        try {
            File file = new File(this.path + this.fileName);
            if (!file.exists()) {
                try {
                    File pathToFile = new File(this.path);
                    if(!pathToFile.exists()) {
                        pathToFile.mkdirs();
                    }
                    file.createNewFile();
                    System.out.println("Created " + this.path + this.fileName);

                    // ending function here, as there was no file before, so we give lastId value 0
                    return lastId;
                } catch (IOException e) {
                    System.out.println("Undetected error on file creating process.");
                }
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            lastId = objectInputStream.readInt();

            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lastId;
    }
}
