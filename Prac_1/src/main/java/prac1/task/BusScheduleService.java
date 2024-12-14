package prac1.task;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusScheduleService {

    private final BusScheduleRepository repository;

    public BusScheduleService(BusScheduleRepository repository) {
        this.repository = repository;
    }

    public List<BusScheduleDTO> getSchedulesForWeekdays() {
        List<BusSchedule> schedules = repository.findSchedulesForWeekdays();
        return schedules.stream()
            .map(this::convertToDTO) 
            .collect(Collectors.toList());
    }
    
    public List<BusScheduleDTO> getAllSchedules() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BusScheduleDTO saveSchedule(BusScheduleDTO scheduleDTO) {
        BusSchedule schedule = convertToEntity(scheduleDTO);
        BusSchedule savedSchedule = repository.save(schedule);
        return convertToDTO(savedSchedule);
    }

    public BusScheduleDTO updateSchedule(Long id, BusScheduleDTO scheduleDTO) {
        BusSchedule existingSchedule = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found with id: " + id));

        BusSchedule updatedSchedule = convertToEntity(scheduleDTO);
        updatedSchedule.setId(existingSchedule.getId());
        BusSchedule savedSchedule = repository.save(updatedSchedule);
        return convertToDTO(savedSchedule);
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

    private BusScheduleDTO convertToDTO(BusSchedule schedule) {
        BusScheduleDTO dto = new BusScheduleDTO();
        dto.setDestinationCity(schedule.getDestinationCity());
        dto.setBusNumber(schedule.getBusNumber());
        dto.setDepartureDate(schedule.getDepartureDate());
        dto.setCarrier(schedule.getCarrier());
        dto.setLicensePlate(schedule.getLicensePlate());
        dto.setTravelDurationMinutes(schedule.getTravelDurationMinutes());
        dto.setPlatformNumber(schedule.getPlatformNumber());
        return dto;
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
