package models;

import enums.Category;
import enums.Periodicity;

/**
 * @author Yuliia Shcherbakova ON 18.07.2019
 */
public class Edition {
    private int editionId;
    private String editionTitle;
    private Category category;
    private Periodicity periodicity;
    private String description;
    private double price;
    private boolean isDeleted;


    /**
     * @param editionTitle
     * @param category
     * @param periodicity
     * @param description
     * @param price
     */
    public Edition(String editionTitle, Category category, Periodicity periodicity, String description, double price) {
        this.editionTitle = editionTitle;
        this.category = category;
        this.periodicity = periodicity;
        this.description = description;
        this.price = price;
        isDeleted = false;
    }

    /**
     *
     */
    public Edition() {
        isDeleted = false;
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
    public String getEditionTitle() {
        return editionTitle;
    }

    /**
     * @param editionTitle
     */
    public void setEditionTitle(String editionTitle) {
        this.editionTitle = editionTitle;
    }

    /**
     * @return
     */
    public String getCategory() {
        return category.toString().toLowerCase();
    }

    /**
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return
     */
    public String getPeriodicity() {
        return periodicity.toString().toLowerCase();
    }

    /**
     * @param periodicity
     */
    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
