define({ "api": [
  {
    "type": "get",
    "url": "/logs/last/html",
    "title": "",
    "description": "<p>Retrieve the last log message (HTML format)</p>",
    "group": "Internal",
    "version": "1.0.0",
    "name": "GetLastLineHtml",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 200 OK\n<tr><td>Level</td><td class='level'>Message</td></tr>",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/mobilebox/reader/service/Service.java",
    "groupTitle": "Internal",
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "optional": false,
            "field": "InternalError",
            "description": "<p>Generic error</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Server Error\n{\n    \"code\": \"\",\n    \"message\": \"Error description\"\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/log",
    "title": "",
    "description": "<p>Retrieve the dashboard (Yes, the dashboard).</p>",
    "group": "Internal",
    "version": "1.0.0",
    "filename": "src/main/java/com/mobilebox/reader/service/Service.java",
    "groupTitle": "Internal",
    "name": "GetLog"
  },
  {
    "type": "get",
    "url": "/logs/total/:level",
    "title": "",
    "description": "<p>Retrieve the total of a given log level. (Plain text format)</p>",
    "group": "Internal",
    "version": "1.0.0",
    "name": "GetTotalByLevel",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 200 OK\nerror: 8",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/mobilebox/reader/service/Service.java",
    "groupTitle": "Internal",
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "optional": false,
            "field": "InternalError",
            "description": "<p>Generic error</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Server Error\n{\n    \"code\": \"\",\n    \"message\": \"Error description\"\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/logs/last",
    "title": "",
    "description": "<p>Retrieve the last log message.</p>",
    "group": "Logs",
    "version": "1.0.0",
    "name": "GetLastLine",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 200 OK\n{\n\t    \"data\": {\n\t        \"message\": \"[Appium] Appium REST http interface listener started on 0.0.0.0:4723\",\n\t        \"level\": \"info\"\n\t    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/mobilebox/reader/service/Service.java",
    "groupTitle": "Logs",
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "optional": false,
            "field": "InternalError",
            "description": "<p>Generic error</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Server Error\n{\n    \"code\": \"\",\n    \"message\": \"Error description\"\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/logs/:lines",
    "title": "",
    "description": "<p>Retrieve N lines of log (from the beginning).</p>",
    "group": "Logs",
    "version": "1.0.0",
    "name": "GetLines",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "lines",
            "description": "<p>Number of lines to retrieve.</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 200 OK\n{\n\t    \"data\": {\n\t        \"items\": [\n\t            {\n\t                \"message\": \"[Appium] Received SIGINT - shutting down\",\n\t                \"level\": \"info\"\n\t            }\n\t        ],\n\t        \"size\": 1\n\t    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/mobilebox/reader/service/Service.java",
    "groupTitle": "Logs",
    "error": {
      "fields": {
        "400": [
          {
            "group": "400",
            "optional": false,
            "field": "ParameterError",
            "description": "<p>Check your request parameters. You might be using an unsupported parameter or have a malformed something or another.</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "optional": false,
            "field": "InternalError",
            "description": "<p>Generic error</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n    \"code\": \"\",\n    \"message\": \"Error description\"\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Server Error\n{\n    \"code\": \"\",\n    \"message\": \"Error description\"\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/logs",
    "title": "",
    "description": "<p>Retrieve a collection of all log messages.</p>",
    "group": "Logs",
    "version": "1.0.0",
    "name": "GetLogs",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 200 OK\n{\n\t    \"data\": {\n\t        \"items\": [\n\t            {\n\t                \"message\": \"[Appium] Appium REST http interface listener started on 0.0.0.0:4723\",\n\t                \"level\": \"info\"\n\t            },\n\t            {\n\t                \"message\": \"[Appium] Appium support for versions of node < 8 has been deprecated and will be removed in a future version. Please upgrade!\",\n\t                \"level\": \"warn\"\n\t            },\n\t            {\n\t                \"message\": \"[Appium]   webhook: 127.0.0.1:5000\",\n\t                \"level\": \"info\"\n\t            },\n\t            {\n\t                \"message\": \"[Appium] Welcome to Appium v1.8.0\",\n\t                \"level\": \"info\"\n\t            },\n\t            {\n\t                \"message\": \"[Appium] Non-default server args:\",\n\t                \"level\": \"info\"\n\t            }\n\t        ],\n\t        \"size\": 5\n   }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/mobilebox/reader/service/Service.java",
    "groupTitle": "Logs",
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "optional": false,
            "field": "InternalError",
            "description": "<p>Generic error</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Server Error\n{\n    \"code\": \"\",\n    \"message\": \"Error description\"\n}",
          "type": "json"
        }
      ]
    }
  }
] });
