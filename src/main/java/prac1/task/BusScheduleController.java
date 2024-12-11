package prac1.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.Entity;
import javax.persistence.Id;


import java.util.List;
@Entity
@RestController
@RequestMapping("/api/schedules")
public class BusScheduleController {

    @Id
    private Long id;

    private final BusScheduleService service;

    public BusScheduleController(BusScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public List<BusSchedule> getAllSchedules() {
        return service.getAllSchedules();
    }

    @PostMapping
    public ResponseEntity<BusSchedule> createSchedule(@RequestBody BusSchedule schedule) {
        BusSchedule savedSchedule = service.saveSchedule(schedule);
        return ResponseEntity.ok(savedSchedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusSchedule> updateSchedule(@PathVariable Long id, @RequestBody BusSchedule schedule) {
        BusSchedule updatedSchedule = service.updateSchedule(id, schedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        service.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
