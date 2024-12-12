package prac1.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class SchedulerController {

    private final BusScheduleService schedulerService;
    private final UserService userService;  

    @Value("${admin.username}")
    private String adminUsername;

    public SchedulerController(BusScheduleService schedulerService, UserService userService) {
        this.schedulerService = schedulerService;
        this.userService = userService;  
    }

    @GetMapping
    public ResponseEntity<List<BusScheduleDTO>> getAllSchedules(@RequestHeader("Authorization") String token) {
        if (validateToken(token)) {
            List<BusScheduleDTO> schedules = schedulerService.getAllSchedules();
            return ResponseEntity.ok(schedules);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping
    public ResponseEntity<BusScheduleDTO> createSchedule(@RequestHeader("Authorization") String token, @RequestBody BusScheduleDTO scheduleDTO) {
        if (validateToken(token)) {
            BusScheduleDTO savedSchedule = schedulerService.saveSchedule(scheduleDTO);
            return ResponseEntity.ok(savedSchedule);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusScheduleDTO> updateSchedule(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody BusScheduleDTO scheduleDTO) {
        if (validateToken(token)) {
            BusScheduleDTO updatedSchedule = schedulerService.updateSchedule(id, scheduleDTO);
            return ResponseEntity.ok(updatedSchedule);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (validateToken(token)) {
            schedulerService.deleteSchedule(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/grouped")
    public ResponseEntity<List<?>> getGroupedSchedules(@RequestHeader("Authorization") String token) {
        if (validateToken(token)) {
            List<?> groupedSchedules = schedulerService.getSchedulesGroupedByWeekday();
            return ResponseEntity.ok(groupedSchedules);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token) {
        if (validateToken(token)) {
            List<User> users = userService.getAllUsers();  
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Validation failed: ");
        ex.getBindingResult().getAllErrors().forEach(error -> 
            errorMessage.append(error.getDefaultMessage()).append(". ")
        );
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }

    private boolean validateToken(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            String[] parts = decoded.split(":");
            String username = parts[0];
            long expirationTime = Long.parseLong(parts[1]);

            if (adminUsername.equals(username) && expirationTime > System.currentTimeMillis()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
