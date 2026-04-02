package w2l.inspired.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import w2l.inspired.model.Customer;

import java.sql.Date;
import java.util.List;

@Component
@Primary
public class CustomerDaoDB implements CustomerDao{

    private final JdbcTemplate template;

    public CustomerDaoDB(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Customer> getCustomers() {
        return template.query("select id, name from customers",(rs, rowNum) -> {
            int id = (rs.getInt("id"));
            String name = rs.getString("name");
            return new Customer(id,name);
        });
    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }

    @Override
    public Customer getCustomerByName(String name) {
        return null;
    }

    @Override
    public void insertCustomer(Customer t) {

    }

    @Override
    public void deleteCustomer(Customer t) {

    }
}
