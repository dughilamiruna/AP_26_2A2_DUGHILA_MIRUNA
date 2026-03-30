<!DOCTYPE html>
<html>
<head>
    <title>Raport Filme</title>
</head>
<body>
<h2>Movie List</h2>
<table border="1">
    <tr>
        <th>Titlu</th>
        <th>Data Lansare</th>
        <th>Durată</th>
        <th>Scor</th>
        <th>Gen</th>
        <th>Actori</th> </tr>
    <#list movies as movie>
        <tr>
            <td>${movie.title}</td>
            <td>${movie.releaseDate}</td>
            <td>${movie.duration} min</td>
            <td>${movie.score}</td>
            <td>${movie.genre}</td>

            <td>${movie.actors!'-'}</td>
        </tr>
    </#list>
</table>
</body>
</html>