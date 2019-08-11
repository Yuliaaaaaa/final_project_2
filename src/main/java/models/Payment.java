package models;

import dtos.SecureUser;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 18.07.2019
 * @project publishing
 */
public class Payment {
    private int paymentId;
    private int userId;
    private double paymentSum;
    private Timestamp paymentDate;
    private List<PaymentDetail> paymentDetails;
    private SecureUser user;

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


    /**
     * @return
     */
    public List<PaymentDetail> getPaymentDetails() {
        return paymentDetails;
    }

    /**
     * @param paymentDetails
     */
    public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    /**
     * @return
     */
    public SecureUser getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(SecureUser user) {
        this.user = user;
    }

}
