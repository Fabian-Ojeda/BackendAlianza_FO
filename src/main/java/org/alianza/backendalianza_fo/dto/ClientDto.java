package org.alianza.backendalianza_fo.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data Transfer Object representing client information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    @Schema(description = "Unique shared key generated automatically from name and last name.", example = "JSmith")
    private String sharedKey;

    @Schema(description = "Business identifier of the client.", example = "John Smith")
    private String businessId;

    @Schema(description = "First name of the client.", example = "John")
    private String name;

    @Schema(description = "Last name of the client.", example = "Smith")
    private String lastName;

    @Schema(description = "Client's email address.", example = "john.smith@example.com")
    private String email;

    @Schema(description = "Client's phone number. Should be at least 10 digits.", example = "1234567890")
    private String phone;

    @Schema(description = "Date when the client started the business relationship.", example = "2025-01-01")
    private Date dataStart;

    @Schema(description = "Date when the client finished the business relationship.", example = "2025-12-31")
    private Date dataFinish;
}
