Post http://localhost:8080/animal
```bash
{
	"name":"",
	"breed":"",
	"appearance":"",
	"species":"",
	"age":0,
	"weight":0,
	"client":{
	  "id":"0"
	}
}
```
Post http://localhost:8080/client
```bash
{
	"name":"",
	"email":"",
	"tel":"",
	"age":""
}
```
http://localhost:8080/service
```bash
{
	"name":"",
	"description":"",
	"time":"" 
}
```
Post http://localhost:8080/schedule
```bash
{
	"date":"2022/07/18 16:21:00",
	"service":{
		"id":16
	},
	"animal":{
		"id":15
	},
	"paid":false,
	"concluded":false
}
```