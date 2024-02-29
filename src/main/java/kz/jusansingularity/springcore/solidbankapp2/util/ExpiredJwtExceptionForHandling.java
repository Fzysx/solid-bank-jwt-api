package kz.jusansingularity.springcore.solidbankapp2.util;

import java.io.IOException;

public class ExpiredJwtExceptionForHandling extends IOException {

    public ExpiredJwtExceptionForHandling(String message) {
        super(message);
    }
}
