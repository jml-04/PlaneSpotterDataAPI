package de.gtec.psdata.web.api;

import de.gtec.psdata.service.download.DownloadService;
import de.gtec.psdata.storage.entity.Area;
import de.gtec.psdata.storage.entity.Frame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/data/api/download")
public class DownloadController {

    @Autowired
    private DownloadService downloader;

    @GetMapping(path = "/all")
    public ResponseEntity<?> downloadAll() {
        Frame[] frames = downloader.downloadAll();
        if (frames == null || frames.length == 0)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(frames);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<?> downloadFrameCount() {
        long count = downloader.downloadSize();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(count);
    }

    @GetMapping(path = "/id={id}")
    public ResponseEntity<?> download(@PathVariable int id) {
        Frame frame = downloader.download(id);
        if (frame == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(frame);
    }

    @GetMapping(path = "/between/target={target}&min={min}&max={max}")
    public ResponseEntity<?> between(@PathVariable String target, @PathVariable Number min, @PathVariable Number max) {
        Frame[] frames = switch (target) {
            case "alt", "altitude" -> downloader.downloadAllByAltitudeBetween(min.intValue(), max.intValue());
            case "speed", "groundspeed" -> downloader.downloadAllBySpeedBetween(min.intValue(), max.intValue());
            case "track", "heading" -> downloader.downloadAllByTrackBetween(min.intValue(), max.intValue());
            case "time", "timestamp" -> downloader.downloadAllByTimestampBetween(min.longValue(), max.longValue());
            default -> null;
        };

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(frames);
    }

    @GetMapping(path = "/area/{latTopLeft}&{latBottomRight}&{lonTopLeft}&{lonBottomRight}")
    public ResponseEntity<?> area(@PathVariable float latTopLeft, @PathVariable float latBottomRight,
                                  @PathVariable float lonTopLeft, @PathVariable float lonBottomRight) {

        // overhead object, but prettier code
        Area area = new Area(latTopLeft, latBottomRight, lonTopLeft, lonBottomRight);

        // downloaded frames
        Frame[] frames = downloader.downloadAllByArea(area);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(frames);

    }

    @GetMapping(path = "/icao/icao={icao}")
    public ResponseEntity<?> icao(String icao) {
        Frame[] frames = downloader.downloadAllByIcao(icao);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(frames);
    }

    @GetMapping(path = "/callsign/callsign={callsign}")
    public ResponseEntity<?> callsign(String callsign) {
        Frame[] frames = downloader.downloadAllByCallsign(callsign);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(frames);
    }

}
