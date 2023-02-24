# First you'll want to set the API_KEY in the config class, just head to https://platform.openai.com/account/api-keys and get yours.

# Second you'll want to change the directories in both the "Controller" and the "Docker-compose" file. you'll need it for your csv file.

# Now you'll want to navigate to the folder
```bash
cd ..
```

# Then you'll want to create a new docker container
```bash
docker build -t youruser/nameoftheapp:version .
```
note that in the "nameoftheapp:version" you can really write anything you want.

# Now just run this:
```bash
docker compose up
```

# After seeing that application has started, go to Postman and send a POST request to : http://localhost:8080/api/v1/bot/send with a body :
```bash
{ "message": "____" }
```

you won't need to add any authorization or Headers on Postman since it's added in the code.
