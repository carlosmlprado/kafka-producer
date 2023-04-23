package com.study.controller;

import com.study.dto.FamilyDTO;
import com.study.message.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class Controller {

    private KafkaProducer kafkaProducer;

    @GetMapping("/email/{number}")
    public ResponseEntity<Void> sendMessage(@PathVariable Integer number) {

        FamilyDTO familiaDTO = new FamilyDTO();
        familiaDTO.setDependents(9);
        familiaDTO.setEmail("carlos_mattheus@hotmail.com");
        familiaDTO.setPoints(90);

        for(int i = 0; i < number; i++){

            familiaDTO.setPoints(i);
            kafkaProducer.sendMessage(familiaDTO);
        }
        return null;
    }
}
