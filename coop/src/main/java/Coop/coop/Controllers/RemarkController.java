package Coop.coop.Controllers;


import Coop.coop.Entities.Remark;
import Coop.coop.Services.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http:localhost:3000")
@RestController
@RequestMapping("/api/Song/{id}")
public class RemarkController {
    final RemarkService remarkService;

    @Autowired
    public RemarkController(RemarkService remarkService){
        this.remarkService = remarkService;
    }

    @PostMapping("/post/Remark")
    public Remark addRemark(@RequestBody Remark remark){
        return remarkService.addRemark(remark);
    }

    @PutMapping("/put/Remark/{id}")
    public Boolean updateRemark(@RequestBody Remark remark){
        return remarkService.updateRemark(remark);
    }

    @GetMapping("/get/Remark/{id}")
    public List<Remark> getRemarks(@PathVariable long id){
        return remarkService.getRemarksForSong(id);
    }

    @DeleteMapping("/delete/Remark/{id}")
    public void deleteRemark(@PathVariable long id){
        remarkService.deleteRemark(id);
    }
}
