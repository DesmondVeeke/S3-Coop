package Coop.coop.Controllers;


import Coop.coop.Entities.Remark;
import Coop.coop.Services.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http:localhost:3000")
@RestController
@RequestMapping("/api/remarks")
public class RemarkController {
    final RemarkService remarkService;

    @Autowired
    public RemarkController(RemarkService remarkService){
        this.remarkService = remarkService;
    }

    @PostMapping()
    public ResponseEntity<String> addRemark(@RequestBody Remark remark)
    {
        try
        {
            this.remarkService.addRemark(remark);
            return new ResponseEntity<>("Remark is saved at: " + remark.getTimeInTrack() + " seconds.", HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRemark(@RequestBody Remark remark)
    {
        try
        {
            remarkService.updateRemark(remark);
            return new ResponseEntity<>("The remark was succesfully updated.", HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/forsong/{id}")
    public List<Remark> getRemarksForSong(@PathVariable long id)
    {
        return remarkService.getRemarksForSong(id);
    }

    @GetMapping("/{id}")
    public Optional<Remark> getRemark(@PathVariable long id)
    {
        return remarkService.getRemark(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRemark(@PathVariable long id)
    {
        try
        {
            remarkService.deleteRemark(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
