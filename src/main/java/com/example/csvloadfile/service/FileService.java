package com.example.csvloadfile.service;

import com.example.csvloadfile.entity.Client;
import com.example.csvloadfile.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final ClientRepository clientRepository;

    private final static String NULL = "null";

    public void saveClientsFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().forEach(line -> {
                try {
                    String[] data = line.replaceAll(" ", "").split(",");
                    Client client = new Client();
                    client.setName(data[0]);
                    client.setAge(Integer.valueOf(data[1]));
                    client.setGroupId(Integer.valueOf(data[2]));
                    client.setPhone(formatPhone(data[3]));
                    client.setDate(formatDate(data[4]));
                    clientRepository.save(client);
                } catch (Exception e) {
                    log.error("ERROR PROCESSING LINE: " + line, e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatPhone(String phone) {
        if (StringUtils.isBlank(phone) || NULL.equals(phone)) {
            return null;
        }
        return phone.replaceAll("\\D+", "");
    }

    private LocalDate formatDate(String date) {
        if (StringUtils.isBlank(date) || NULL.equals(date)) {
            return null;
        }
        return LocalDate.parse(date);
    }
}
