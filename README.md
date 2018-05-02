# Appium Log Reader
**Appium Log Reader** is a simple viewer that works with the Appium logs. It supports filtering, searching, highlighting and many other useful features. 

[Appium](http://appium.io) has a built-in mechanism in order to post messages to an external Webhook. They make use of normal HTTP requests with a JSON payload that includes a log message and the log level. This service listens these messages.

You can also use **Appium Log Reader** as an API.


---

![Dashboard](https://raw.githubusercontent.com/mobileboxlab/appium-log-reader/master/docs/screen.png)

## Features

  - A great Appium log HTTP API.
  - View, filter, searching and and export logs in different formats such as: XLS, CSV, TXT, JSON, SQL, etc.
  - Localized log levels.
  - Shortcut to search errors messages on [DuckDuckGo](https://duckduckgo.com)

# Prerequisites

You will need:
* [Java](https://www.java.com)
* [Appium](http://appium.io)

# Getting Started

* Download the latest version of [Appium Log Reader]() (requires JDK 1.8 or newer) and install Appium
* Start **Appium Log Reader**:

```bash
java - jar appium-log-reader-service-X.X.X.jar -p 5000 -i 127.0.0.1
```

* Start **Appium** using **--webhook** flag. This flag enable the log output to HTTP listener:

```bash
appium --webhook 127.0.0.1:5000 
```

* Navigate to [http://127.0.0.1:5000/log](http://127.0.0.1:5000/log) to view the dashboard.

## Docker
An alternative way to run the **Appium Log Reader** is via Docker:


# HTTP API

Appium Log Reader’s HTTP API serves two primary purposes:

* The Appium Log Reader API gives you a way to embed Appium logs into another webpage or a third-party application.

* Programmatic search for logs. Most common tasks you might want to do outside the Appium console output or Appium Log Reader Dashboard, like searching programmatically for logs and retrieving logs during a certain time period, etc.

## URL structure

The Appium Log Reader API lives at [http://127.0.0.1:5000/api/v1/](http://127.0.0.1:5000/api/v1/), with particular endpoints following that prefix.

Responses are in **JSON** (except for some endpoint that are used on the Dashboard)

## Errors

Failed requests return **400 Bad Request**, and a JSON hash is provided containing a key called message with further information about the issue. For example:

```json
{
 "code": "ERROR_CODE",
 "message": "Error description"
}
```

* If the resource is not found, **404 Not Found** is returned. 

## Example calls

API examples use [httpie](https://httpie.org), a command-line HTTP client.

Retrieve a collection of all log messages:

```bash
http http://127.0.0.1:5000/api/v1/logs
```
**Response**

```json
{
  "data": {
    "items": [
      {
        "message": "[Appium] Appium REST http interface listener started on 0.0.0.0:4723",
        "level": "info"
      },
      {
        "message": "[Appium] Appium support for versions of node < 8 has been deprecated and will be removed in a future version. Please upgrade!",
        "level": "warn"
      },
      {
        "message": "[Appium]   webhook: 127.0.0.1:5000",
        "level": "info"
      },
      {
        "message": "[Appium] Welcome to Appium v1.8.0",
        "level": "info"
      },
      {
        "message": "[Appium] Non-default server args:",
        "level": "info"
      }
    ],
    "size": 5
  }
}
```

Retrieve the last log message:

```bash
http http://127.0.0.1:5000/api/v1/logs/last
```

**Response**

```json
{
  "data": {
    "message": "[Appium] Appium REST http interface listener started on 0.0.0.0:4723",
    "level": "info"
  }
}
```

 Retrieve N lines of log (from the top):

```bash
http http://127.0.0.1:5000/api/v1/logs/1
```

**Response**

```json
{
  "data": {
    "items": [
      {
        "message": "[Appium] Received SIGINT - shutting down",
        "level": "info"
      }
    ],
    "size": 1
  }
}
```

# API Documentation

We use [apiDoc](http://apidocjs.com/) in order to generate documentation for Appium Log Reader’s HTTP API.

**apiDoc** creates a documentation from API annotations in your source code. Please check the documentation [here(http://apidocjs.com/#params)

The proyect come with a Maven task in order to generate the documentation from Maven without any pain, but FIRST you need install **apiDoc** of course:

```bash
npm install apidoc -g 
```

And now we need execute the following command: 

```bash
mvn exec:exec
```

The documentation is generated on the **doc** directory.

## Contribution

Any ideas are welcome. Feel free to submit any issues or pull requests.
