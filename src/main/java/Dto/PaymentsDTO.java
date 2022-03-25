package Dto;

import lombok.Data;

@Data
public class PaymentsDTO {

    private Long id;
    private String type;
    private int discount;
    private boolean status;

}
