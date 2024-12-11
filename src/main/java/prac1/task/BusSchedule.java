package prac1.task;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bus_schedule")
public class BusSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(nullable = false)
    private String destinationCity;

    @Column(nullable = false)
    private String busNumber;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column(nullable = false)
    private String carrier;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private int travelDurationMinutes;

    @Column(nullable = false)
    private String platformNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getTravelDurationMinutes() {
        return travelDurationMinutes;
    }

    public void setTravelDurationMinutes(int travelDurationMinutes) {
        this.travelDurationMinutes = travelDurationMinutes;
    }

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber;
    }
}
