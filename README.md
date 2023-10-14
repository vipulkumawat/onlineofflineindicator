# onlineofflineindicator
Prototype for online offline indicator with spring boot and H2

#### GET: localhost:8080/status/user/{user_id}
* Example: GET http://localhost:8080/status/user/1
* output: false

#### PATCH http://localhost:8080/heartbeat/
* Body: [1,2,3,4,5]
* output: pulse updated.

#### GET: localhost:8080/status/user/{user_id}
* Example: GET http://localhost:8080/status/user/1
* output: true

#### GET: localhost:8080/status/user/{user_id}
* Example: GET http://localhost:8080/status/user/3
* output: true

#### GET: localhost:8080/status/user/{user_id}
* Example: GET http://localhost:8080/status/user/6
* output: false

___________________After 30 sec _____________________________
#### GET: localhost:8080/status/user/{user_id}
* Example: GET http://localhost:8080/status/user/1
* output: false

#### GET: localhost:8080/status/user/{user_id}
* Example: GET http://localhost:8080/status/user/3
* output: false
