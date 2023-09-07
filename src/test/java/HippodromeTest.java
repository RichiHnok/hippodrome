import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void listIsNull_HippodromeConstructor_TestingExceptionType(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
    }

    @Test
    void listIsNull_HippodromeConstructor_TestingExceptionMessage(){
        try{
            new Hippodrome(null);
        }catch(IllegalArgumentException exception){
            assertEquals("Horses cannot be null.", exception.getMessage());
        }
    }

    @Test
    void listIsEmpty_HippodromeConstructor_TestingExceptionType(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>());
                }
        );
    }

    @Test
    void listIsEmpty_HippodromeConstructor_TestingExceptionMessage(){
        try{
            new Hippodrome(new ArrayList<>());
        }catch(IllegalArgumentException exception){
            assertEquals("Horses cannot be empty.", exception.getMessage());
        }
    }
    @Test
    void methodGetHorses_isItReturnsSameListAsInConstructor(){
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            horses.add(new Horse("horse", 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertArrayEquals(horses.toArray(), hippodrome.getHorses().toArray());
    }

    @Test
    void methodMove_isMoveRunsInAllHorses(){
        List<Horse> mockedHorses = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            mockedHorses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();

        for(int i = 0; i < 50; i++){
            Mockito.verify(mockedHorses.get(i)).move();
        }
    }

    @Test
    void givingNothing_methodGetWinner_isWinHorseHasLargestDistance(){
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4, 5),
                new Horse("Blaze", 2.7, 10),
                new Horse("Cherry", 3, 15)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(2), hippodrome.getWinner());
    }
}