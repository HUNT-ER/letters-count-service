# Letters Count API
This API allows you to count letters (unique symbols) in any string. 

# Build with
- Spring Boot
- Spring Web MVC
- Lombok

## Set up 
- [*clone*](https://github.com/HUNT-ER/letters-count-service.git) the project
- run the project using [LettersCountApplication.java](src/main/java/org/boldyrev/letterscount/LettersCountApplication.java)

# API Reference  
**POST** `/api/v1/letters-count`
  count letters in string by request body:
```agsl
  request body:
{
    "input_string": "Test string",
}
```
