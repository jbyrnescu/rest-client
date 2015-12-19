# Http Rest Client
It supports **json** by [JsonPath](https://github.com/jayway/JsonPath) and **xml** by [XPath](https://docs.oracle.com/javase/tutorial/jaxp/xslt/xpath.html) responses.

## Features
 * Support for cookies
 * Flexibility provided by plugins

## Maven artifact
```xml
<dependency>
    <groupId>org.jsoftware</groupId>
    <artifactId>rest-client</artifactId>
    <version>1.2</version>
</dependency>
```

## Examples:
### Http GET request for JSON response:
```java
RestClient restClient = new DefaultRestClient();                             // new instance of RestClient
restClient.setPlugins(Arrays.asList(new VerbosePlugin(false, System.out)));  // add verbose plugin - it prints request and response to stdout
RestClientResponse response = restClient.get("https://api.stackexchange.com/2.2/answers") // API URL (http get)
                                .parameter("pagesize", 5)                         // Parameters
                                .parameter("order", "desc")
                                .parameter("sort", "activity")
                                .parameter("site", "stackoverflow")
                                .execute();                                         // execute http call

List<Number> questionIds = (List<Number>) response.json("$..answer_id");            // get all answer_id fields from response
```

### Http GET request for XML response:
```java
RestClient restClient = new DefaultRestClient();                             // new instance of RestClient
RestClientResponse response = restClient.get("https://api.somewhere.com.com/xml/call") // API URL (http get)
                                .execute();
NodeList nList = (NodeList) response.xPath("/answer/items/", XPathConstants.NODESET);  // fetch xml data using XPath notation
```

### Http POST request:
Emulate sending html form:
```java
RestClient restClient = new DefaultRestClient();                             // new instance of RestClient
RestClientResponse response = restClient.post("https://somewhere.com")       // API URL (http post)
                                .parameter("username", "John")               // Http form parameters
                                .parameter("password", "John's password")
                                .execute();                                  // execute http call
```

Send JSON:
```java
RestClient restClient = new DefaultRestClient();                             // new instance of RestClient
RestClientResponse response = restClient.post("https://somewhere.com")       // API URL (http post)
                                .body("{\"username\" : \"John\"}", org.apache.http.entity.ContentType.APPLICATION_JSON)      // request body
                                .execute();                                  // execute http call
```

### Add custom header
```java
RestClient restClient = new DefaultRestClient();
    RestClientResponse response = restClient.get("https://somewhere.com")
                                    .header("headerName", "headerValue")    // add http header
                                    .execute();                             // execute http call
```