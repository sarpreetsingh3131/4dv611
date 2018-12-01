package org.domain.utils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class FileHandler {

    public static final String IMAGE_DIR = System.getProperty("user.dir") + "/domain/src/main/resources/public/images/";
    public static final String MANUAL_DIR = System.getProperty("user.dir") + "/domain/src/main/resources/public/manuals/";

    public static String getImageURL(String data, String extension) throws Exception {
        return getURL(IMAGE_DIR, data, extension);
    }

    public static String getManualURL(String data, String extension) throws Exception {
        return getURL(MANUAL_DIR, data, extension);
    }

    private static String getURL(String directory, String data, String extension) throws Exception {
        File file = new File(getPath(directory, extension));
        try {
            FileOutputStream fop = new FileOutputStream(file, false);
            fop.write(DatatypeConverter.parseBase64Binary(data));
            fop.flush();
            fop.close();
        } catch (Exception e) {
            throw new Exception("Cannot write file to path = " + file.getPath());
        }
        return file.getPath();
    }

    private static String getPath(String directory, String extension) {
        String path = directory + UUID.randomUUID().toString() + extension;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0) {
            return path.replaceAll("/", "\\");
        }
        return path;
    }
}
