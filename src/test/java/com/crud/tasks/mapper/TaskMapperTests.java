package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskMapperTests {
    TaskMapper mapper = new TaskMapper();
    Task task1 = new Task(1L,"title","content");
    Task task2 = new Task(2L,"t2","c2");
    TaskDto dto1 = new TaskDto(1L,"title","content");

    //Improving coverage
    @Test
    public void shouldMapToTask() {
        Task expected = mapper.mapToTask(dto1);
        Assert.assertEquals(expected.getId(),dto1.getId());
        Assert.assertEquals(expected.getTitle(),dto1.getTitle());
        Assert.assertEquals(expected.getContent(),dto1.getContent());
    }
    //Improving coverage
    @Test
    public void shouldMapToTaskDto() {
        TaskDto expected = mapper.mapToTaskDto(task1);
        Assert.assertEquals(expected.getId(),task1.getId());
        Assert.assertEquals(expected.getTitle(),task1.getTitle());
        Assert.assertEquals(expected.getContent(),task1.getContent());
    }
    //Improving coverage
    @Test
    public void shouldMapToTaskDtoList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        List<TaskDto> expected = mapper.mapToTaskDtoList(tasks);
        Assert.assertEquals(2,expected.size());
    }
}
