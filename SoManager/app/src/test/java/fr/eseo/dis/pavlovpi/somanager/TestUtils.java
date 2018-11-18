package fr.eseo.dis.pavlovpi.somanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestUtils {

    private String str;
    private String strExpected;

    /**
     * TestUtils
     * @param str The String to test
     * @param strExpected The String we want
     * @brief Creation of variables needed for data verification
     */
    public TestUtils(String str, String strExpected) {
        this.str = str;
        this.strExpected = strExpected;
    }

    /**
     * Check : params
     * The list of parameters to check
     * @return an Object with the list of String and size
     */
    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[]{"test", "test"},
                new Object[]{"", ""},
                new Object[]{"thisstringisrealytoolongfortheappsowewillcutit", "thisstringisrealytoolongforthe"},
                new Object[]{"éè à ëê _^/-*=", "éè à ëê _^/-*="},
                new Object[]{"éè à ëê _^/-*=éè à ëê _^/-*=éè à ëê _^/-*=", "éè à ëê _^/-*=éè à ëê _^/-*=éè"}
        );
    }

    @Test
    public void testReduceString() {
        // TODO Test url
        // assertEquals(this.strExpected, TODO );
    }
}