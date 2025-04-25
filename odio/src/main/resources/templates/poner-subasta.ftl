<!DOCTYPE html>
<html>
<head>
<title>Subasta de Jugador</title>
</head>
<body>
<h1>Subasta de Jugador</h1>

<form action="/pujar" method="post">
        <p><strong>Nombre del jugador:</strong> Lionel Messi</p>
        <p><strong>Posición:</strong> Delantero</p>
        <p><strong>Equipo:</strong> Inter Miami</p>
        <p><strong>Precio inicial:</strong> $10,000,000</p>

        <label for="nombre">Tu nombre:</label><br>
        <input type="text" id="nombre" name="nombre"><br><br>

        <label for="puja">Tu puja ($):</label><br>
        <input type="number" id="puja" name="puja" step="100000"><br><br>

        <button type="submit">Enviar puja</button>
    </form>
</body>
</html>
