package com.ssafy.enjoytrip.global.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadUtil {

    @Value("${file.imageDir}")
    public String imgFolder;

    public String saveImage(MultipartFile file) throws IOException {
        StringBuilder sb = new StringBuilder();
        File folder = new File(imgFolder);
        if(!folder.exists()) folder.mkdir();

        UUID uuid = UUID.randomUUID();
        sb.append(uuid).append(file.getOriginalFilename());
        String saveImgPath = sb.toString();
        file.transferTo(new File(folder, saveImgPath));
        return saveImgPath;
    }
}
