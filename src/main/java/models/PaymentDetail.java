package models;

/**
 * @author Yuliia Shcherbakova ON 18.07.2019
 * @project publishing
 */
public class PaymentDetail {
    private int detailsId;
    private int paymentId;
    private int subscriptionId;

    /**
     * @return
     */
    public int getDetailsId() {
        return detailsId;
    }

    /**
     * @param detailsId
     */
    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
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
    public int getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * @param subscriptionId
     */
    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
