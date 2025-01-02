#!/bin/bash

APP_NAME="soundnata-0.0.1-SNAPSHOT.jar" # Replace with your JAR file name
APP_PATH="target/$APP_NAME"          # Path to your JAR file
LOG_FILE="application.log"           # Log file for application output
PID_FILE="app.pid"                   # File to store the application's process ID

start_app() {
    if [ -f "$PID_FILE" ] && kill -0 $(cat "$PID_FILE") > /dev/null 2>&1; then
        echo "Application is already running!"
    else
        echo "Starting application..."
        nohup java -jar "$APP_PATH" > "$LOG_FILE" 2>&1 &
        echo $! > "$PID_FILE"
        echo "Application started with PID $(cat $PID_FILE). Logs: $LOG_FILE"
    fi
}

stop_app() {
    if [ -f "$PID_FILE" ] && kill -0 $(cat "$PID_FILE") > /dev/null 2>&1; then
        echo "Stopping application..."
        kill -15 $(cat "$PID_FILE")
        rm -f "$PID_FILE"
        echo "Application stopped."
    else
        echo "Application is not running."
    fi
}

restart_app() {
    echo "Restarting application..."
    stop_app
    sleep 2
    start_app
}

status_app() {
    if [ -f "$PID_FILE" ] && kill -0 $(cat "$PID_FILE") > /dev/null 2>&1; then
        echo "Application is running with PID $(cat $PID_FILE)."
    else
        echo "Application is not running."
    fi
}

case "$1" in
    start)
        start_app
        ;;
    stop)
        stop_app
        ;;
    restart)
        restart_app
        ;;
    status)
        status_app
        ;;
    *)
        echo "Usage: $0 {start|stop|restart|status}"
        exit 1
        ;;
esac