package de.gtec.psdata.service.download;

import de.gtec.psdata.service.Iterables;
import de.gtec.psdata.storage.FrameRepository;
import de.gtec.psdata.storage.entity.Frame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.LongStream;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private FrameRepository frameRepo;

    @Override
    public Frame download(long id) {
        return frameRepo.findById(id)
                .orElse(null);
    }

    @Override
    public Frame[] downloadAll() {
        Iterable<Frame> frames = frameRepo.findAll();
        return Iterables.toFrameArray(frames);
    }

    @Override
    public Frame[] downloadAllByIds(long[] ids) {
        List<Long> idList = LongStream.of(ids)
                .boxed()
                .toList();
        Iterable<Frame> frames = frameRepo.findAllById(idList);

        return Iterables.toFrameArray(frames);
    }

    @Override
    public Frame[] downloadAllByAltitudeBetween(int min, int max) {
        Iterable<Frame> frames = frameRepo.findAllByAltitudeBetween(min, max);
        return Iterables.toFrameArray(frames);
    }

    @Override
    public Frame[] downloadAllBySpeedBetween(int min, int max) {
        Iterable<Frame> frames = frameRepo.findAllBySpeedBetween(min, max);
        return Iterables.toFrameArray(frames);
    }

    @Override
    public Frame[] downloadAllByTrackBetween(int min, int max) {
        Iterable<Frame> frames = frameRepo.findAllByTrackBetween(min, max);
        return Iterables.toFrameArray(frames);
    }

    @Override
    public Frame[] downloadAllByTimestampBetween(long min, long max) {
        Iterable<Frame> frames = frameRepo.findAllByTimestampBetween(min, max);
        return Iterables.toFrameArray(frames);
    }

    @Override
    public Frame[] downloadAllByLatBetweenAndLonBetween(float latTopLeft, float latBottomRight, 
                                                        float lonTopLeft, float lonBottomRight) {
        Iterable<Frame> frames = frameRepo.findAllByLatBetweenAndLonBetween(latTopLeft, latBottomRight, lonTopLeft, lonBottomRight);
        return Iterables.toFrameArray(frames);
    }
}
