package com.leon.ms_accounts.accounts;

import com.intuit.karate.junit5.Karate;

public class AccountsRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}

