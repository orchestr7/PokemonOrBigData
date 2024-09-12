# Pokemon or BigData
This full-stack Kotlin app is a humorous test of 12 questions, made over a couple of nights to illustrate how large a zoo of names has formed in the Big Data world. 
The original idea was born [here](https://docs.google.com/forms/d/e/1FAIpQLScRsfRHXPTuEXdNvUcI8DzJIU5iazqlpksWucPF0d8l2ztkkA/viewform) and [here](https://pixelastic.github.io/pokemonorbigdata/).

# How to
### Internals
-- frontend: React application powered by KotlinJS

-- backend: Spring Boot application powered by KotlinJVM

### Build and run
1) Build: `./gradlew build`

2) Run backend locally: `./gradlew backend:bootRun`

3) Run frontend locally: `./gradlew frontend:jsBrowserDevelopmentRun`

### Deployment
We are using deploy-gh action, which will build the frontend in `gh-pages` branch (without resources and images included).
To configure it do the following:
```
If you do not supply the action with an access token or an SSH key, you must access your repositories settings and provide Read and Write Permissions to the provided GITHUB_TOKEN, otherwise you'll potentially run into permission issues. Alternatively you can set the following in your workflow file to grant the action the permissions it needs.
```


