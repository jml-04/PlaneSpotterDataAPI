package de.gtec.psdata.web.api;

import de.gtec.psdata.service.file.CSVUtils;
import de.gtec.psdata.service.file.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/data/api/download/file")
public class DownloadFileController {

    @Autowired
    private FileService fileService;

    @GetMapping(path = "/csv/all/filename={filename}")
    public ResponseEntity<Resource> downloadAllCSV(String filename) {
        filename = CSVUtils.normalizeFileName(filename, "ALL_CSV.csv");

        MultipartFile file = fileService.getAllCSV(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getResource());
    }

}
