package models;

import java.sql.Timestamp;

/**
 * @author Yuliia Shcherbakova ON 18.07.2019
 * @project publishing
 */
public class Payment {
    private int paymentId;
    private int userId;
    private double paymentSum;
    private Timestamp paymentDate;

    /**
     * @param userId
     * @param paymentSum
     * @param paymentDate
     */
    public Payment(int userId, double paymentSum, Timestamp paymentDate) {
        this.userId = userId;
        this.paymentSum = paymentSum;
        this.paymentDate = paymentDate;
    }

    /**
     *
     */
    public Payment() {
    }

    /**
     * @return
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * @param paymentId
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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
    public double getPaymentSum() {
        return paymentSum;
    }

    /**
     * @param paymentSum
     */
    public void setPaymentSum(double paymentSum) {
        this.paymentSum = paymentSum;
    }

    /**
     * @return
     */
    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate
     */
    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }
}
