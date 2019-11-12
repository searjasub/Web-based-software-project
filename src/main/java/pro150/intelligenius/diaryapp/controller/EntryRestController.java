package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pro150.intelligenius.diaryapp.model.Entry;

import java.util.List;

@RestController
@RequestMapping(path = "/entries")
public class EntryRestController {

    @Autowired
    private EntryJpaRepository entryJpaRepository;

//    @Autowired
//    private UserJpaRepository userJpaRepository;

    //TODO, This line may need changing, not sure of the path
    @RequestMapping(path = "", method = RequestMethod.POST)
    public void createEntry(@RequestBody Entry newEntry) {
        entryJpaRepository.save(newEntry);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Entry> getAllEntries() {
        return entryJpaRepository.findAll();
    }


}
