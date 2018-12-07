package org.material.service.utils;

import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileHandler {

    private final String DIR = System.getProperty("user.dir");

    public String writeFile(String data, String extension) throws Exception {
        File file = new File(Paths.get(DIR).resolve(UUID.randomUUID().toString() + extension).toUri());
        try {
            FileOutputStream fop = new FileOutputStream(file, false);
            fop.write(DatatypeConverter.parseBase64Binary(data));
            fop.flush();
            fop.close();
        } catch (Exception e) {
            throw new Exception("Cannot write image to path = " + file.getPath());
        }
        return file.getAbsolutePath().replace(DIR, "");
    }

    public Boolean deleteFile(String url) throws Exception {
        File file = new File(Paths.get(DIR).resolve(url.substring(1)).toUri());
        if (file.exists()) {
            return file.delete();
        }
        throw new Exception(url + " does not exists");
    }
}
