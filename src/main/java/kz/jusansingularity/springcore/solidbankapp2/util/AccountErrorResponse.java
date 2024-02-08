package kz.jusansingularity.springcore.solidbankapp2.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountErrorResponse {

    private String message;
    private long timestamp;

}
