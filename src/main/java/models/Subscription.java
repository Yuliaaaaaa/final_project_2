package models;

import java.sql.Timestamp;

/**
 * @author Yuliia Shcherbakova ON 18.07.2019
 * @project publishing
 */
public class Subscription {
    private int subscriptionId;
    private int userId;
    private int editionId;
    private int issuesQuantity;
    private Timestamp orderDate;
    private boolean isPaid;

    /**
     * @param userId
     * @param editionId
     * @param issuesQuantity
     * @param isPaid
     */
    public Subscription(int userId, int editionId, int issuesQuantity, boolean isPaid) {
        this.userId = userId;
        this.editionId = editionId;
        this.issuesQuantity = issuesQuantity;
        this.isPaid = isPaid;
    }

    /**
     *
     */
    public Subscription() {
    }

    /**
     * @return
     */
    public int getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * @param subscriptionId
     */
    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    /**
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return
     */
    public int getEditionId() {
        return editionId;
    }

    /**
     * @param editionId
     */
    public void setEditionId(int editionId) {
        this.editionId = editionId;
    }

    /**
     * @return
     */
    public int getIssuesQuantity() {
        return issuesQuantity;
    }

    /**
     * @param issuesQuantity
     */
    public void setIssuesQuantity(int issuesQuantity) {
        this.issuesQuantity = issuesQuantity;
    }

    /**
     * @return
     */
    public Timestamp getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate
     */
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * @param paid
     */
    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }
}
