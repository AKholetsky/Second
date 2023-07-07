package org.kh.service.account;

import org.kh.Account;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class AccountService {

    private final Logger logger = Logger.getLogger("AccountService");

    public boolean saveAccount(final Account account) {
        final var fileName = "Account" + account.getId();
        try {
            saveToFile(fileName, account.asJson());
            return true;
        } catch (IOException e) {
            logger.warning("Can't save account to file");
            return false;
        }
    }

    private void saveToFile(final String fileName, final String json) throws IOException {
        final Path path = Paths.get(fileName);
        Files.write(path, json.getBytes(StandardCharsets.UTF_8));
    }
}
