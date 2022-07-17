# Six Letter Words API

To use Docker to run the application pull the image with following command:

``` 
docker pull foodsnotgood/sixletterapi
```
To run the it you can use following command:
```
docker run -p8080:8080 foodsnotgood/sixletterapi
```

In case the port 8080 is already occupied on your machine you can use a different (i.e. 8081)

```
docker run -p8081:8080 foodsnotgood/sixletterapi
```

To test the api you can easily import the 6LetterApi.postman_collection.json in Postman.
If so, make sure to reselect the correct files for the 'txt' and 'csv' request in the request body as the absolute path will be different once you pulled the repository.

The third 'string' request contains the same values as the input files but will be sent as plain text instead of a multipart request.

The app uses an in memory h2 database and stores the results there. You can check it in the browser under:

>localhost:8080/h2-console

Change the port number if needed like above.

Enter following credentials:
>url: jdbc:h2:mem:api \
>username: sa \
>no password


Have fun!
