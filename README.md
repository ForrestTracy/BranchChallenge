# BranchChallenge

## to run locally: gradle bootRun command
$ ./gradlew bootRun

## gradle run tests command
$ ./gradlew test

## PRODUCTION web curl
### I deployed my application to an Amazon EC2 instance. You can test it and use it here:
$ curl --request GET "http://18.224.90.44:8080/github-data/octocat"

## local curl
$ curl --request GET "http://localhost:8080/github-data/octocat"

## local curl for testing-only using a "Fake Cache"
$ curl --request GET "http://localhost:8080/github-data/octocat?useOnlyCache=true"

# References
Getting Started with GitHub APIs: [HERE](https://docs.github.com/en/rest/using-the-rest-api/getting-started-with-the-rest-api?apiVersion=2022-11-28)

Octocat Profile Endpoint: [HERE](https://api.github.com/users/octocat)

Octocat Repos Endpoint:   [HERE](https://api.github.com/users/octocat/repos)


# Expansions I'd make if this was intended for full PROD activity

1. Implement a true cache.
2. Create a GitHub account to get an access token to allow higher rates of use.
3. Use access token to gather data about repos with non-public scope access.
4. Add an Open API ("Swagger") page for reference.