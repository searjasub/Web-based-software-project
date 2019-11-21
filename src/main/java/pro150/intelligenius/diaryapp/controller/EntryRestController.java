package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pro150.intelligenius.diaryapp.model.Entry;
import pro150.intelligenius.diaryapp.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/entries")
public class EntryRestController {

    @Autowired
    private EntryJpaRepository entryJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    //TODO, This line may need changing, not sure of the path
    @RequestMapping(path = "", method = RequestMethod.POST)
    public void createEntry(@RequestBody Entry newEntry,Principal principal) {
        User u = userJpaRepository.findById(principal.getName()).orElse(null);
        //send to jpa that allows the user to create an entry
        entryJpaRepository.save(newEntry);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Entry> getAllEntries(Principal principal) {
        User u = userJpaRepository.findById(principal.getName()).orElse(null);
        List<Entry> allEntries = u.getEntries();
        return entryJpaRepository.findAll();
    }


}
