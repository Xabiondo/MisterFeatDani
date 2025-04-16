<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

</head>
<body>

<!-- Contenedor principal -->
<div class="menu-container">
    <!-- Saludo personalizado -->
    <h1>¡Hola, ${nombreUsuario}!</h1>
    <p>Tu dinero disponible es: $${dineroDisponible}</p>

    <!-- Menú de opciones -->
    <button class="menu-button"><a href="/poner-subasta">Poner jugador a subasta</a></button>
    <button class="menu-button"><a href="/Mercado">Mercado</a></button>
    <button class="menu-button"><a href="/Equipo">Ver mi equipo</a></button>
    <button class="menu-button"><a href="/ver-dinero">Ver mi dinero disponible</a></button>
    <button class="menu-button"><a href="/logout">Cerrar sesión</a></button>
</div>

</body>
</html>