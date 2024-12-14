package prac1.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {

    @Query(value = "SELECT * FROM bus_schedule WHERE EXTRACT(ISODOW FROM departure_date) BETWEEN 1 AND 5", nativeQuery = true)
    List<BusSchedule> findSchedulesForWeekdays();
}
