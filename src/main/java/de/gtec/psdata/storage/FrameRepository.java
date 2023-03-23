package de.gtec.psdata.storage;

import de.gtec.psdata.storage.entity.Area;
import de.gtec.psdata.storage.entity.Frame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface FrameRepository extends CrudRepository<Frame, Long> {

    boolean existsByIcao(String icao);

    boolean existsByCallsign(String callsign);

    Iterable<Frame> findAllByAltitudeBetween(int min, int max);

    Iterable<Frame> findAllBySpeedBetween(int min, int max);

    Iterable<Frame> findAllByTrackBetween(int min, int max);

    Iterable<Frame> findAllByTimestampBetween(long min, long max);

    Iterable<Frame> findAllByLatBetweenAndLonBetween(double latTopLeft, double latBottomRight,
                                                     double lonTopLeft, double lonBottomRight);

    Iterable<Frame> findAllByIcao(String icao);

    Iterable<Frame> findAllByCallsign(String callsign);

    default Iterable<Frame> findAllByArea(Area area) {
        return findAllByLatBetweenAndLonBetween(
                area.getLatTopLeft(), area.getLatBottomRight(),
                area.getLonTopLeft(), area.getLonBottomRight()
        );
    }



}
