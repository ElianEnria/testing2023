package com.testing2023.grupo1.Repository;

import com.testing2023.grupo1.Entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTask, Long> {
}
