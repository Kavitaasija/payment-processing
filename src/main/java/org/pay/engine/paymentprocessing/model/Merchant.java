package org.pay.engine.paymentprocessing.model;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a merchant in the payment processing system.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
  private String id;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Merchant merchant = (Merchant) o;
    return Objects.equals(id, merchant.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Merchant{" +
        "id='" + id + '\'' +
        '}';
  }
}