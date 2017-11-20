package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService dbService;
    @MockBean
    private TaskMapper taskMapper;

    public List<TaskDto> createTaskDtosList() {
        List<TaskDto> taskDtos = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(1L,"Title1","Content1");
        TaskDto taskDto2 = new TaskDto(2L,"Title2","Content2");
        TaskDto taskDto3 = new TaskDto(3L,"Title3","Content3");
        taskDtos.add(taskDto1);
        taskDtos.add(taskDto2);
        taskDtos.add(taskDto3);
        return taskDtos;
    }

    @Test
    public void shouldGetTasks() throws Exception {
        List<TaskDto> taskDtos = createTaskDtosList();
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtos);
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetTask() throws Exception {
        Task task = new Task(1L, "Title1", "Content1");
        TaskDto taskDto = createTaskDtosList().get(0);
        when(dbService.getTask(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        mockMvc.perform(get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Title1")))
                .andExpect(jsonPath("$.content", is("Content1")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        Task task = new Task(1L, "Title1", "Content1");
        TaskDto taskDto = createTaskDtosList().get(0);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Title1")))
                .andExpect(jsonPath("$.content", is("Content1")));
    }
}
