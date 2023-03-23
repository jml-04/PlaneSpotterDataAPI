package de.gtec.psdata.service.upload;

import de.gtec.psdata.storage.FrameRepository;
import de.gtec.psdata.storage.entity.Frame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FrameRepository frameRepo;

    @Override
    public boolean uploadAll(Frame[] dps) {
        if (dps == null || dps.length == 0) {
            return false;
        }
        List<Frame> frames = Arrays.stream(dps).toList();
        Iterable<Frame> saved = frameRepo.saveAll(frames);

        Iterator<Frame> it = saved.iterator();
        int savedCount = 0;
        while (it.hasNext()) {
            it.next();
            savedCount++;
        }

        return dps.length == savedCount;
    }
}
