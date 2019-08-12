# RECALL Sample

## Porpose
This sample is a project to show how to do recall for canceled rides. The sample ups a server with a endpoint to recall a ride.

## Run project
First, need changes some lines on recallService.java:
```Java
    /**
     * API Key to identify and login you API
     */
    private static final String API_KEY = "YOUR_API_KEY";
    /**
     * Company ID to distinct company if your login has more than one company
     */
    private static final String COMPANY_ID = "YOUR_COMPANY_ID";
```
Change the variable `API_KEY`'s contain for your API Key, and change the `COMPANY_ID`'s contain for your company key. 


To run this application, only need to execute the next command in the recall folder:
```cmd
$ ./gradlew bootRun
```

## Testing endpoint
Post on the endpoint to do recall:
```cmd
$ curl -X POST http://localhost:8080/recall/123456789
```
- Note: `123456789` is the canceled ride identifier.