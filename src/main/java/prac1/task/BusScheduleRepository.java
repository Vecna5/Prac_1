package prac1.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {
    // Дополнительные методы можно добавить при необходимости
    @Query("SELECT s FROM BusSchedule s WHERE FUNCTION('DAY_OF_WEEK', s.departureDate) BETWEEN 2 AND 6")
    List<BusSchedule> findSchedulesForWeekdays();
}
