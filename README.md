# WeatherAPITest

Using openweathermap.org for getting weather details. 

Weather details for next 4 days and 3 hours per day fore casting returned.

Responses are cached to reduce above service calls and improve performace. ConcurrentHashMap ( default cache of spring boot ) are used for the same. 

xjc and jsonschema2pojo for converting xml schema and json schema to java files. Schemas are saved in resources folder. 

Both xml and json response type are available. http://localhost:8080/getReport/zip/{zipid}
Eg http://localhost:8080/getReport/zip/940401
Content-Type : application/json
Content-Type : application/xml

One test case has written for evaluation purpose. 

Two filters are written to track logging and request evaluation. Logs folder is created for log files. 

"Zip is invalid" meesage in case zip is not validated in filter

"Some issue is there, while processing your request. Please try some time later" in case some error ocurred in service. 


Future Scope. 

Response can be compressed. 
Security can be provided. 
