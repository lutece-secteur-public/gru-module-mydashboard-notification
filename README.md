# gru-module-mydashboard-notification

## ENDPOINT

The endpoints for notification API

###### PRODUCES AND CONSUMES

```
application/json
```

###### GET NOTIFICATION BY ID
```
METHOD GET => /rest/notification-api/{id}
```
###### GET LIST NOTIFICATION BY GUID
```
METHOD GET => /rest/notification-api/list/{userId}
```
###### CREATE NOTIFICATION
```
METHOD POST => /rest/notification-api/add

Data example (Application/json):
{
	"object":"Your object",
	"message":"Your message",
	"sender":"your sender",
	"idUser":"user guid",
	"dateCreation":"2022-04-13"
}

dateCreation: format accepted ("yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd")
```
###### CREATE LIST OF NOTIFICATION
```
METHOD POST => /rest/notification-api/addList

Data example (Application/json):
[
	{
		"object":"Your object",
		"message":"Your message",
		"idUser":"guid",
		"sender":"sender",
		"dateCreation":"2022-04-13"
	},
	{
		"object":"Your object",
		"message":"Your message",
		"idUser":"guid",
		"sender":"sender",
		"dateCreation":"2022-04-13"
	}
]
dateCreation: format accepted ("yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd")
```
###### REMOVE NOTIFICATION 
```
METHOD DELETE => /rest/notification-api/{id}
```