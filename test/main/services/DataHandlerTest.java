package main.services;

import main.models.BaseDragon;
import main.models.UserDragon;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {

    private static final String TEST_BASE_PATH = "test/test_dragons.txt";
    private static final String TEST_USER_DRAGON_PATH = "test/test_user_dragons.txt";

    @Test
    void loadFiles_withInvalidFilePaths() {
        boolean isSuccessLoad = DataHandler.loadFiles("", "");

        assertFalse(isSuccessLoad);

        assertNotNull(DataHandler.getBaseDragons());
        assertNotNull(DataHandler.getUserDragons());

        assertEquals(0, DataHandler.getBaseDragons().size());
        assertEquals(0, DataHandler.getUserDragons().size());
    }

    @Test
    void loadFiles_withInvalidUserDragonPath() {
        boolean isSuccessLoad = DataHandler.loadFiles(TEST_BASE_PATH, "");

        assertFalse(isSuccessLoad);

        assertEquals(20, DataHandler.getBaseDragons().size());
        assertEquals(0, DataHandler.getUserDragons().size());
    }

    @Test
    void loadFiles_withInvalidBaseDragonPath() {
        boolean isSuccessLoad = DataHandler.loadFiles("", TEST_USER_DRAGON_PATH);

        assertFalse(isSuccessLoad);

        assertEquals(0, DataHandler.getBaseDragons().size());
        assertEquals(0, DataHandler.getUserDragons().size());
    }

    @Test
    void loadFiles_withValidPaths() {
        boolean isSuccessLoad = DataHandler.loadFiles(TEST_BASE_PATH, TEST_USER_DRAGON_PATH);

        assertTrue(isSuccessLoad);

        List<BaseDragon> bases = DataHandler.getBaseDragons();
        List<UserDragon> userDragons = DataHandler.getUserDragons();

        assertEquals(20, bases.size());
        assertEquals(10, userDragons.size());

        assertBaseDragonsEquals(bases);

        assertUserDragonsEquals(userDragons);
    }

    private void assertBaseDragonsEquals(List<BaseDragon> bases) {
        assertEquals(1, bases.get(0).getId());
        assertEquals("Terra", bases.get(0).getName());

        assertEquals(15, bases.get(14).getId());
        assertEquals("Firebird", bases.get(14).getName());

        assertEquals(20, bases.get(19).getId());
        assertEquals("Star", bases.get(19).getName());
    }

    private void assertUserDragonsEquals(List<UserDragon> dragons) {
        assertNotNull(dragons.get(0).getBaseDragon());
        assertEquals(1, dragons.get(0).getBaseDragon().getId());
        assertEquals("Béla", dragons.get(0).getName());

        assertNotNull(dragons.get(4).getBaseDragon());
        assertEquals(6, dragons.get(4).getBaseDragon().getId());
        assertEquals("Sándor", dragons.get(4).getName());

        assertNotNull(dragons.get(9).getBaseDragon());
        assertEquals(12, dragons.get(9).getBaseDragon().getId());
        assertEquals("Kázmér", dragons.get(9).getName());
    }

}