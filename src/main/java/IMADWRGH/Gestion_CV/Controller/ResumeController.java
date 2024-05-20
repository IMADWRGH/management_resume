package IMADWRGH.Gestion_CV.Controller;

import IMADWRGH.Gestion_CV.Exception.InvalidRequestException;
import IMADWRGH.Gestion_CV.Model.Resume;
import IMADWRGH.Gestion_CV.Services.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/resume")
public class ResumeController {
    private final ResumeService resumeService;


    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Resume> create(@RequestBody Resume resume) {
//        if (resume.equals(resume)){
//            throw new InvalidRequestException("Invalid Resume request");
//        }
        resumeService.create(resume);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Resume> getResume(@PathVariable(name = "id") int id) {
        System.out.println("test ");
      var optionalResume= resumeService.getResume(id);
        if (optionalResume.isPresent()) {
            return new ResponseEntity<>(optionalResume.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteResume(@PathVariable(name = "id") Integer id) {
        resumeService.delete(id);
        return new ResponseEntity<>("resume id: "+ id + " deleted successfully", HttpStatus.OK);
    }




}
