package ru.ifmo.fitp.labtestermaster.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ifmo.fitp.labtestermaster.domain.user.Participant;
import ru.ifmo.fitp.labtestermaster.repository.ParticipantRepository;

@Controller
public class ParticipantController {
    private static final Logger LOG = Logger.getLogger(ParticipantController.class);

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantController(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @GetMapping(value = "/login")
    public ResponseEntity login(@RequestParam String login,
                                @RequestParam String password) {
        LOG.info("Login request for participant with name " + login);

        Participant participant = participantRepository.findParticipantByLogin(login);

        if (participant == null) {
            LOG.warn(String.format("Participant with name %s not found", login));
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (password.equals(participant.getPassword())) {
            LOG.info("Successful login for participant with name " + login);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            LOG.warn("Wrong password for participant with name " + login);
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
