package pro150.intelligenius.diaryapp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class DiaryRestController {

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public void doLogin(){

    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public void loginScreen(){

    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public void doRegistration(){

    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteEntryById(@PathVariable long id){

    }

    @RequestMapping(path = "/collection", method = RequestMethod.GET)
    public void getAllEntries(){

    }
}
