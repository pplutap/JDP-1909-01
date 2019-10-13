package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return getSampleData().get(0);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return product;
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
    }

}
//public class TaskController {
//    @Autowired
//    private DbService service;
//    @Autowired
//    private TaskMapper taskMapper;
//
//    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
//    public List<TaskDto> getTasks(){
//        return taskMapper.mapToTaskDtoList(service.getAllTasks());
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "getTaskById")
//    public TaskDto getTasksById(@RequestParam(name = "id") Long taskId){
//        return taskMapper.mapToTaskDto(service.findTaskById(taskId));
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "getTask")
//    public TaskDto getTask(@RequestParam(name = "id") Long taskId){
//
//        return new TaskDto(taskId,"Test title", "Test content");
//    }
//
//    //Request param zmienia wartosc wymagana w linku, teraz jest ?id bez niego byloby ?taskId
//    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
//    public void deleteTask(@RequestParam(name = "id") Long taskId){
//        service.deleteTask(taskId);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
//    public TaskDto updateTask(@RequestBody TaskDto taskDto){
//        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)
//    public void createTask(@RequestBody TaskDto taskDto){
//        service.saveTask(taskMapper.mapToTask(taskDto));
//    }
//
//
//}