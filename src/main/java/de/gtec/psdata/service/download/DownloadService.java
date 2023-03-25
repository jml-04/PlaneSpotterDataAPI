package de.gtec.psdata.service.download;

import de.gtec.psdata.storage.entity.Area;
import de.gtec.psdata.storage.entity.Frame;

public interface DownloadService {

    long downloadSize();

    Frame download(long id);

    Frame[] downloadAllByIds(long[] ids);

    Frame[] downloadAll();

    Frame[] downloadAllByAltitudeBetween(int min, int max);

    Frame[] downloadAllBySpeedBetween(int min, int max);

    Frame[] downloadAllByTrackBetween(int min, int max);

    Frame[] downloadAllByTimestampBetween(long min, long max);

    Frame[] downloadAllByLatBetweenAndLonBetween(float latTopLeft, float latBottomRight,
                                                 float lonTopLeft, float lonBottomRight);

    Frame[] downloadAllByIcao(String icao);

    Frame[] downloadAllByCallsign(String callsign);

    default Frame[] downloadAllByArea(Area area) {
        return downloadAllByLatBetweenAndLonBetween(
                area.getLatTopLeft(), area.getLatBottomRight(),
                area.getLonTopLeft(), area.getLonBottomRight()
        );
    }



}
