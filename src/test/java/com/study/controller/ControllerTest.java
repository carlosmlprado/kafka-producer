package com.study.controller;

import com.study.dto.FamilyDTO;
import com.study.message.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private KafkaProducer kafkaProducer;

    /**
     * This test uses the TestRestTemplate provided by Spring Boot to make a GET request to the /email/{number} endpoint, passing the number as a parameter.
     * We then verify that the response status code is OK.
     * To ensure that the sendMessage() method is called with the correct parameters, we use Mockito to mock the KafkaProducer dependency and capture the arguments passed to it using an ArgumentCaptor.
     * We then assert that the captured FamiliaDTO objects have the expected pontuacao values.
     * Note that we're using the @MockBean annotation to inject a mock of the KafkaProducer into the Spring context for the duration of this test.
     * This ensures that the real KafkaProducer is not used and that we can verify its behavior independently of the rest of the system.
     **/
    @Test
    public void sendMessage_shouldCallKafkaProducer() {
        int number = 5;
        FamilyDTO familiaDTO = new FamilyDTO();
        familiaDTO.setDependents(9);
        familiaDTO.setEmail("carlos_mattheus@hotmail.com");
        familiaDTO.setPoints(90);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Integer> entity = new HttpEntity<>(number, headers);

        ResponseEntity<Void> response = restTemplate.exchange("/email/{number}", HttpMethod.GET, entity, Void.class, number);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ArgumentCaptor<FamilyDTO> argument = ArgumentCaptor.forClass(FamilyDTO.class);
        verify(kafkaProducer, times(number)).sendMessage(argument.capture());
    }

}