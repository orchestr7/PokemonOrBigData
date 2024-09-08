# Важно
Сделано за 3 ночи 1 человеком, который был и швец, и жнец, и на дуде игрец. И девопс, и бэкендер и фронтендер. 
Спасибо ChatGpt и моей маме.

# Покемон Или BigData

Шутливое приложение, которое показывает, насколько огромный зоопарк из названий образовался в дате.
Оригинальная идея и отсылки к [этой форме](https://docs.google.com/a/octo.com/forms/d/1kckcq_uv8dk9-W5rIdtqRwCHN4Uh209ELPUjTEZJDxc/viewform) и к [этому](https://github.com/pixelastic/pokemonorbigdata) проекту.


### How to
1) Build: `./gradlew build`

2) Run locally: `./gradlew frontend:jsBrowserDevelopmentRun`

### Runbook
We are using deploy-gh action, which will build the project in gh-pages branch.
To configure it do:
```
If you do not supply the action with an access token or an SSH key, you must access your repositories settings and provide Read and Write Permissions to the provided GITHUB_TOKEN, otherwise you'll potentially run into permission issues. Alternatively you can set the following in your workflow file to grant the action the permissions it needs.
```


