package de.gtec.psdata.storage.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tracking")
public class Frame {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lon", nullable = false)
    private double lon;

    @Column(name = "alt", nullable = false)
    private int altitude;

    @Column(name = "speed", nullable = false)
    private int speed;

    @Column(name = "track", nullable = false)
    private int track;

    @Column(name = "squawk", nullable = false)
    private int squawk;

    @Column(name = "icao")
    private String icao;

    @Column(name = "callsign")
    private String callsign;

    @Column(name = "time", nullable = false)
    private long timestamp;

}
