package kz.jusansingularity.springcore.solidbankapp2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String message;
    private Long id;
    private String username;
}
