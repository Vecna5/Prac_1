package prac1.task;

import javax.validation.constraints.*;
import java.time.LocalDate;

    public class BusScheduleDTO {

        @NotNull(message = "Destination city is required.")
        @Size(min = 2, max = 100, message = "Destination city must be between 2 and 100 characters.")
        private String destinationCity;
    
        @NotNull(message = "Bus number is required.")
        @Size(min = 1, max = 20, message = "Bus number must be between 1 and 20 characters.")
        private String busNumber;
    
        @NotNull(message = "Departure date is required.")
        private LocalDate departureDate;
    
        @NotNull(message = "Carrier is required.")
        @Size(min = 2, max = 50, message = "Carrier must be between 2 and 50 characters.")
        private String carrier;
    
        @NotNull(message = "License plate is required.")
        @Pattern(regexp = "^[A-Z0-9-]+$", message = "License plate must be alphanumeric.")
        private String licensePlate;
    
        @NotNull(message = "Travel duration is required.")
        @Min(value = 1, message = "Travel duration must be at least 1 minute.")
        @Max(value = 1440, message = "Travel duration must be less than 1440 minutes.")
        private int travelDurationMinutes;
    
        @NotNull(message = "Platform number is required.")
        @Size(min = 1, max = 10, message = "Platform number must be between 1 and 10 characters.")
        private String platformNumber;
    

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
