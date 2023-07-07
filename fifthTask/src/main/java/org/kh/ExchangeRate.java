package org.kh;

import java.math.BigDecimal;

public record ExchangeRate(Currency from, Currency to, BigDecimal rate) {

}