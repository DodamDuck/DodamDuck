package org.chosun.dodamduck.auth

import org.chosun.dodamduck.utils.AuthExt.verifyId
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse

class IdValidatorTest {

    @Test
    fun verifyId_validPattern_returnTrue() {
        assertTrue("a1b2c3d4".verifyId())
    }

    @Test
    fun verifyId_startsWithNumber_returnFalse() {
        assertFalse("1abc1234".verifyId())
    }

    @Test
    fun verifyId_shorterThan8Characters_returnsFalse() {
        assertFalse("a1b2c".verifyId())
    }

    @Test
    fun verifyId_longerThan16Characters_ReturnsFalse() {
        assertFalse("a1b2c3d4e5f6g7h89".verifyId())
    }

    @Test
    fun verifyId_withoutNumbers_returnsFalse() {
        assertFalse("abcdefgh".verifyId())
    }

    @Test
    fun verifyId_withoutLowercaseLetters_returnsFalse() {
        assertFalse("12345678".verifyId())
    }

    @Test
    fun verifyId_withUppercaseLettersAndCorrectPattern_returnsTrue() {
        assertTrue("aB1cD2eF3".verifyId())
    }
}