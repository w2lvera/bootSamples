package w2l.inspired.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import w2l.inspired.logical.DailyLogDaoDB;
import w2l.inspired.model.DailyLog;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class DailyLogController {

    private final DailyLogDaoDB dailyLogDao;

    @Autowired
    public DailyLogController(DailyLogDaoDB dailyLogDao) {
        this.dailyLogDao = dailyLogDao;
    }

    @GetMapping ("/dailyLog")
    public ResponseEntity<List<DailyLog>> getTodayLog() throws IOException {
        List<DailyLog> logs = dailyLogDao.getLog();
        return ResponseEntity.ok(logs);
    }
}
