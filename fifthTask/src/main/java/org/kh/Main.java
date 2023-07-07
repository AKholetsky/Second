package org.kh;

import org.kh.service.account.AccountService;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(1);

        AccountService service = new AccountService();
        service.saveAccount(account);
    }
}