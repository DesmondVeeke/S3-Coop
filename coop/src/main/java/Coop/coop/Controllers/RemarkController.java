package Coop.coop.Controllers;


import Coop.coop.Entities.Remark;
import Coop.coop.Services.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http:localhost:3000")
@RestController
@RequestMapping("/api/Remarks")
public class RemarkController {
    final RemarkService remarkService;

    @Autowired
    public RemarkController(RemarkService remarkService){
        this.remarkService = remarkService;
    }

    @PostMapping("/post")
    public Remark addRemark(@RequestBody Remark remark){
        return remarkService.addRemark(remark);
    }

    @PutMapping("/put/{id}")
    public Boolean updateRemark(@RequestBody Remark remark){
        return remarkService.updateRemark(remark);
    }

    @GetMapping("/get/{id}")
    public List<Remark> getRemarks(@PathVariable long id){
        return remarkService.getRemarksForSong(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRemark(@PathVariable long id){
        remarkService.deleteRemark(id);
    }
}
