package com.pranshu.linkedInProject.connectionsService.controller;

import com.pranshu.linkedInProject.connectionsService.entity.Person;
import com.pranshu.linkedInProject.connectionsService.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId) {

        List<Person> personList = connectionsService.getFirstDegreeConnections(userId);
        return ResponseEntity.ok(personList);

    }
}
