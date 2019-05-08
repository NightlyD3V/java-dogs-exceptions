package com.lambdaschool.projectrestdogs.controller;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.exception.ResourceNotFoundException;
import com.lambdaschool.projectrestdogs.model.Dog;
import com.lambdaschool.projectrestdogs.services.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
@RequestMapping("/dogs")
public class DogController
{
    private static final Logger logger = LoggerFactory.getLogger(Dog.class);
    // localhost:8080/dogs/dogs
    @GetMapping(value = "/dogs")
    public ResponseEntity<?> getAllDogs()
    {
        logger.info("Got all dogs");
        MessageSender.sendMessage();
        return new ResponseEntity<>(ProjectrestdogsApplication.ourDogList.dogList, HttpStatus.OK);
    }

    // localhost:8080/dogs/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDogDetail(@PathVariable long id)
    {
        logger.info("got dog with this the id of: " + id);
        Dog rtnDog;
        if ((ProjectrestdogsApplication.ourDogList.findDog(e -> (e.getId()) == id)) == null)
        {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        } else
        {
            rtnDog = ProjectrestdogsApplication.ourDogList.findDog(e -> (e.getId() == id));
        }
        return new ResponseEntity<>(rtnDog, HttpStatus.OK);
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}")
    public ResponseEntity<?> getDogBreeds (@PathVariable String breed)
    {
        logger.info("got dog with this breed: " + breed);
        ArrayList<Dog> rtnDogs = ProjectrestdogsApplication.ourDogList.
                findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()));
        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);
    }

    @GetMapping(value ="/dogtable")
    public ModelAndView getRender()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("dogs");
        mav.addObject("DogList", ProjectrestdogsApplication.ourDogList.dogList);
        return mav;
    }
}