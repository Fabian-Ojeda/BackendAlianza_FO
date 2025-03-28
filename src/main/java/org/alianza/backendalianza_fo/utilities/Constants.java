package org.alianza.backendalianza_fo.utilities;

/**
 * Utility class that contains all constants used in the client module.
 *
 * This class defines URL paths, success messages, and log messages
 * to ensure consistency and avoid hardcoded strings throughout the application.
 */
public class Constants {

    // Base URL for client-related endpoints
    public static final String BASE_URL_CLIENTS = "/clients";

    // Path to retrieve all clients
    public static final String PATH_GET_ALL_CLIENTS = "/getAllClients";

    // Path to retrieve clients by shared key
    public static final String PATH_GET_CLIENTS_BY_SHARED_KEY = "/getClientsBySharedKey/{sharedKey}";

    // Path to create a new client
    public static final String PATH_CREATE_CLIENTS = "/create";

    // Success message when a client is created
    public static final String MESSAGE_CLIENT_CREATED_SUCCESFULL = "Client created successfully";

    // Log message when starting to search for all clients
    public static final String LOG_START_SEARCHING_ALL_CLIENTS = "Start searching for all clients";

    // Log message when starting to search for clients by shared key
    public static final String LOG_START_SEARCHING_CLIENTS_BY_SHARED_KEY =
            "Start searching for clients by shared key with shared key %s";

    // Log message showing the result of searching clients by shared key
    public static final String LOG_RESULT_SEARCHING_CLIENTS_BY_SHARED_KEY =
            "%d records have been found with shared key %s";

    // Log message when a user is created
    public static final String LOG_USER_CREATED =
            "An user was created with id %d";
}