<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mercado de Jugadores de Fútbol</title>
</head>
<body>
<h1>Mercado de Jugadores de Fútbol</h1>

<!-- Formulario de búsqueda -->
<h2>Buscar Jugador</h2>
<form action="/buscar" method="get">
    <label for="nombre">Nombre del jugador:</label>
    <input type="text" id="nombre" name="nombre" placeholder="Ej: Lionel Messi">
    <button type="submit">Buscar</button>
</form>

<!-- Lista de jugadores disponibles -->
<h2>Jugadores Disponibles</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Equipo</th>
        <th>Precio</th>
        <th>Acción</th>
    </tr>
    </thead>
    <tbody>
    <!-- Iteramos sobre la lista de jugadores -->
    <#list jugadores as jugador>
        <tr>
            <td>${jugador.id}</td>
            <td>${jugador.nombre}</td>
            <td>${jugador.equipo}</td>
            <td>${jugador.precio} €</td>
            <td><button>Fichar</button></td>
        </tr>
    </#list>
    </tbody>
</table>

<!-- Pie de página -->
<footer>
    <p>&copy; 2023 Mercado de Jugadores de Fútbol. Todos los derechos reservados.</p>
</footer>
</body>
</html>