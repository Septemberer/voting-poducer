package itmo.apartmentService.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @ToString.Exclude
    @Column(name = "token")
    private String token;

    @Column(name = "karma_positive")
    private int karmaPositive = 0;

    @Column(name = "pro_user")
    private boolean professional = false;

    @Column(name = "karma_negative")
    private int karmaNegative = 0;

    @Column(name = "banned")
    private boolean banned = false;

    public void incPositive() {
        karmaPositive++;
        if (karmaPositive >= 5 && !professional) {
            professional = true;
        }
    }

    public void incNegative() {
        karmaNegative++;
        if (karmaNegative >= 5 && !banned) {
            banned = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(getUserName(), customer.getUserName()) &&
                Objects.equals(getPassword(), customer.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getPassword());
    }
}
