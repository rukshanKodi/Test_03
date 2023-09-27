package org.wecancodeit.hometask.Models;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class HouseholdMember {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private int cashAmount;
    
    
    @ManyToOne
    private Household household;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Task> tasks;

    public HouseholdMember() {
    }
    

    public HouseholdMember(String name, Household household, int cashAmount) {
        this.name = name;
        this.household = household;
        this.cashAmount = cashAmount;
    } 


    public long getId() {
        return id;
    }

    public long getHouseholdMemberId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 public void addHousehold(Household household) {
        this.household = household;
    }
   
    public Household getHousehold() {
        return household;
    }

    public int getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(int cashAmount) {
        this.cashAmount = cashAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HouseholdMember other = (HouseholdMember) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HouseholdMember [name=" + name + "]";
    }
}
