package com.qa.portal.reflection.service;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.util.mapper.ReflectionMapper;

@Service
public class ReflectionService {

	private ReflectionRepository reflectionRepo;

	private ReflectionMapper mapper;

	private QaTrainerRepository trainerRepo;
	
	private QaTraineeRepository traineeRepo;
	
	private GetSelfReflectionsForUserOperation getSelfReflectionsForUserOperation;

	public ReflectionService(ReflectionRepository repo, ReflectionMapper mapper, QaTrainerRepository trainerRepo, QaTraineeRepository traineeRepo, GetSelfReflectionsForUserOperation getSelfReflectionsForUserOperation) {
		super();
		this.reflectionRepo = repo;
		this.mapper = mapper;
		this.trainerRepo = trainerRepo;
		this.getSelfReflectionsForUserOperation = getSelfReflectionsForUserOperation;
		this.traineeRepo = traineeRepo;
	}

	@Transactional
	public Set<ReflectionDto> getSelfReflectionsForTrainee(String traineeId) {
		return this.getSelfReflectionsForUserOperation.getSelfReflectionsForUser(traineeId, this.reflectionRepo, this.traineeRepo);
	}

	@Transactional
	public ReflectionDto getSelfReflection(Integer id) {
		ReflectionEntity reflection = this.reflectionRepo.findById(id)
				.orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
		return this.mapper.mapToReflectionDto(reflection);
	}

	@Transactional
	public ReflectionDto getSelfReflection(Integer userId, LocalDate date) {
		TrainerEntity trainer = this.trainerRepo.findById(userId)
				.orElseThrow(() -> new QaResourceNotFoundException("Trainer does not exist"));
		ReflectionEntity reflection = this.reflectionRepo.findByReviewerAndFormDate(trainer, date)
				.orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
		return this.mapper.mapToReflectionDto(reflection);
	}

	@Transactional
	public ReflectionDto createSelfReflection(ReflectionDto reflection, String userName) {
		ReflectionDto output = this.mapper.mapToReflectionDto(this.reflectionRepo.save(this.mapper.mapToReflectionEntity(reflection)));
		return output;
	}

	@Transactional
	public ReflectionDto updateSelfReflection(ReflectionDto reflection) {
		ReflectionEntity reflectionToUpdate = this.reflectionRepo.findById(reflection.getId())
				.orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
		ReflectionEntity reflectionToUpdateFrom = this.mapper.mapToReflectionEntity(reflection);
		reflectionToUpdate.setFormDate(reflectionToUpdateFrom.getFormDate());
		reflectionToUpdate.setResponder(reflectionToUpdateFrom.getResponder());
		reflectionToUpdate.setReviewer(reflectionToUpdateFrom.getReviewer());
		return this.mapper.mapToReflectionDto(this.reflectionRepo.save(reflectionToUpdate));
	}

}
