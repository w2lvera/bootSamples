package w2l.inspired.model;

import java.time.LocalDate;

public class DailyLog {
    LocalDate date;
    Customer customer;

    public DailyLog(LocalDate date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

    public DailyLog(Customer customer) {
        this.date = LocalDate.now();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }


    public LocalDate getDate() {
        return date;
    }

}
