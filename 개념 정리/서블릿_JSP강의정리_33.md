# 서버에서 페이지 전환해주기(Redirection)

## Redirection
> - 요청을 수행하고 서버에서 백지가 아닌 페이지로 전환해주는 기능
> - 사용자가 요청하지 않아도 서블릿이 페이지를 전환시켜주며, 주소창의 주소가 바뀐다.

### 사용법
---
``` java
response.sendRedirect("calc2.html");
```
