# Weather-Monitoring
CREATE TABLE alert_thresholds (
    id BIGINT NOT NULL AUTO_INCREMENT,
    condition VARCHAR(255),
    threshold_value DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

Create a MariaDB database named weather_db.


README:
# Real-Time Weather Monitoring System

## Overview
The Real-Time Weather Monitoring System is a Spring Boot application designed to fetch, process, and analyze weather data from the OpenWeatherMap API. It provides summarized insights such as daily weather summaries and alerting features based on user-configurable thresholds. The system continuously retrieves real-time weather data for major cities in India and aggregates it to offer meaningful insights.

## Features
- **Real-Time Data Retrieval**: Continuously fetches weather data for cities like Delhi, Mumbai, Chennai, Bangalore, Kolkata, and Hyderabad.
- **Daily Weather Summaries**: Aggregates weather data daily to calculate average, maximum, minimum temperatures, and dominant weather conditions.
- **Alerting Mechanism**: Configurable thresholds for temperature and weather conditions that trigger alerts.
- **Support for Additional Parameters**: Incorporates humidity, wind speed, and other weather parameters into summaries.
- **Visualizations**: Provides insights through historical trends and triggered alerts (to be implemented).
  
## Getting Started
These instructions will help you set up and run the application on your local machine for development and testing purposes.

### Prerequisites
- Java JDK 8
- Maven
- MariaDB
- An OpenWeatherMap API key

### Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/weather-monitoring.git
   cd weather-monitoring


#steps for openweathermap API Key:
sign up in https://openweathermap.org
once you login, navigate to9 API keys section .
copy that api key and place in appplication.properties under
#replace your_api_key with your actual api key
openweather.api.key=dab5a0f70a3bb365fb7c8ab346b085cf 
