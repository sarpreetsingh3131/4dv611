package org.material.service.utils;

import org.domain.model.Image;
import org.domain.model.Manual;
import org.material.service.dto.ImageDto;
import org.material.service.dto.ManualDto;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileHandler {

    private final String DIR = System.getProperty("user.dir");
    private final String HOST = "http://localhost:8080";

    public String writeFile(ImageDto imageDto) throws Exception {
        return writeFile(imageDto.getBase64Binary(), imageDto.getExtension());
    }

    public String writeFile(ManualDto manualDto) throws Exception {
        return writeFile(manualDto.getBase64Binary(), manualDto.getExtension());
    }

    public Boolean deleteFile(Image image) throws Exception {
        return deleteFile(image.getUrl());
    }

    public Boolean deleteFile(Manual manual) throws Exception {
        return deleteFile(manual.getUrl());
    }

    private String writeFile(String base64Binary, String extension) throws Exception {
        File file = new File(Paths.get(DIR).resolve(UUID.randomUUID().toString() + extension).toUri());
        try {
            FileOutputStream fop = new FileOutputStream(file, false);
            fop.write(DatatypeConverter.parseBase64Binary(base64Binary));
            fop.flush();
            fop.close();
        } catch (Exception e) {
            throw new Exception("Cannot write image to path = " + file.getPath());
        }
        return HOST + file.getAbsolutePath().replace(DIR, "");
    }

    private Boolean deleteFile(String url) throws Exception {
        File file = new File(Paths.get(DIR).resolve(url.replace(HOST, "").substring(1)).toUri());
        if (file.exists()) {
            return file.delete();
        }
        throw new Exception(url + " does not exists");
    }
}
