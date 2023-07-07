package org.kh;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Account {

    private final long id;
    private final Map<Currency, BigDecimal> balances;

    public Account(final long id) {
        this.id = id;
        balances = new HashMap<>(2);
    }

    public long getId() {
        return id;
    }

    public String asJson() {
        return """
                {
                    id: %d,
                    %s
                }
                """.formatted(this.id, balancesToJson());

    }


    private String balancesToJson() {
        return balances.entrySet().stream().map(entry -> entry.getKey() + " : " + entry.getValue()).collect(Collectors.joining(",\\n"));
    }

}
