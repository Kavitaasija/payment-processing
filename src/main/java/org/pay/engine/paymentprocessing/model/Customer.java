package org.pay.engine.paymentprocessing.model;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
  private String id;
  private String name;
  private String email;
  private String phone;

  public Customer() {
  }

  public Customer(String id, String name, String email, String phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id) &&
        Objects.equals(name, customer.name) &&
        Objects.equals(email, customer.email) &&
        Objects.equals(phone, customer.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, phone);
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        '}';
  }
}