package by.academy.it.rest.controller;

import by.academy.it.entity.Person;
import by.academy.it.services.IPersonService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private static final String MAIN = "persons/main";
    @Autowired
    private IPersonService personService;

    @GetMapping(value = "/page")
    public String mainPage(ModelMap model) {
        fillModel(model);
        return MAIN;
    }

    @PostMapping(value = "/add")
    public String addPerson(ModelMap model,
                            @Valid Person person, BindingResult br/*,
                            @RequestParam(value = "t", defaultValue = "T") String text,
                            @PathVariable(value = "id") Long personId*/) {
        if (!br.hasErrors()) {
            person = personService.create(person);
            if (person != null) {
                model.put("person", person);
            }
        }
        model.put("persons", personService.getPersons());
        return MAIN;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePerson(ModelMap model, Person person) {
        if (person != null) {
            personService.delete(person);
            model.put("message", "Person: " + person.getName() + " was deleted");
        }
        fillModel(model);

        return MAIN;
    }

    private void fillModel(ModelMap model) {
        List<Person> list = personService.getPersons();
        model.put("persons", list);
        Person person = new Person();
        if (list.size() > 1) {
            person = list.get(0);
        }
        model.put("person", person);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String processFile(@RequestParam(value = "avatar", required = false) MultipartFile image,
                              ModelMap map) {
        processImage(image, "1_");
        fillModel(map);
        return MAIN;
    }

    @RequestMapping(value = "/download/image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam(value = "name") String fileName) {
        File file = new File("/resources/" + fileName);
        if (file.exists()) {
            byte[] content = new byte[0];
            try {
                content = FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                //Exception handling
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(content.length);
            return new ResponseEntity<>(content, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private void processImage(MultipartFile image, String fileName) {
        try {
            if (image != null && !image.isEmpty()) {
                validateImage(image);
                saveImage(fileName, image);
            }
        } catch (IOException e) {
            //Error handling
        }
    }

    private void validateImage(MultipartFile image) throws IOException {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new IOException("Only JPG images accepted");
        }
    }

    private void saveImage(String filename, MultipartFile image) throws IOException {
        File file = new File("/resources/" + filename + ".jpg");
        FileUtils.writeByteArrayToFile(file, image.getBytes());
    }
}



