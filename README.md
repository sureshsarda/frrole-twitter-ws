# Frrole WS
## Elastic Search POC with Twitter Stream API

The project is divided into 3 modules:
### elastic-search-backend
This is a JAR project that integrates elastic search with this application and currently provides only implementation to interact with elastic search with it's REST endpoints. More Elastic Search implementation can be done in this module like advanced searching and getting any particault tweet details, etc.
###twitter-stream
Fetching twitter data, authentication and other twitter related activities.
###frolle-ws
This is a Java EE Web Services project currently has only one REST endpoint. All customer should interact with this application.

# REST Endpoint
The applications listens at frolle-ws/api/tweet

### POST

#### Request
**Headers**
Content: application/json
**Body**
Body should be in Json format with following parameters:
```json
{
	"query" : "search_string",
	"format" : "xml|csv|json"
}
```
The format can be XML, CSV or JSON. Any other format is not supported. All the fields are mandatory.

Currently no other methods are supported.