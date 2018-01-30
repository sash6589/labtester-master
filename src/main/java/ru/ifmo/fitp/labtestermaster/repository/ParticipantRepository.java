package ru.ifmo.fitp.labtestermaster.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ifmo.fitp.labtestermaster.domain.user.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    Participant findParticipantByLogin(String login);
}
