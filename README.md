# spring-boot

[![Build Status](https://travis-ci.org/dbi1463/spring-boot.svg?branch=master)](https://travis-ci.org/dbi1463/spring-boot)
[![codecov](https://codecov.io/gh/dbi1463/spring-boot/branch/master/graph/badge.svg)](https://codecov.io/gh/dbi1463/spring-boot)

## Get Started

Download the repository, run the command to start the server:

```
gradle bootRun
```

## Components

The server consists of some important components:

### `Advertisement`

The data model and also the ORM model.

### `MockAdvertisementWebService`

This class is a `RestController` to provide either a correct advertisement immediately, or provide `{}` after 10 seconds.

### `AdvertisementRepository`

This interface defines the methods to access the database for persisting or obtaining objects. The implementation is provided by Spring Data framework.

### `AdvertisementBiddingSystem`

This class tries to get an advertisement from TenMax or `MockAdvertisementWebService` every minute. Each request waits at most 5 seconds and then save the advertisement if it presented.

### `TenMaxAdvertisementWebService`

This class is a `RestController` to query saved advertisements with query parameter `q`, for example, `http://localhost:8080/advertisements?q=3`.
