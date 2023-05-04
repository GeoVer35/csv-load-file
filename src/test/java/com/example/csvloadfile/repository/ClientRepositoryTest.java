package com.example.csvloadfile.repository;

import com.example.csvloadfile.entity.Client;
import com.example.csvloadfile.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class ClientRepositoryTest {

    @Autowired
    private FileService loadFileService;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void getClientByAgeTest() throws IOException {
        loadFileService.saveClientsFromFile("./src/main/resources/Clients.csv");
        List<Client> clients = clientRepository.getClientsByAge(30);

        clients.forEach(client -> {
            log.info(client.toString());
            assertTrue(client.getAge() < 30);
        });
    }
}
