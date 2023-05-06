package com.testing2023.grupo1.Repository;

import com.testing2023.grupo1.Entity.Task;
import com.testing2023.grupo1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserIdAndDateTimeGreaterThan(Long userId, Date dateTime);
    List<Task> findByUserAndDateTimeAfter(User user, LocalDateTime dateTime);
}
