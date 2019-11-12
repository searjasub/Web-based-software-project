package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pro150.intelligenius.diaryapp.model.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @RequestMapping(name = "", method = RequestMethod.POST)
    public void createUser(@RequestBody User newUser){
        userJpaRepository.save(newUser);
    }

    @RequestMapping(name = "", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userJpaRepository.findAll();
    }

}
