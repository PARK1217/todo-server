package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoModel;
import org.example.model.TodoRequest;
import org.example.service.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    //아이템을 추가하는 메서드

    public TodoModel add(TodoRequest request) {
        TodoModel todoModel = new TodoModel();
        todoModel.setTitle(request.getTitle());
        todoModel.setOrder(request.getOrder());
        todoModel.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoModel);
    }

    //아이템을 조회하는 메서드
    public TodoModel searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //전체를 조회하는 메서드
    public List<TodoModel> searchAll() {
        return this.todoRepository.findAll();
    }

    //수정된 결과 엔티티
    public TodoModel updateById(Long id, TodoRequest request) {
        TodoModel todoModel = this.searchById(id);
        if(request.getTitle() != null) {
            todoModel.setTitle(request.getTitle());
        }
        if(request.getOrder() != null) {
            todoModel.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null) {
            todoModel.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoModel);
    }

    //삭제
    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);

    }

    //전체 삭제
    public void deleteAll() {
        this.todoRepository.deleteAll();
    }

}
