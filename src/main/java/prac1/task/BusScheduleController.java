package prac1.task;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class BusScheduleController {

    private final BusScheduleService service;

    public BusScheduleController(BusScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public List<BusSchedule> getAllSchedules() {
        return service.getAllSchedules();
    }

    @PostMapping
    public ResponseEntity<BusSchedule> createSchedule(@Valid @RequestBody BusScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(service.saveSchedule(scheduleDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusSchedule> updateSchedule(@PathVariable Long id, @Valid @RequestBody BusScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(service.updateSchedule(id, scheduleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        service.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/grouped")
    public ResponseEntity<List<?>> getGroupedSchedules() {
        List<?> groupedSchedules = service.getSchedulesGroupedByWeekday();
        return ResponseEntity.ok(groupedSchedules);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Validation failed: ");
        ex.getBindingResult().getAllErrors().forEach(error -> 
            errorMessage.append(error.getDefaultMessage()).append(". ")
        );
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }
}
