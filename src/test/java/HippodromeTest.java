import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @Test
    void nullHorsesException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void nullHorsesMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    void emptyHorsesException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void emptyHorsesMessage() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    void getHorses() {

        List<Horse> horses = new ArrayList<>();

        for (int i = 1; i < 30; i++) {
            horses.add(new Horse("Test horse #" + i, 2.5));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());

    }

    @Test
    void move() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
//            verify(horse, times(5)).move(); // Тест пройдет, если move() вызывался 5 раз
//            verify(horse, atLeast(5)).move(); // Тест пройдет, если move() вызывался не менее 5 раз
        }

    }

    @Test
    void getWinner() {

        Horse horse1 = new Horse("Test horse #1", 2.5, 2.0);
        Horse horse2 = new Horse("Test horse #2", 2.5, 3.0);
        Horse winner = new Horse("Winner", 2.5, 5.0);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, winner));

        assertSame(winner, hippodrome.getWinner());

    }
}