package de.gtec.psdata.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    MultipartFile getAllCSV(String filename);

}
