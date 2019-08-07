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
    private Timestamp startDate;
    private Timestamp expireDate;
    private boolean isPaid;
    private Edition edition;

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
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    /**
     * @return
     */
    public Timestamp getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate
     */
    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = expireDate;
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

    /**
     * @return
     */
    public Edition getEdition() {
        return edition;
    }

    /**
     * @param edition
     */
    public void setEdition(Edition edition) {
        this.edition = edition;
    }
}
