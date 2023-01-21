package com.inops.visitorpass.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails {

	private String cardNumber;
    private String viditorName;
    private String fromDate;
    private String toDate;
}
