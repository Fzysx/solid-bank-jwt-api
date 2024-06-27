package kz.jusansingularity.springcore.solidbankapp2.DTO;

import lombok.Data;

@Data
public class JwtRequestDTO {
    private String username;
    private String password;
}
