package com.mshah.crowdfunding.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationFormDto {
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1.00", message = "Amount must be greater than or equal to 1$")
    private BigDecimal amount;

    private String message;

    @NotBlank(message = "Card type is required")
    private String cardType;

    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "^[0-9]{16}$", message = "Card number must be 16 digits.")
    private String cardNumber;

    @NotBlank(message = "CVV is required")
    @Pattern(regexp = "^[0-9]{3}$", message = "CVV must be 3 digits.")
    private String cardCvv;

    @NotBlank(message = "Expiration date is required")
    private String cardExpDate;

    public void setMessage(String message) {
        if (!StringUtils.isBlank(message)) {
            this.message = message;
        }
    }
}
