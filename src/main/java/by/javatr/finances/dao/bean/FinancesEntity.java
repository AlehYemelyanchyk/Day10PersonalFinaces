package by.javatr.finances.dao.bean;

import java.io.Serializable;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public class FinancesEntity implements Serializable {
    private String name;
    private String currency;

    public FinancesEntity() {
    }

    public FinancesEntity(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        FinancesEntity newFinancesEntity = (FinancesEntity) obj;

        if (this.getName() != null ? !this.getName().equals(newFinancesEntity.getName())
                : newFinancesEntity.getName() != null) {
            return false;
        }
        return this.getCurrency() != null ? this.getCurrency().equals(newFinancesEntity.getCurrency())
                : newFinancesEntity.getCurrency() == null;
    }

    @Override
    public int hashCode() {
        return 31 * ((this.getName() == null) ? 1 : this.getName().hashCode()) + this.getCurrency().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + '@'
                + "name: " + this.getName()
                + ", currency: " + this.getCurrency();
    }
}
