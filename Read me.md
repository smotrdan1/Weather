# Weather Insight Service

### Introduction

Welcome to the Weather Insight Service! This service provides users with insights into weather conditions based on provided parameters such as latitude, longitude, and weather condition. It ingests weather data from CSV files, processes it, and exposes an API endpoint to retrieve weather insights.

### Usage

To use this service, make a GET request to the following endpoint:

https://weatherapp-ltx4.onrender.com/weather/insight?condition={condition}&lat={latitude}&lon={longitude}

Query Parameters:

- `condition`: The weather condition for which insights are requested. Accepted values are `veryHot` or `rainyAndCold`.
- `lat`: The latitude coordinate of the location.
- `lon`: The longitude coordinate of the location.

Example Usage #1:

https://weatherapp-ltx4.onrender.com/weather/insight?lon=35&lat=-90&condition=veryHot

The service will return a JSON array containing weather insights with the following structure:

Expected Response:

[
    {
        "forecastTime": "2021-04-02T13:00:00",
        "conditionMet": true
    },
    {
        "forecastTime": "2021-04-02T14:00:00",
        "conditionMet": false
    },
    {
        "forecastTime": "2021-04-02T15:00:00",
        "conditionMet": false
    }
]


Example Usage #2(lan/lon not rounded up to integer nor .5):

https://weatherapp-ltx4.onrender.com/weather/insight?lon=35.6&lat=-90&condition=veryHot

Expected Response:

The service will return an empty JSON array if no insights are available.


Example Usage #3 (lat/lon/condition does not pass validation):


https://weatherapp-ltx4.onrender.com/weather/insight?lon=356&lat=-90&condition=veryHot

Expected Response:

The service will return a 400 BAD_REQUEST status with the appropriate error message(see Assumptions section):

400 BAD_REQUEST "Invalid longitude. Valid range is --180 to 180"




###### Implementation Details

### Database
The service utilizes a PostgreSQL database to store weather data ingested from CSV files. The CSV files provided for this assignment are uploaded to a table called weather, and the data is stored there.

For this purpose, I have created an internal API endpoint for personal use. It triggers the CSV upload service and writes the CSV data into the table using threads and batch writing.

The number of threads and batch size may be changed (see Optimization section).

# Note: 
Please do not run this endpoint unless you want duplicated data! Running it will result in duplicate records in the database because its sole purpose is to read from CSV files and write to the PostgreSQL database.

To trigger the CSV upload service and write the CSV data into the table, an internal API endpoint is available:

https://weatherapp-ltx4.onrender.com/csv/digest

## example of retunred values:
200 http status with "CSV data loaded successfully." for succsesful data writing.
500 http status with "Failed to load CSV data" for failure.



### Web Server
The service runs on a web server utilizing Java, Spring Boot, JPA, and Hibernate. It reads weather data from the database and exposes it via the API endpoint /weather/insight.

The data is returned according to the conditions specified in the assignment.


### Deployment

The service has been deployed using render.com. Both the web service and the PostgreSQL database are hosted on render.com. The web service is Dockerized and uploaded via Docker, and the database is created on render.com. The communication between the web service and the database is facilitated using a YAML blueprint file.


### Optimization and Pitfalls
List of potential optimizations and pitfalls in the solution:

1. Optimization: Utilize a caching mechanism (e.g., Redis) to store frequently accessed weather data and improve response times.
2. Optimization: Implement continuous data flow for real-time updates.
3. Pitfall: Ensuring accuracy in latitude and longitude queries to avoid misleading results.
4. Optimization: Optimizing the number of threads and the batch size.
5. Optimization: Add unit tests to test the application.


### Assumptions
List of assumptions made during the implementation:

1. The latitude and longitude values provided in the query parameters may not always be rounded or have increments of 0.5. Hence, the service returns an empty array if the exact coordinates are not available.

2. Conditions specified in the query parameters (condition) must be case-sensitive and adhere to strict formatting rules. Any deviations result in a 400 BAD_REQUEST status with the appropriate error message.

# Note: 
This is not a production-ready service. While it provides basic functionality, additional optimization such as continuous data flow, using alternative data sources, improving accuracy in latitude and longitude queries, and deploying on servers with higher resources are recommended for production use. Additionally, making the service more resilient to massive loads of API calls is crucial for scalability and reliability.


