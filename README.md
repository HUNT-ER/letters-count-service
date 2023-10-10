# Letters Count API
This API allows you to count letters (unique symbols) in any string.
Response will be in decending order by count letters.

# Build with
- Spring Boot
- Spring Web MVC
- Lombok

## Set up 
- [*clone*](https://github.com/HUNT-ER/letters-count-service.git) the project
- run the project using [LettersCountApplication.java](src/main/java/org/boldyrev/letterscount/LettersCountApplication.java)

# API Reference  
**POST** `/api/v1/letters-count?case-sensitive=(default true)`
  count letters in string by request body:
```agsl
request body:
{
    "input_string": "This Is A Test String"
}
```

```agsl
response body with case-sensitive flag == true or without flag:
{
    "input_string": "This Is A Test String",
    "letters_count": "' ': 4,'s': 3,'t': 2,'T': 2,'i': 2,'A': 1,'r': 1,'S': 1,'e': 1,'g': 1,'h': 1,'I': 1,'n': 1"
}
```

```agsl
response body with case-sensitive flag == false:
{
    "input_string": "This Is A Test String",
    "letters_count": "' ': 4,'s': 4,'t': 4,'i': 3,'a': 1,'r': 1,'e': 1,'g': 1,'h': 1,'n': 1"
}
```



