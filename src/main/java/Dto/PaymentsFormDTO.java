package Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PaymentsFormDTO {

    @NotEmpty(message = "type is required")
    private String type;

    @NotEmpty(message = "discount is required")
    private int discount;

    @NotEmpty(message = "status is required")
    private boolean status;

}
