package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pro150.intelligenius.diaryapp.model.Entry;
import pro150.intelligenius.diaryapp.model.Profile;
import pro150.intelligenius.diaryapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/entries")
public class EntryRestController {

    @Autowired
    private EntryJpaRepository entryJpaRepository;

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    @RequestMapping(path = "/addEntry", method = RequestMethod.POST)
    public void createEntry(@ModelAttribute("entry") Entry entry, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        User u = userJpaRepository.findById(principal.getName()).orElse(null);
//        assert u != null;
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("showEntries");
//        userJpaRepository.save(u);
//        //send to jpa that allows the user to create an entry
//        entryJpaRepository.save(entry);
//        return mav;

        String username = (String) request.getSession().getAttribute("username");
        Profile profile = profileJpaRepository.findById(username).orElse(null);
        entry.setProfileOwner(profile);
        entryJpaRepository.save(entry);

        response.sendRedirect("/home");

    }

//    @RequestMapping(path = "", method = RequestMethod.GET)
//    public ModelAndView getAllEntries(Principal principal) {
//        User u = userJpaRepository.findById(principal.getName()).orElse(null);
//        ModelAndView modelAndView = new ModelAndView();
//        try{
////            List<Entry> allEntries = u.getEntries();
////            modelAndView.addObject("list", allEntries);
//            modelAndView.setViewName("showEntries");
//        }
//        catch(NullPointerException npe) {
//            modelAndView.setViewName("error");
//        }
//        return modelAndView;
//    }

    @RequestMapping(path = "/addEntry", method = RequestMethod.GET)
    public void addEntry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("addDiary");
//        return mav;
        request.getRequestDispatcher("../addDiary.jsp").forward(request, response);
    }
}
