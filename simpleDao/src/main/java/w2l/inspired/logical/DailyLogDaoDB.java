package w2l.inspired.logical;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.dao.DailyLogDao;
import w2l.inspired.model.Customer;
import w2l.inspired.model.DailyLog;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Component
public class DailyLogDaoDB {
    public static final Logger LOGGER = LogManager.getLogger(DailyLogDaoDB.class);

    private final DailyLogDao dao;

    @Autowired
    public DailyLogDaoDB(DailyLogDao dao) {
        this.dao = dao;
    }

    public List<DailyLog> getLog() throws IOException {
       return dao.findAll();
    }

    public void reWriteLog(List<DailyLog> log) throws IOException {
        try {
            dao.saveAll(log);
        } catch (DataIntegrityViolationException ex) {
            LOGGER.warn("Error saving daily statuses!" + ex.getMessage());
            throw new DataRelatedException(ex.getMessage());
        }

    }


}
