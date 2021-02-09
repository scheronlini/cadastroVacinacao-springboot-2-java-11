package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaNome {

	public static boolean isNome(String nome) {

		if (nome.length() < 5 || nome.length() > 50) {
			return (false);
		}
		if (nome.indexOf(" ") == -1) {
			return (false);
		}

		if (nome.isEmpty()) {
			return (false);
		} else {
			Pattern pattern = Pattern.compile("[0-9]");
			Matcher matcher = pattern.matcher(nome);
			if (matcher.find()) {
				return (false);
			}
		}
		return (true);
	}
}