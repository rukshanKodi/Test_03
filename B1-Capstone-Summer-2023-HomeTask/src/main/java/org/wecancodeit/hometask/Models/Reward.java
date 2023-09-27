package org.wecancodeit.hometask.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/*
Basic Set up for the model for rewards
 */

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String rewardName;

    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "task")
    private Task task;

    @ManyToOne
    private Household household;

    public Reward() {
    }

    public Reward(String rewardName, Household household) {
        this.rewardName = rewardName;
        this.household = household;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rewardName == null) ? 0 : rewardName.hashCode());
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
        Reward other = (Reward) obj;
        if (rewardName == null) {
            if (other.rewardName != null)
                return false;
        } else if (!rewardName.equals(other.rewardName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reward [rewardName=" + rewardName + "]";
    }

}
