package org.material.service.utils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class FileHandler {

    private static final String IMAGE_DIR = System.getProperty("user.dir");

    public static String writeFile(String data, String extension) throws Exception {
        String path = IMAGE_DIR + "/" + UUID.randomUUID().toString() + extension;
        File file = new File(path.contains("\\") ? path.replace("/", "\\") : path);
        try {
            FileOutputStream fop = new FileOutputStream(file, false);
            fop.write(DatatypeConverter.parseBase64Binary(data));
            fop.flush();
            fop.close();
        } catch (Exception e) {
            throw new Exception("Cannot write image to path = " + file.getPath());
        }
        return "file://" + file.getPath();
    }

    public static boolean deleteFile(String url) throws Exception {
        File file = new File(url.replace("file://", ""));
        if (file.exists()) {
            return file.delete();
        }
        throw new Exception(url + " does not exists");
    }
}
