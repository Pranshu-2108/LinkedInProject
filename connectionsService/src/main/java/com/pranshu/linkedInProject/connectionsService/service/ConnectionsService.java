package com.pranshu.linkedInProject.connectionsService.service;

import com.pranshu.linkedInProject.connectionsService.entity.Person;
import com.pranshu.linkedInProject.connectionsService.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionsService {
    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnections(Long userId) {
        log.info("getting first degree connections of user with userId : {}", userId);
        return personRepository.getFirstDegreeConnections(userId);
    }
}
