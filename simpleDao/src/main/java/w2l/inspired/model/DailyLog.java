package w2l.inspired.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="daily_logs")
public class DailyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private final LocalDate date;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private final Customer customer;

    protected DailyLog() {
        this.id = 0;
        this.date = LocalDate.now();
        this.customer = new Customer();
    }

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
