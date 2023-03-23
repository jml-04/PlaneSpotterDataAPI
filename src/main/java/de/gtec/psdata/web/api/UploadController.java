package de.gtec.psdata.web.api;

import de.gtec.psdata.service.upload.UploadService;
import de.gtec.psdata.storage.entity.Frame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/data/api/upload")
public class UploadController {

    @Autowired
    private UploadService uploader;

    @PostMapping(path = "/frames")
    public ResponseEntity<?> upload(@RequestBody Frame[] data) {
        if (data == null || data.length == 0)
            return ResponseEntity.notFound().build();

        if (uploader.uploadAll(data))
            log.info("Data uploaded, size=" + data.length);
        else
            log.info("Data could not be uploaded!");
        return ResponseEntity.accepted()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Build successfully");
    }

}
