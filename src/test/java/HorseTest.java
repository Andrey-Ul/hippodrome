import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;


class HorseTest {

    // Tests of constructor
    @Test
    void nullNameException() {

        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5));

//        assertThrows(IllegalArgumentException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                new Horse(null, 2.5);
//            }
//        });
    }

    @Test
    void nullNameMessage() {
        try {
            new Horse(null, 2.5);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @Test
    void nullNameExceptionAndMessage() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5));

        assertEquals("Name cannot be null.", e.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void emptyNameException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.5));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void emptyNameMessage(String name) {
        try {
            new Horse(name, 2.5);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    void nullSpeedException() {

        assertThrows(IllegalArgumentException.class, () -> new Horse("Test Horse", -1.0));

//        assertThrows(IllegalArgumentException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                new Horse("Test Horse", -1.0);
//            }
//        });
    }

    @Test
    void nullSpeedMessage() {
        try {
            new Horse("Test Horse", -1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    void nullDistanceException() {

        assertThrows(IllegalArgumentException.class, () -> new Horse("Test Horse", 2.5, -1.0));

//        assertThrows(IllegalArgumentException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                new Horse("Test Horse", 2.5, -1.0);
//            }
//        });
    }

    @Test
    void nullDistanceMessage() {
        try {
            new Horse("Test Horse", 2.5, -1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    void getName() {
        Horse testHorse = new Horse("Test horse", 2.5);
        assertEquals("Test horse", testHorse.getName());
    }

    // Проверяем, что name верно задаётся при создании Horse конструктором
    @Test
    void setNameConstructor() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Test horse", 2.5);

        Field nameField = Horse.class.getDeclaredField("name");
        nameField.setAccessible(true);
        String name = (String) nameField.get(horse);
        assertEquals("Test horse", name);
    }

    @Test
    void getSpeed() {
        Horse testHorse = new Horse("Test horse", 2.5);
        assertEquals(2.5, testHorse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse testHorse = new Horse("Test horse", 2.5, 2.5);
        assertEquals(2.5, testHorse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3, 0.4, 0.5})
    void move(double randomDouble) {

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Test horse", 2.5, 5.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);

            horse.move();

            assertEquals(5.0 + 2.5 * randomDouble, horse.getDistance());

        }

    }

    @Test
    void moveGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Test horse", 2.5, 5.0).move();
//            mockedStatic.verify(new MockedStatic.Verification() {
//                @Override
//                public void apply() throws Throwable {
//                    Horse.getRandomDouble(0.2, 0.9);
//                }
//            });
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            //mockedStatic.verify(() -> Horse.getRandomDouble(anyDouble(), anyDouble())); // Тест без привязки к значениям
            //mockedStatic.verify(() -> Horse.getRandomDouble(0.2, anyDouble())); // Тест не пройдет, т.к. нельзя смешивать мачеры (anyDouble) и значения
            //mockedStatic.verify(() -> Horse.getRandomDouble(eq(0.2), anyDouble())); // Тест пройдет, так как значение обернуто в eq
        }
    }

}