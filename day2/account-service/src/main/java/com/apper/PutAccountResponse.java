package com.apper;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
public class PutAccountResponse {
    private LocalDateTime lastUpdate;
}
