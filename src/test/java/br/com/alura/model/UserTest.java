package br.com.alura.model;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import br.com.alura.util.DigestUtil;

public final class UserTest {

	public static User build() throws NoSuchAlgorithmException {
		return new User().setEmail("ana@gmail.com").setInsertDate(LocalDateTime.now(ZoneId.of("UTC"))).setName("Ana")
				.setPassword(DigestUtil.convertPassword("123456qwerty")).setRole(UserRole.ADMIN).setUsername("ana");
	}
}
