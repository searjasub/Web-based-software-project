package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Entry;
import pro150.intelligenius.diaryapp.model.User;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/entries")
public class EntryRestController {

    @Autowired
    private EntryJpaRepository entryJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @RequestMapping(path = "/addEntry", method = RequestMethod.POST)
    public ModelAndView createEntry(@ModelAttribute("entry") Entry entry, Principal principal) {
        User u = userJpaRepository.findById(principal.getName()).orElse(null);
        assert u != null;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("showEntries");
        u.addToEntryList(entry);
        userJpaRepository.save(u);
        //send to jpa that allows the user to create an entry
        entryJpaRepository.save(entry);
        return mav;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView getAllEntries(Principal principal) {
        User u = userJpaRepository.findById(principal.getName()).orElse(null);
        ModelAndView modelAndView = new ModelAndView();
        try{
            List<Entry> allEntries = u.getEntries();
            modelAndView.addObject("list", allEntries);
            modelAndView.setViewName("showEntries");
        }
        catch(NullPointerException npe) {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/addEntry", method = RequestMethod.GET)
    public ModelAndView addEntry() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addDiary");
        return mav;
    }
}
