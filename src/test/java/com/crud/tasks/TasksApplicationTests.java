package com.crud.tasks;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksApplicationTests {

	@Autowired
	TaskController controller;

	@Test
	public void contextLoads() {
	}

	/*
	@Test
	public void findTaskByIdAndDeleteTaskById() throws TaskNotFoundException {

		Task task1 = new Task(Long.valueOf(1),"test1","test1");
		Task task2 = new Task(Long.valueOf(2),"test2","test2");
		TaskDto dto1 = new TaskDto(task1.getId(),task1.getTitle(),task1.getContent());
		TaskDto dto2 = new TaskDto(task2.getId(),task2.getTitle(),task2.getContent());

		controller.createTask(dto1);
		controller.createTask(dto2);

		Assert.assertEquals(true,controller.getTasks().contains(dto1));
		Assert.assertEquals(true,controller.getTasks().contains(dto2));
		Assert.assertEquals("test1",controller.getTask(task1.getId()).getTitle());
		Assert.assertEquals("test2",controller.getTask(task2.getId()).getTitle());

		controller.deleteTask(task1.getId());
		controller.deleteTask(task2.getId());

		Assert.assertEquals(false,controller.getTasks().contains(task1));
		Assert.assertEquals(false,controller.getTasks().contains(task2));
	}
	*/

}
