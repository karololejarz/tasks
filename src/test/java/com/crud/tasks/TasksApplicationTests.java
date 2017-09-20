package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void findTaskByIdAndDeleteTaskById() {
		DbService service = new DbService();
		Task task99 = new Task(Long.valueOf(99),"test99","test99");
		service.saveTask(task99);

		Assert.assertEquals("test99",service.getTask(task99.getId()).get().getTitle());

		service.deleteTask(task99.getId());

		Assert.assertEquals(false,service.getAllTasks().contains(task99));
	}

}
