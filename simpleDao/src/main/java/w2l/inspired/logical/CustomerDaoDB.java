package w2l.inspired.logical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.model.Customer;

import java.sql.Date;
import java.util.List;

@Component
@Primary
public class CustomerDaoDB  {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerDaoDB(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getCustomers() {
        return customerDao.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerDao.findById(id).orElseThrow( ()->new DataRelatedException("Error in ids"));
    }

}
