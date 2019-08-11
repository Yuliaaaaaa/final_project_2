package factories;

import enums.Category;

/**
 * @author Yuliia Shcherbakova ON 24.07.2019
 * @project publishing
 */
public class CategoryFactory {

    private CategoryFactory() {
    }

    public static Category getCategory(String category) {
        switch (category) {
            case "medicine":
                return Category.MEDICINE;
            case "science":
                return Category.SCIENCE;
            case "gardening":
                return Category.GARDENING;
            case "industry":
                return Category.INDUSTRY;
            case "agriculture":
                return Category.AGRICULTURE;
            case "building":
                return Category.BUILDING;
            case "economics":
                return Category.ECONOMICS;
            case "fashion":
                return Category.FASHION;
            case "newspaper":
                return Category.NEWSPAPER;
            case "mind-breaker":
                return Category.MIND_BREAKER;
            case "for men":
                return Category.FOR_MEN;
            case "for women":
                return Category.FOR_WOMEN;
            case "for children":
                return Category.FOR_CHILDREN;
        }
        return null;
    }
}
