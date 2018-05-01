# Appium log reader
Appium log reader is a simple viewer that works with the Appium logs. It supports filtering, searching, highlighting and many other useful features. 

[Appium](http://appium.io) has a built-in mechanism in order to post messages to an external Webhook. They make use of normal HTTP requests with a JSON payload that includes a log message and the log level. This service listens these messages.

You can also use **Appium log reader** as an API.


---

![Dashboard](https://raw.githubusercontent.com/mobileboxlab/appium-log-reader/master/docs/screen.png)

## Features

  - A great Appium log HTTP API.
  - View, filter, searching and and export logs in different formats such as: XLS, CSV, TXT, JSON, SQL, etc.
  - Localized log levels.
  - Grouped logs levels.
  - Shortcut to search errors messages on [DuckDuckGo](https://duckduckgo.com)


# Setup 

## Requirements



## Start Appium log reader



## Start Appium

Start Appium using flag the **--webhook** flag. This flag enable the log output to HTTP listener:

appium --webhook ip:port 



## HTTP API



## Contribution

Any ideas are welcome. Feel free to submit any issues or pull requests.
