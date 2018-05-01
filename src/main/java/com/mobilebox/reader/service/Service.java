package com.mobilebox.reader.service;


import static com.github.jankroken.commandline.OptionStyle.SIMPLE;
import static com.mobilebox.reader.core.Conts.API_VERSION;
import static com.mobilebox.reader.core.Conts.CONTENT_TYPE_HTML;
import static com.mobilebox.reader.core.Conts.CONTENT_TYPE_JSON;
import static com.mobilebox.reader.core.HttpResponseCodes.BAD_REQUEST;
import static com.mobilebox.reader.core.HttpResponseCodes.INTERNAL_ERROR;
import static com.mobilebox.reader.core.HttpResponseCodes.OK;
import static java.lang.String.format;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jankroken.commandline.CommandLineParser;
import com.google.gson.Gson;
import com.mobilebox.reader.core.HttpResponseCodes;
import com.mobilebox.reader.core.LogAccess;
import com.mobilebox.reader.core.MustacheEngine;
import com.mobilebox.reader.exception.InternalException;
import com.mobilebox.reader.exception.NotFoundException;
import com.mobilebox.reader.exception.ParameterException;
import com.mobilebox.reader.model.Log;
import com.mobilebox.reader.model.Response;
import com.mobilebox.reader.model.Responses;

import spark.ModelAndView;

/**
 * Appium has a built-in mechanism in order to post messages to an external
 * Webhook. They make use of normal HTTP requests with a JSON payload that
 * includes a log message and the log level. This service listens these messages.
 */
public class Service {

	static Logger log = LoggerFactory.getLogger(Service.class);
	static HashMap<String, Object> service = new HashMap<String, Object>();
	static int lastLine = 0;

	/**
	 * Routes are matched in the order they are defined.
	 * 
	 * @throws ParameterException If one of the given parameters are invalid.
	 */
	public static void main(String[] args) throws ParameterException {
		Integer port = 0;
		String ip = "127.0.0.1";
		
		try {
            Args arguments = CommandLineParser.parse(Args.class, args, SIMPLE);
            port = Integer.valueOf(arguments.getPort());
            ip = arguments.getIp();
        } catch (Exception e) {
        	throw new ParameterException(format("Parameter is not valid. %s", e.getMessage()));
        }

		port(port);
		log.info("Start Appium log reader service on http://{}:{}", ip, port);
		service.put("service",format("http://%s:%s",ip, port));
		staticFileLocation("/public");

		/**
		 * Before-filters are evaluated before each request, and can read the request
		 * and read/modify the response. Enabled CORS.
		 */
		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Request-Method", "*");
			response.header("Access-Control-Allow-Headers", "*");
		});

		/**
		 * @api {get} /log
		 * @apiDescription Retrieve the page (Yes, the page).
		 * @apiGroup Log
	     * @apiVersion 1.0.0
		 * 
		 */
		get("/log", (request, response) -> new ModelAndView(service, "reader.mustache"), new MustacheEngine());

	    /**
	     * @api {get} /logs/last
	     * @apiDescription Retrieve a collection of all log messages.
	     * @apiGroup Log
	     * @apiVersion 1.0.0
	     * @apiName GetLastLine
	     * 
	     * @apiSuccessExample Success-Response: 
	     * HTTP/1.1 200 OK
		 * {
		 *	    "data": {
		 *	        "items": [
		 *	            {
		 *	                "message": "[Appium] Appium REST http interface listener started on 0.0.0.0:4723",
		 *	                "level": "info"
		 *	            },
		 *	            {
		 *	                "message": "[Appium] Appium support for versions of node < 8 has been deprecated and will be removed in a future version. Please upgrade!",
		 *	                "level": "warn"
		 *	            },
		 *	            {
		 *	                "message": "[Appium]   webhook: 127.0.0.1:5000",
		 *	                "level": "info"
		 *	            },
		 *	            {
		 *	                "message": "[Appium] Welcome to Appium v1.8.0",
		 *	                "level": "info"
		 *	            },
		 *	            {
		 *	                "message": "[Appium] Non-default server args:",
		 *	                "level": "info"
		 *	            }
		 *	        ],
		 *	        "size": 5
	     *    }
		 *}
	     * 
	     * @apiUse InternalError
	     */
		get(API_VERSION + "/logs", (request, response) -> {
			Responses<Log> logs = LogAccess.INSTANCE.getLogs();
			response.type(CONTENT_TYPE_JSON);
			response.status(OK);
			return new Response().withData(logs).toJson();
		});

	    /**
	     * @api {get} /logs/last
	     * @apiDescription Retrieve the last log message.
	     * @apiGroup Log
	     * @apiVersion 1.0.0
	     * @apiName GetLastLine
	     * 
	     * @apiSuccessExample Success-Response: 
	     * HTTP/1.1 200 OK
		 *{
		 *	    "data": {
		 *	        "message": "[Appium] Appium REST http interface listener started on 0.0.0.0:4723",
		 *	        "level": "info"
		 *	    }
		 *}
	     * 
	     * @apiUse InternalError
	     */
		get(API_VERSION + "/logs/last", (request, response) -> {
			Log log = LogAccess.INSTANCE.getLastLine();
			response.type(CONTENT_TYPE_JSON);
			response.status(OK);
			return new Response().withData(log).toJson();
		});
			
	    /**
	     * @api {get} /logs/last/html
	     * @apiDescription Retrieve the last log message (HTML format)
	     * @apiGroup Log
	     * @apiVersion 1.0.0
	     * @apiName GetLastLineHtml
	     * 
	     * @apiSuccessExample Success-Response: 
	     * HTTP/1.1 200 OK
		 * <tr><td>Level</td><td class='level'>Message</td></tr>
	     * 
	     * @apiUse InternalError
	     */
		get(API_VERSION + "/logs/last/html", (request, response) -> {
			List<Log> logs = LogAccess.INSTANCE.getLogContent();
			response.type(CONTENT_TYPE_HTML);
			response.status(OK);
			//No-Op Responses for Intercooler.
			if(logs.size() == 0) {
				return " ";
			}
			
			if(logs.size() <= lastLine) {
				return " ";
			}
			
			Log log = logs.get(lastLine);			
			lastLine = lastLine + 1;
			
			if(log == null) {
				return " ";
			}
			return log.toHtml();
		});

	    /**
	     * @api {get} /logs/total/:level
	     * @apiDescription Retrieve the total of a given log level. (Plain text format)
	     * @apiGroup Log
	     * @apiVersion 1.0.0
	     * @apiName GetTotalByLevel
	     * 
	     * @apiSuccessExample Success-Response: 
	     * HTTP/1.1 200 OK
		 * error: 8
	     * 
	     * @apiUse InternalError
	     */
		get(API_VERSION + "/logs/total/:level", (request, response) -> {
			String level = request.params(":level");
			int total = LogAccess.INSTANCE.getTotalByLevel(level);
			response.type(CONTENT_TYPE_HTML);
			response.status(OK);	
			return level + ": " + total;
		});
		
	    /**
	     * @api {get} /logs/:lines 
	     * @apiDescription Retrieve N lines of log (from the beginning).
	     * @apiGroup Log
	     * @apiVersion 1.0.0
	     * @apiName GetLines
	     * @apiParam {int} lines Number of lines to retrieve.
	     * 
	     * @apiSuccessExample Success-Response: 
	     * HTTP/1.1 200 OK
		 *{
		 *	    "data": {
		 *	        "items": [
		 *	            {
		 *	                "message": "[Appium] Received SIGINT - shutting down",
		 *	                "level": "info"
		 *	            }
		 *	        ],
		 *	        "size": 1
		 *	    }
         *}
	     * 
	     * @apiUse ParameterError
	     * @apiUse InternalError
	     */
		get(API_VERSION + "/logs/:lines", (request, response) -> {
			int lines = 0;

			try {
				lines = Integer.parseInt(request.params(":lines"));
			} catch (Exception e) {
				throw new ParameterException(format("The parameter lines must be numeric. %s", e.getMessage()));
			}

			Responses<Log> nLogs = LogAccess.INSTANCE.getLines(lines);
			response.type(CONTENT_TYPE_JSON);
			response.status(OK);
			return new Response().withData(nLogs).toJson();
		});

		/**
		 * The Appium POST receiver. 
		 * 
		 * Payload: 
		 *{
		 *  "params": {		     
		 *    "message": "Console",
		 *    "level": "info"
		 *  }
		 *}
		 */
		post("/", (request, response) -> {
			Map<?, ?> log = (Map<?, ?>) new Gson().fromJson(request.body(), Map.class).get("params");
			Log logThis = new Log(log.get("message").toString(), log.get("level").toString());
			LogAccess.INSTANCE.getLogContent().add(logThis);
			response.status(OK);
			return "";
		});

	    /**
	     * @apiDefine InternalError
	     *
	     * @apiError (500) InternalError Generic error
	     *
	     * @apiErrorExample Error-Response:
	     * HTTP/1.1 500 Server Error
	     * {
	     *     "code": "",
	     *     "message": "Error description"
	     * }
	     */
		exception(InternalException.class, (e, request, response) -> {
			response.status(INTERNAL_ERROR);
			response.type(CONTENT_TYPE_JSON);
			response.body(new Response().withMessage(e.getMessage()).toJson());
		});

	    /**
	     * @apiDefine NotFoundError
	     *
	     * @apiError (404) NotFoundError The resource you have referenced could not be found.
	     *
	     * @apiErrorExample Error-Response:
	     * HTTP/1.1 404 Not Found
	     * {
	     *     "code": "",
	     *     "message": "Error description"
	     * }
	     */
		exception(NotFoundException.class, (e, request, response) -> {
			response.status(HttpResponseCodes.NOT_FOUND);
			response.type(CONTENT_TYPE_JSON);
			response.body(new Response().withMessage(e.getMessage()).toJson());
		});

	    /**
	     * @apiDefine ParameterError
	     *
	     * @apiError (400) ParameterError Check your request parameters. 
	     * You might be using an unsupported parameter or have a 
	     * malformed something or another.
	     *
	     * @apiErrorExample Error-Response:
	     * HTTP/1.1 400 Bad Request
	     * {
	     *     "code": "",
	     *     "message": "Error description"
	     * }
	     */
		exception(ParameterException.class, (e, request, response) -> {
			response.status(BAD_REQUEST);
			response.type(CONTENT_TYPE_JSON);
			response.body(new Response().withMessage(e.getMessage()).toJson());
		});

	}

}
