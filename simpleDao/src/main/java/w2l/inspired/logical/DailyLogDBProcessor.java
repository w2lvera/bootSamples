package w2l.inspired.logical;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.model.Customer;
import w2l.inspired.model.DailyLog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Component
public class DailyLogDBProcessor implements DailyLogProcessor {
    public static final Logger LOGGER = LogManager.getLogger(DailyLogDBProcessor.class);

    private final JdbcTemplate template;

    @Autowired
    public DailyLogDBProcessor(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<DailyLog> getLog() throws IOException {
        return template.query("select daily_logs.id, date,customer_id, name from daily_logs join customers on daily_logs.customer_id = customers.id",(rs, rowNum) -> {
            int id = rs.getInt("daily_logs.id");
            Date date = rs.getDate("date");
            LocalDate localDate = date.toInstant()
                    .atZone(ZoneId.systemDefault()) // Привязываем к системному часовому поясу
                    .toLocalDate();                 // Извлекаем только дату
            Customer c = new Customer(rs.getInt("customer_id"),rs.getString("name"));
            return new DailyLog(localDate,c);
        });
    }

    @Override
    public void reWriteLog(List<DailyLog> log) throws IOException {
        try {
            for (DailyLog s : log) {
                template.update("insert into daily_logs(customer_id) values (?)", s.getCustomer().getId());
            }
        } catch (DataIntegrityViolationException ex) {
            LOGGER.warn("Error saving daily statuses!" + ex.getMessage());
            throw new DataRelatedException(ex.getMessage());
        }

    }


}
