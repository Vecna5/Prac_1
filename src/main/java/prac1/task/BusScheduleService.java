package prac1.task;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BusScheduleService {

    private final BusScheduleRepository repository;

    public BusScheduleService(BusScheduleRepository repository) {
        this.repository = repository;
    }

    public List<BusSchedule> getAllSchedules() {
        return repository.findAll();
    }

    public BusSchedule saveSchedule(BusSchedule schedule) {
        return repository.save(schedule);
    }

    public BusSchedule updateSchedule(Long id, BusSchedule schedule) {
        BusSchedule existingSchedule = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found with id: " + id));
        existingSchedule.setDestinationCity(schedule.getDestinationCity());
        existingSchedule.setBusNumber(schedule.getBusNumber());
        existingSchedule.setDepartureDate(schedule.getDepartureDate());
        existingSchedule.setCarrier(schedule.getCarrier());
        existingSchedule.setLicensePlate(schedule.getLicensePlate());
        existingSchedule.setTravelDurationMinutes(schedule.getTravelDurationMinutes());
        existingSchedule.setPlatformNumber(schedule.getPlatformNumber());
        return repository.save(existingSchedule);
    }

    public void deleteSchedule(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Schedule not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
