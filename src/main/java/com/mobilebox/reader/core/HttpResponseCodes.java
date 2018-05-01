package com.mobilebox.reader.core;

public abstract class HttpResponseCodes {
	/**
	 * Indicates that the client may continue with its request. This value is
	 * specified as part of RFC 2068 but was not included in Sun's JDK, so beware of
	 * using this value
	 */
	static final int CONTINUE = 100;

	/**
	 * Indicates the request succeeded.
	 */
	public static final int OK = 200;

	/**
	 * The requested resource has been created.
	 */
	public static final int CREATED = 201;

	/**
	 * The request has been accepted for processing but has not completed. There is
	 * no guarantee that the requested action will actually ever be completed
	 * succesfully, but everything is ok so far.
	 */
	public static final int ACCEPTED = 202;

	/**
	 * The meta-information returned in the header is not the actual data from the
	 * original server, but may be from a local or other copy. Normally this still
	 * indicates a successful completion.
	 */
	public static final int NOT_AUTHORITATIVE = 203;

	/**
	 * The server performed the request, but there is no data to send back. This
	 * indicates that the user's display should not be changed.
	 */
	public static final int NO_CONTENT = 204;

	/**
	 * The server performed the request, but there is no data to sent back, however,
	 * the user's display should be "reset" to clear out any form fields entered.
	 */
	public static final int RESET = 205;

	/**
	 * The server completed the partial GET request for the resource.
	 */
	public static final int PARTIAL = 206;

	/* HTTP Redirection Response Codes */

	/**
	 * There is a list of choices available for the requested resource.
	 */
	public static final int MULT_CHOICE = 300;

	/**
	 * The resource has been permanently moved to a new location.
	 */
	public static final int MOVED_PERM = 301;

	/**
	 * The resource requested has been temporarily moved to a new location.
	 */
	public static final int MOVED_TEMP = 302;

	/**
	 * The response to the request issued is available at another location.
	 */
	public static final int SEE_OTHER = 303;

	/**
	 * The document has not been modified since the criteria specified in a
	 * conditional GET.
	 */
	public static final int NOT_MODIFIED = 304;

	/**
	 * The requested resource needs to be accessed through a proxy.
	 */
	public static final int USE_PROXY = 305;

	/* HTTP Client Error Response Codes */

	/**
	 * The request was misformed or could not be understood.
	 */
	public static final int BAD_REQUEST = 400;

	/**
	 * The request made requires user authorization. Try again with a correct
	 * authentication header.
	 */
	public static final int UNAUTHORIZED = 401;

	/**
	 * Code reserved for future use - I hope way in the future.
	 */
	public static final int PAYMENT_REQUIRED = 402;

	/**
	 * There is no permission to access the requested resource.
	 */
	public static final int FORBIDDEN = 403;

	/**
	 * The requested resource was not found.
	 */
	public static final int NOT_FOUND = 404;

	/**
	 * The specified request method is not allowed for this resource.
	 */
	public static final int BAD_METHOD = 405;

	/**
	 * Based on the input headers sent, the resource returned in response to the
	 * request would not be acceptable to the client.
	 */
	public static final int NOT_ACCEPTABLE = 406;

	/**
	 * The client must authenticate with a proxy prior to attempting this request.
	 */
	public static final int PROXY_AUTH = 407;

	/**
	 * The request timed out.
	 */
	public static final int CLIENT_TIMEOUT = 408;

	/**
	 * There is a conflict between the current state of the resource and the
	 * requested action.
	 */
	public static final int CONFLICT = 409;

	/**
	 * The requested resource is no longer available. This ususally indicates a
	 * permanent condition.
	 */
	public static final int GONE = 410;

	/**
	 * A Content-Length header is required for this request, but was not supplied.
	 */
	public static final int LENGTH_REQUIRED = 411;

	/**
	 * A client specified pre-condition was not met on the server.
	 */
	public static final int PRECON_FAILED = 412;

	/**
	 * The request sent was too large for the server to handle.
	 */
	public static final int ENTITY_TOO_LARGE = 413;

	/**
	 * The name of the resource specified was too long.
	 */
	public static final int REQ_TOO_LONG = 414;

	/**
	 * The request is in a format not supported by the requested resource.
	 */
	public static final int UNSUPPORTED_TYPE = 415;

	/**
	 * The server encountered an unexpected error (such as a CGI script crash) that
	 * prevents the request from being fulfilled.
	 */
	public static final int INTERNAL_ERROR = 500;

	/**
	 * The server does not support the requested functionality.
	 * 
	 * @since 1.3
	 */
	public static final int NOT_IMPLEMENTED = 501;

	/**
	 * The proxy encountered a bad response from the server it was proxy-ing for
	 */
	public static final int BAD_GATEWAY = 502;

	/**
	 * The HTTP service is not availalble, such as because it is overloaded and does
	 * not want additional requests.
	 */
	public static final int UNAVAILABLE = 503;

	/**
	 * The proxy timed out getting a reply from the remote server it was proxy-ing
	 * for.
	 */
	public static final int GATEWAY_TIMEOUT = 504;

	/**
	 * This server does not support the protocol version requested.
	 */
	public static final int VERSION = 505;
}
