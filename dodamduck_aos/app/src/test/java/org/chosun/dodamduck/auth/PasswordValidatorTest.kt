import org.chosun.dodamduck.utils.AuthExt.verifyPassword
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PasswordValidatorTest {

    @Test
    fun verifyPassword_startsWithLowercase_returnsTrue() {
        assertTrue("password123A".verifyPassword())
    }

    @Test
    fun verifyPassword_startsWithUppercase_returnsTrue() {
        assertTrue("Password123".verifyPassword())
    }

    @Test
    fun verifyPassword_missingNumbers_returnsFalse() {
        assertFalse("Password".verifyPassword())
    }

    @Test
    fun verifyPassword_missingUppercaseLetters_returnsFalse() {
        assertFalse("password123".verifyPassword())
    }

    @Test
    fun verifyPassword_missingLowercaseLetters_returnsFalse() {
        assertFalse("PASSWORD123".verifyPassword())
    }

    @Test
    fun verifyPassword_tooShort_returnsFalse() {
        assertFalse("Pass12".verifyPassword())
    }

    @Test
    fun verifyPassword_tooLong_returnsFalse() {
        assertFalse("Password123456789A".verifyPassword())
    }
}
