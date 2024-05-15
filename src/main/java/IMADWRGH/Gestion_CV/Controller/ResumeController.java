package IMADWRGH.Gestion_CV.Controller;

import IMADWRGH.Gestion_CV.Model.Resume;
import IMADWRGH.Gestion_CV.Services.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/resume")
public class ResumeController {
    private final ResumeService resumeService;


    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Resume> create(@RequestBody Resume resume) {
        resumeService.create(resume);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/get/{id}")
    public  ResponseEntity<Resume> getResume(@PathVariable int id){
        return null;
    }



}
