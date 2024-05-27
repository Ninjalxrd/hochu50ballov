import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorPriceTest {

    @Test
    void compare_LowerPrice_ReturnsNegative() {
        ComparatorPrice comparator = new ComparatorPrice();
        Car car1 = new Car("Toyota", "Camry", 2020, 20000);
        Car car2 = new Car("Honda", "Accord", 2021, 25000);
        int result = comparator.compare(car1, car2);
        assertTrue(result < 0);
    }

    @Test
    void compare_HigherPrice_ReturnsPositive() {
        ComparatorPrice comparator = new ComparatorPrice();
        Car car1 = new Car("BMW", "X5", 2022, 60000);
        Car car2 = new Car("Audi", "Q5", 2021, 50000);
        int result = comparator.compare(car1, car2);
        assertTrue(result > 0);
    }

    @Test
    void compare_EqualPrice_ReturnsZero() {
        ComparatorPrice comparator = new ComparatorPrice();
        Car car1 = new Car("Ford", "Focus", 2020, 25000);
        Car car2 = new Car("Hyundai", "Elantra", 2020, 25000);
        int result = comparator.compare(car1, car2);
        assertEquals(0, result);
    }
}