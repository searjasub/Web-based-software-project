package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Entry;
import pro150.intelligenius.diaryapp.model.Profile;
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
    @RequestMapping(path = "/create-entry", method = RequestMethod.POST)
    public void createEntry(@ModelAttribute("entry") Entry entry, Principal principal) {
        User u = userJpaRepository.findById(principal.getName()).orElse(null);
        assert u != null;
        u.addToEntryList(entry);
        userJpaRepository.save(u);
        //send to jpa that allows the user to create an entry
        entryJpaRepository.save(entry);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView getAllEntries(Principal principal) {
        User u = userJpaRepository.findById(principal.getName()).orElse(null);
        List<Entry> allEntries = u.getEntries();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", allEntries);
        modelAndView.setViewName("showDiary");
        return modelAndView;
    }


}
