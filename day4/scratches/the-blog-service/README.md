The Blog Service
Register Blogger
POST /blogger

{
"email": "hdoe@apper.ph",
"name": "John Doe",
"password": "jdoe123"
}
All fields are required.
Email format must be valid.
Password must be at least 5 characters.
Response
201 - Created

{
"id": "<UUID>",
"date_registration": "2023-07-11T23:23:12"
}