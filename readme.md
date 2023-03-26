# Music for Weather API - group 5

## Introduction
An API application that takes the name of a city as input and provides a song recommendation based on the current weather conditions in that city.

## Pre-Requisities

- Java SE Development Kit 17
- Maven
- Deezer API https://rapidapi.com/deezerdevs/api/deezer-1
- Weather API - https://www.weatherapi.com/docs/

## Technology & Dependencies

- Spring Boot
- Spring Web
- Lombok
- openjfx
- junit


## UML diagram
![UML Diagram of Mars Rover Challenge](./images/Music4WeatherAPI-group5.drawio.png)


## Features

- Recommend a song to the User based on input city name and city's current weather condition.
- If it's sunny in the user's location, we can recommend them a random song from a keyword related to sun/sunshine.


## Endpoints

| Number | Endpoint                         | Description                                                                              |
|--------|----------------------------------|------------------------------------------------------------------------------------------|
| 1      | GET /api/v1/music4Weather        | Welcome to Music for Weather API                                                         |
| 2      | GET /api/v1/music4Weather/{city} | input : city name output : song recommendation preview                                   |
| 3      | GET /weather/{city}              | intput : city name output : current weather info                                         |
| 4      | GET /keywords/{city}             | input : city output : random one track preview call getWeatherByCity then getTrachByName |
| 5      | GET /api/v1/track/{id}           | input : id output : music data                                                           |
| 6      | GET /api/v1/track/search?q=      | input : keyword output : music data                                                      |


## Test result


## Future thoughts
