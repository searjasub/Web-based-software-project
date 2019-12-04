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
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username != null) {
            Profile profile = profileJpaRepository.findById(username).orElse(null);
            entry.setProfileOwner(profile);
            entryJpaRepository.save(entry);
            response.sendRedirect("/home");
        } else {
            session.setAttribute("error", "You must login.");
            response.sendRedirect("../");
        }


    }

    @RequestMapping(path = "/addEntry", method = RequestMethod.GET)
    public void addEntry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username != null) {
            request.getRequestDispatcher("../addDiary.jsp").forward(request, response);
        } else {
            session.setAttribute("error", "You must login.");
            response.sendRedirect("../");
        }
    }
}
