package com.testing2023.grupo1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class UserTask {
    @Id
    private Long id;
    private String username;
    private String password;

    // constructor, getters y setters
}

