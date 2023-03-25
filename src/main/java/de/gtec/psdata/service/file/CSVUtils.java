package de.gtec.psdata.service.file;

import de.gtec.psdata.storage.entity.Frame;
import lombok.NonNull;

public class CSVUtils {

    public static String normalizeFileName(String filename, @NonNull String defaultName) {
        if (filename == null || filename.isBlank())
            filename = defaultName;

        if (!filename.endsWith(".csv"))
            filename = filename + ".csv";

        return filename;
    }

    public static String getFrameHeader() {
        return "ID,Lat,Lon,Alt,Speed,Track,Squawk,Icao,Callsign,Time\n";
    }

    public static String getLine(Frame frame) {
        String icao = frame.getIcao() == null || frame.getIcao().isBlank() ? "NA" : frame.getIcao();
        String callsign = frame.getCallsign() == null || frame.getCallsign().isBlank() ? "NA" : frame.getCallsign();
        return frame.getId() + "," + frame.getLat() + "," + frame.getLon() + "," + frame.getAltitude() + "," +
                frame.getSpeed() + "," + frame.getTrack() + "," + frame.getSquawk() + "," + icao + "," +
                callsign + "," + frame.getTimestamp() + "\n";
    }

}
