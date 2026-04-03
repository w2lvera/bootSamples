package w2l.inspired.model;

import jakarta.persistence.*;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    private final String name;

    protected Customer() {
        this.id = 0;
        this.name = "John Doe";
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {        return id;    }
    public String getName() {  return name;    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + '}';
    }
}

