# API
***
```/api/v1/posts```   
#### POST
- 게시글 작성 기능

#### Request body
```json
{
  "title": "제목(string)",
  "content": "내용(string)" 
}
```
#### Response body
###### Status Code: 200 Ok (정상 요청)
```json
{
  "id": "번호(number)",
  "title": "제목(string)",
  "content": "내용(string)"
}
```
###### Status Code: 400 Bad Request (올바르지 않은 요청)   

```json
{
  "message": "오류메세지(string)"
}
```
***
```/api/v1/posts/all```  
#### GET
- 모든 게시글 불러오기 기능

#### Response body
###### Status Code: 200 Ok (정상 요청)

```json
{
  "Posts": [
    {
      "id": "번호(number)",
      "title": "제목(string)",
      "content": "내용(string)"
    }
  ]
}
```
###### Status Code: 400 Bad Request (올바르지 않은 요청)

```json
{
  "message": "오류메세지(string)"
}
```
***

```/api/v1/posts/{id}```
#### GET
- 특정 게시글 조회 기능
#### Response body
###### Status Code: 200 Ok (정상 요청)

```json
{
  "id": "번호(number)",
  "title": "제목(string)",
  "content": "내용(string)"
}
```
###### Status Code: 400 Bad Request (올바르지 않은 요청)
```json
{
  "message": "오류메세지(string)"
}
```
***


```/api/v1/posts/{id}```
#### PUT
- 특정 게시글 수정 기능
#### Request body
```json
{
  "title": "변경제목(string)",
  "content": "변경내용(string)",
}
```
#### Response body
###### Status Code: 200 Ok (정상 요청)
###### Status Code: 400 Bad Request (올바르지 않은 요청)
```json
{
  "message": "오류메세지(string)"
}
```
***

```/api/v1/posts/{id}```
#### DELETE
- 특정 게시글 삭제 기능
#### Response body
###### Status Code: 200 Ok (정상 요청)
###### Status Code: 400 Bad Request (올바르지 않은 요청)
