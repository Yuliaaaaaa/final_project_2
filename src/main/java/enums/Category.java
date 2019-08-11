package enums;

/**
 * @author Yuliia Shcherbakova ON 24.07.2019
 * @project publishing
 */
public enum Category {
    MEDICINE, SCIENCE, GARDENING, INDUSTRY, AGRICULTURE, BUILDING,
    ECONOMICS, FASHION, NEWSPAPER,
    MIND_BREAKER {
        @Override
        public String toString() {
            return "mind-breaker";
        }
    },
    FOR_MEN {
        @Override
        public String toString() {
            return "for men";
        }
    },
    FOR_WOMEN {
        @Override
        public String toString() {
            return "for women";
        }
    },
    FOR_CHILDREN {
        @Override
        public String toString() {
            return "for children";
        }
    }
}
