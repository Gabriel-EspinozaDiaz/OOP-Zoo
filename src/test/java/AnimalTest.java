import animals.*;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


class AnimalTest {
    Buzzard nigel;
    Parrot bobby;

    @BeforeEach
    void setup() {
        nigel = new Buzzard("nigel");
        bobby = new Parrot("bobby");

    }

    @org.junit.jupiter.api.Test
    void getNickname() {
        assertEquals("nigel",nigel.getNickname());
        assertFalse(nigel.isCompatibleWith(bobby));

    }

    @org.junit.jupiter.api.Test
    void isCompatibleWith() {
    }

    @org.junit.jupiter.api.Test
    void getInteraction() {
    }

    @org.junit.jupiter.api.Test
    void setInteraction() {
    }
}