package com.crud.tasks.repository;

import com.crud.tasks.domain.TaskDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskDto, Long> {
    @Override
    List<TaskDto> findAll();

    @Override
    TaskDto save(TaskDto taskDto);
}
