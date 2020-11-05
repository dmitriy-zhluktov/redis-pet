# redis-pet
Сначала ставим Redis на Windows.
Для этого используем Docker. Для версий Windows, не поддерживающих Docker, ставим Docker Toolbox.

Далее в докере:
<br/>
docker pull redis
<br/>
docker run --name redis -p 6379:6379 -d redis
<br/>
Аргумент -p выставляет порт для доступа к контейнеру redis извне.
Порт 6379 является портом по умолчанию
<br/>
<br/>
Проверяем, что всё завелось:
<br/>
docker exec -it redis sh
redis-cli

На выходе должны получить:
<br/>
127.0.0.1:6379>
