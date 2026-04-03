package w2l.inspired.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import w2l.inspired.model.DailyLog;

import java.io.IOException;
import java.util.List;

@Repository
public interface DailyLogDao extends ListCrudRepository<DailyLog,Integer> {
}
