package com.qa.portal.user.services;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.util.mapper.CohortMapper;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetTrainerCohortsOperation {

    private QaTrainerRepository trainerRepository;

    private CohortMapper cohortMapper;

    private Comparator<QaCohortDto> cohortComparator = (c1, c2) -> c1.getStartDate().isBefore(c2.getStartDate()) ? 1 : -1;

    public GetTrainerCohortsOperation(QaTrainerRepository trainerRepository,
                                      CohortMapper cohortMapper) {
        this.trainerRepository = trainerRepository;
        this.cohortMapper = cohortMapper;
    }

    public List<QaCohortDto> getCohortsForTrainer(String userName) {
        TrainerEntity trainer = trainerRepository.findByUserName(userName)
                .orElseThrow(() -> new QaResourceNotFoundException("Trainer not found"));
        return trainer.getCohorts()
                .stream()
                .map(this.cohortMapper::mapToQaCohortDto)
                .sorted(cohortComparator)
                .collect(Collectors.toList());
    }
}
