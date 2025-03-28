package org.alianza.backendalianza_fo.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity representing the client information stored in the database.
 *
 * This entity is mapped to the table "CLIENT" and holds essential
 * data related to the client's identification, contact information, and validity period.
 */
@Entity
@Table(name = "CLIENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    /**
     * Primary key - auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique shared key generated from the client's name and last name.
     */
    @Column(nullable = false, unique = true)
    private String sharedKey;

    /**
     * Business identifier for the client.
     */
    @Column(nullable = false)
    private String businessId;

    /**
     * Unique email address of the client.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Contact phone number of the client.
     */
    @Column(nullable = false)
    private String phone;

    /**
     * Start date of the client's data validity.
     */
    @Column(nullable = false)
    private Date dataStart;

    /**
     * End date of the client's data validity.
     */
    @Column(nullable = false)
    private Date dataFinish;
}