package dio.projeto_dio_spring.controller;

import dio.projeto_dio_spring.model.User;
import dio.projeto_dio_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController{
    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> getUsers(){
        return repository.listAll();
     }
     @GetMapping("/users/{username}")
     public User getOne(@PathVariable("username") String username){
        return repository.finByName(username);
     }

     @DeleteMapping("/users/{id}")
     public void delete(@PathVariable("id") Integer id){
        repository.remove(id);
    }

    @PostMapping
    public void postUser(@RequestBody User user){
        repository.save(user);
    }
    @PutMapping
    public void putUser(@RequestBody User user){
        repository.update(user);
    }
}
