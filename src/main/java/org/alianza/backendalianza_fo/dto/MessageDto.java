package org.alianza.backendalianza_fo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a response message.
 *
 * This DTO is typically used to provide a message and an error status
 * to the client after performing an operation.
 */
@Data
@AllArgsConstructor
public class MessageDto {
    /**
     * Descriptive message for the client, explaining the result of the operation.
     */
    @Schema(description = "Descriptive message explaining the result of the operation", example = "Client created successfully")
    String message;

    /**
     * Indicates whether the operation resulted in an error.
     */
    @Schema(description = "Indicates whether an error occurred (true if there was an error, false otherwise)", example = "false")
    boolean error;
}
