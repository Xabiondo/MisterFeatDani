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
<h2>Buscar Subasta</h2>
<form action="/buscar" method="get">
    <label for="nombre">Nombre del jugador:</label>
    <input type="text" id="nombre" name="nombre" placeholder="Ej: Lionel Messi">
    <button type="submit">Buscar</button>
</form>

<!-- Lista de subastas activas -->
<h2>Subastas Activas</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID Subasta</th>
        <th>Jugador</th>
        <th>Equipo del Jugador</th>
        <th>Precio de Salida</th>
        <th>Fecha de Inicio</th>
        <th>Vendedor</th>
        <th>Acción</th>
    </tr>
    </thead>
    <tbody>
    <!-- Iteramos sobre la lista de subastas -->
    <#list subastas as subasta>
        <tr>
            <td>${subasta.id}</td>
            <td>${subasta.jugador.nombre}</td>
            <td>${subasta.jugador.equipo}</td>
            <td>${subasta.precioSalida} €</td>
            <td><button>Pujar</button></td>
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