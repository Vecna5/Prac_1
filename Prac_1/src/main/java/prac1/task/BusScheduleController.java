package prac1.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bus-schedules")
public class BusScheduleController {

    private final BusScheduleService service;

    public BusScheduleController(BusScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public List<BusScheduleDTO> getAllBusSchedules() {
        return service.getAllSchedules();
    }

    @PostMapping
    public ResponseEntity<BusScheduleDTO> createBusSchedule(@RequestBody BusScheduleDTO scheduleDTO) {
        BusScheduleDTO savedSchedule = service.saveSchedule(scheduleDTO);
        return ResponseEntity.ok(savedSchedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusScheduleDTO> updateBusSchedule(@PathVariable Long id, @RequestBody BusScheduleDTO scheduleDTO) {
        BusScheduleDTO updatedSchedule = service.updateSchedule(id, scheduleDTO);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusSchedule(@PathVariable Long id) {
        service.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
