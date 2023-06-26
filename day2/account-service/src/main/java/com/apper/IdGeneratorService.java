package com.apper;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGeneratorService {

    public String generateRandomCharacters(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public String getNextId() {
        return UUID.randomUUID().toString();
    }
}
