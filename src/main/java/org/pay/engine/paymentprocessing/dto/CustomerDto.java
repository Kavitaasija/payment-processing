package org.pay.engine.paymentprocessing.dto;

import java.util.Objects;

/**
 * DTO for customer information
 */
public class CustomerDto {
  private String id;
  private String name;
  private String email;
  private String phone;

  public CustomerDto() {
  }

  public CustomerDto(String id, String name, String email, String phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
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
    CustomerDto that = (CustomerDto) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(name, that.name) &&
        Objects.equals(email, that.email) &&
        Objects.equals(phone, that.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, phone);
  }

  @Override
  public String toString() {
    return "CustomerDto{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        '}';
  }
}