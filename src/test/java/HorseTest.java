import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void nameIsNull_HorseConstructor_TestingExceptionType(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(null, 0, 0);
                }
        );
    }

    @Test
    void nameIsNull_HorseConstructor_TestingExceptionMessage(){
        try{
            new Horse(null, 0, 0);
        }catch(IllegalArgumentException exception){
            assertEquals("Name cannot be null.", exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "       ", " ", "   "})
    void nameIsBlank_HorseConstructor_TestingExceptionType(String str){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(str, 0, 0);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void namesIsBlank_HorseConstructor_TestingExceptionMessage(){
        try{
            new Horse("", 0, 0);
        }catch(IllegalArgumentException exception){
            assertEquals("Name cannot be blank.", exception.getMessage());
        }
    }

    @Test
    void speedIsNegative_HorseConstructor_TestingExceptionType(){
        assertThrows(IllegalArgumentException.class,
            () -> {
                new Horse("Horse", -1, 0);
            }
        );
    }

    @Test
    void speedIsNegative_HorseConstructor_TestingExceptionMessage(){
        try{
            new Horse("Horse", -1, 0);
        }catch(IllegalArgumentException exception){
            assertEquals("Speed cannot be negative.", exception.getMessage());
        }
    }

    @Test
    void distanceIsNegative_HorseConstructor_TestingExceptionType(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Horse", 0, -1);
                }
        );
    }

    @Test
    void distanceIsNegative_HorseConstructor_TestingExceptionMessage(){
        try{
            new Horse("Horse", 0, -1);
        }catch(IllegalArgumentException exception){
            assertEquals("Distance cannot be negative.", exception.getMessage());
        }
    }

    @Test
    void givingNameInConstructor_methodGetName_isItReturnsConstructorName() {
        String expectedName = "Horsy";
        Horse horse = new Horse(expectedName, 0, 0);
        assertEquals(expectedName, horse.getName());

    }

    @Test
    void givingSpeedInConstructor_methodGetSpeed_isItReturnsConstructorSpeed() {
        int expectedSpeed = 10;
        Horse horse = new Horse("Horse", expectedSpeed, 0);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void givingDistanceInConstructor_methodGetDistance_isItReturnsConstructorDistance() {
        int expectedDistance = 10;
        Horse horse = new Horse("Horsy", 0, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void notGivingDistanceInConstructor_methodGetDistance_isItReturnsZero(){
        Horse horse = new Horse("Horsy", 0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void givingNothing_staticMethodMove_isItRunsGetRandomDoubleWithArguments0209() {
        try(MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)){
            Horse.getRandomDouble(0.2, 0.9);
            horse.verify(()->Horse.getRandomDouble(0.2,0.9), times(1));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 0.7, 0.3})
    void staticMethodMove_isItCalculateCorrectly(double parameter){
        try(MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)){
            horse.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(parameter);
            Horse horse2 = new Horse("1", 1, 5);
            double expectedDistance = horse2.getDistance() + horse2.getSpeed()*parameter;
            horse2.move();
            assertEquals(expectedDistance, horse2.getDistance());
        }
    }
}