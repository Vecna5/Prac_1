package prac1.task;

import org.springframework.stereotype.Service;
import prac1.task.BusSchedule;
import prac1.task.BusScheduleRepository;
import prac1.task.BusScheduleDTO;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusScheduleService {

    private final BusScheduleRepository repository;

    public BusScheduleService(BusScheduleRepository repository) {
        this.repository = repository;
    }

    public List<BusSchedule> getAllSchedules() {
        return repository.findAll();
    }

    public BusSchedule saveSchedule(BusScheduleDTO scheduleDTO) {
        BusSchedule schedule = convertToEntity(scheduleDTO);
        return repository.save(schedule);
    }

    public BusSchedule updateSchedule(Long id, BusScheduleDTO scheduleDTO) {
        BusSchedule existingSchedule = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found with id: " + id));
        BusSchedule updatedSchedule = convertToEntity(scheduleDTO);
        updatedSchedule.setId(existingSchedule.getId());
        return repository.save(updatedSchedule);
    }

    public void deleteSchedule(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Schedule not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public List<?> getSchedulesGroupedByWeekday() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(schedule -> schedule.getDepartureDate().getDayOfWeek()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() != DayOfWeek.SATURDAY && entry.getKey() != DayOfWeek.SUNDAY)
                .collect(Collectors.toList());
    }

    private BusSchedule convertToEntity(BusScheduleDTO dto) {
        BusSchedule schedule = new BusSchedule();
        schedule.setDestinationCity(dto.getDestinationCity());
        schedule.setBusNumber(dto.getBusNumber());
        schedule.setDepartureDate(dto.getDepartureDate());
        schedule.setCarrier(dto.getCarrier());
        schedule.setLicensePlate(dto.getLicensePlate());
        schedule.setTravelDurationMinutes(dto.getTravelDurationMinutes());
        schedule.setPlatformNumber(dto.getPlatformNumber());
        return schedule;
    }
}
