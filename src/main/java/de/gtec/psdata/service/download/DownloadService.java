package de.gtec.psdata.service.download;

import de.gtec.psdata.storage.entity.Area;
import de.gtec.psdata.storage.entity.Frame;

public interface DownloadService {

    Frame download(long id);

    Frame[] downloadAllByIds(long[] ids);

    Frame[] downloadAll();

    Frame[] downloadAllByAltitudeBetween(int min, int max);

    Frame[] downloadAllBySpeedBetween(int min, int max);

    Frame[] downloadAllByTrackBetween(int min, int max);

    Frame[] downloadAllByTimestampBetween(long min, long max);

    Frame[] downloadAllByLatBetweenAndLonBetween(float latTopLeft, float latBottomRight,
                                                 float lonTopLeft, float lonBottomRight);

    default Frame[] downloadAllByArea(Area area) {
        return downloadAllByLatBetweenAndLonBetween(
                area.getLatTopLeft(), area.getLatBottomRight(),
                area.getLonTopLeft(), area.getLonBottomRight()
        );
    }



}
