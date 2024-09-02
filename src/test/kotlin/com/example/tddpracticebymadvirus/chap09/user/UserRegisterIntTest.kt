package com.example.tddpracticebymadvirus.chap09.user

import com.example.tddpracticebymadvirus.chap07.user.exception.DupIdException
import com.example.tddpracticebymadvirus.chap09.user.UserRegister
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import kotlin.test.assertEquals

class UserRegisterIntTest(
    @Autowired private val register: UserRegister,
    @Autowired private val jdbcTemplate: JdbcTemplate,
) {
    @Test
    fun 동일ID가_이미_존재하면_예외() {
        // given
        jdbcTemplate.update(
            "insert into user value (?,?,?)" +
            "on duplicate key update password = ?, email = ?",
            "cbk", "pw", "cbk@cbk.com", "pw", "cbk@cbk.com"
        )

        // when & then
        assertThrows<DupIdException> {
            register.register("cbk", "strongpw", "email@email.com")
        }
    }

    @Test
    fun 존재하지_않으면_저장함() {
        // given
        jdbcTemplate.update("delete from user where id = ?", "cbk")

        // when
        register.register("cbk", "strongpw", "email@email.com")

        // then
        val rowSet = jdbcTemplate.queryForRowSet("select * from user where id = ?", "cbk")
        rowSet.next()
        assertEquals("email@email.com", rowSet.getString("email"))
    }
}