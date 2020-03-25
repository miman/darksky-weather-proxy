# darksky-weather-proxy
Proxy that can retreive weather info from [Darksky](https://darksky.net/) &amp; store this into an Excel file

# Usage
Retrieve the weather data with the following URL's

The data is stored into an Excel file (located in the target directory) with the name you specified in the URL (or unknown if no name is supplied)
If the file doesn't exist it is created, if it exists the data is just appended to what's already in the file.

## Get current weather
http://localhost:8080/currentweather?longitude=11.974560&latitude=57.708870&name=gothenburg

## Get Weather for specific day
http://localhost:8080/historicaltweather?longitude=11.974560&latitude=57.708870&name=gothenburg&time=2019-12-03T10:15:30Z

## Get weather for all days for a specified year
http://localhost:8080/yearlyweather?longitude=11.974560&latitude=57.708870&name=gothenburg&year=2019
