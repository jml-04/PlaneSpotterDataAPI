package de.gtec.psdata.service.file;

import de.gtec.psdata.service.download.DownloadService;
import de.gtec.psdata.storage.entity.Frame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private DownloadService downloader;

    @Nullable
    @Override
    public MultipartFile getAllCSV(String filename) {
        long size = downloader.downloadSize();
        if (size == 0) {
            return null;
        }
        Frame[] frames = downloader.downloadAll();
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(CSVUtils.getFrameHeader());
        for (Frame frame : frames) {
            csvBuilder.append(CSVUtils.getLine(frame));
        }
        byte[] data = csvBuilder
                .toString()
                .getBytes(StandardCharsets.UTF_8);

        return new MultipartSourceFile(filename, data);
    }
}
